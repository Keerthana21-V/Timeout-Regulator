import React, { Component } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import LeaveRequestItem from './LeaveRequestItem';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableFooter from '@material-ui/core/TableFooter';
import TablePagination from '@material-ui/core/TablePagination';
import TableRow from '@material-ui/core/TableRow';
import TableHead from '@material-ui/core/TableHead';

import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';

import Divider from '@material-ui/core/Divider';
import Skeleton from '@material-ui/lab/Skeleton';

import getUrl from "./getUrl"
import getStatus from "./getStatus"

const url = getUrl();

const useStyles = function(){ 
  return {
    root: {
      width: '100%',
    },
    inline: {
      display: 'inline',
    },
  }
}

const labelStyle = {
  marginLeft : "15px",
  marginRight : "5px",
}

const divStyle = {
  width : "75%",
  margin : "auto",
  marginTop : "150px",
  boxShadow: "0 4px 8px 0 rgba(0,0,0,0.2)",
  backgroundColor : "white",
}

const modalHeader = {
  padding: "2px 16px",
  backgroundColor: "#4682B4",
  color: "white",
  marginBottom : "15px"
}

const closeStyle = {
  color: "black",
  float: "right",
  fontSize: "28px",
  fontWeight: "bold",
  cursor: "default",
  height: "15px",
  width: "15px",
  backgroundColor: "#3D3",
  borderRadius: "50%",
  marginTop : "10px",
}

let pageN = 0;

function handleChangePage(event, newPage) {
  console.log("Hello World "+pageN+" "+newPage);
  //console.log(event.target);
}

export default class ManagerInbox extends Component {

  constructor(){
    super()
    this.classes = useStyles()
    this.state = {
      open : false,
      requests : [],
      fetched : false,
      applicationBody : null,
      responseBody : null,
      leaveStatus : null,
      typeOfLeave : null,
      startDate : null,
      endDate : null,
    }
    this.handleClickOpen = this.handleClickOpen.bind(this)
    this.handleClose = this.handleClose.bind(this)
    this.handleFilterChange = this.handleFilterChange.bind(this)
    this.requestData = this.requestData.bind(this)
  }

  requestData(){
    const obj = {
      eid : 1022,
      leaveStatus : this.state.leaveStatus,
      typeOfLeave : this.state.typeOfLeave,
      startDate : this.state.startDate,
      endDate : this.state.endDate,
    }
    this.state.fetched = false;
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = () => {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            const data = JSON.parse(xhttp.response);
            console.log(data.length,data);
            this.setState({
              requests : data,
              fetched : true,
            })
        }
    };
    xhttp.open("POST",url+"/manager/getHistory", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(obj));
  }

  componentDidMount(){
    //alert("Hello");
    this.requestData()
  }

  handleFilterChange(event) {
    const {name, value} = event.target
    console.log(name,value);
    this.setState({
        [name]: value===""?null:value,
        fetched : false,
    },() => {
      this.requestData()
    }) 
}

  handleClickOpen(event) {
    const applicationBody = event.currentTarget.getAttribute('applicationBody');
    const responseBody = event.currentTarget.getAttribute('responseBody');
    const responseStatus = event.currentTarget.getAttribute('responseStatus');
    console.log(responseStatus);
    this.setState({
      open:true,
      applicationBody : applicationBody,
      responseBody : responseBody,
      responseStatus : responseStatus!=='NOT_PROCESSED',
      dialogStatus : getStatus(responseStatus),
    })
  }

  handleClose() {
    this.setState({open:false})
  }
  render(){
    
    const leaveItems = this.state.requests.map((item) => {
      return (<LeaveRequestItem
      name = {item.employee.ename}
      typeOfLeave = {item.typeOfLeave}
      leaveStart = {item.leaveStart}
      leaveEnd = {item.leaveEnd}
      applicationBody = {item.employeeComment}
      responseBody = {item.managerComment}
      handleClick={this.handleClickOpen}
      status = {item.leaveStatus}
      />);
    });

    return (
      <div style={divStyle}>
      <div style={modalHeader}>
        <h2>Leave Requests</h2>
      </div>
      <Dialog
        open={this.state.open}
        onClose={this.handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          Application
          <span style={this.state.dialogStatus}></span>
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            {this.state.applicationBody}
          </DialogContentText>
        </DialogContent>
        <Divider variant="middle" />
        <DialogTitle>Response</DialogTitle>
        <DialogContent>
          <DialogContentText>
            {
              this.state.responseStatus?
              this.state.responseBody
              :(<textarea rows="6" cols='70' 
              style={{resize:"none",padding:"10px"}}>
              </textarea>)
            }
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={this.handleClose} color="primary" 
          disabled={this.state.responseStatus} autoFocus>
            Approve
          </Button>
          <Button onClick={this.handleClose} color="primary" 
          disabled={this.state.responseStatus}>
            Reject
          </Button>
        </DialogActions>
      </Dialog>
      <Table className={this.classes.table}>
      <TableHead>
      <label style={labelStyle}>Status:</label>
        <select name="leaveStatus" onChange={this.handleFilterChange}
        value={this.state.leaveStatus}>
          <option value={""}>All</option>
          <option value={"NOT_PROCESSED"}>Pending</option>
          <option value={"ACCEPTED"}>Accepted</option>
          <option value={"REJECTED"}>Rejected</option>
          <option value={"REVOKED"}>Revoked</option>
        </select>
        <label style={labelStyle}>Leave Type:</label>
        <select name="typeOfLeave" onChange={this.handleFilterChange}
        value={this.state.leaveStatus}>
          <option value="">All</option>
          <option value="sickLeave">Sick Leave</option>
          <option value="vacationLeave">Vacation</option>
        </select>
        <label style={labelStyle}>Start Date:</label>
        <input type="date" name="startDate" onChange={this.handleFilterChange}
        value={this.state.leaveStatus}/>
        <label style={labelStyle}>End Date:</label>
        <input type="date" name="endDate" onChange={this.handleFilterChange}
        value={this.state.leaveStatus}/>
      </TableHead>
      <TableBody>
        <List className={this.classes.root}>
          {this.state.fetched?leaveItems:<Skeleton variant="rect"/>}
        </List>
        {/* Try the empty rows logic in material ui*/}
      </TableBody>
      <TableFooter>
        <TableRow>
          <TablePagination
          component="nav"
          page={0}
          rowsPerPage={10}
          rowsPerPageOptions={[]}
          count={100}
          onChangePage={handleChangePage}
          />
        </TableRow>
      </TableFooter>
      </Table>
      </div>
    );
  }
}