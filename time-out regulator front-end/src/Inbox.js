import React,{Component} from 'react';
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

import getUrl from "./getUrl"
const url = getUrl();

function useStyles(){
  return {
    root: {
      width: '100%',
    },
    inline: {
      display: 'inline',
    }
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


export default class Inbox extends Component {

  constructor(){
    super()
    this.classes = useStyles();
    this.state = {
      open : false,
      requests : [],
    }
    this.handleClickOpen = this.handleClickOpen.bind(this);
    this.handleClose = this.handleClose.bind(this);
  }

  handleClickOpen() {
    this.setState({
      open : true
    })
  }
  
  handleClose() {
    this.setState({open : false})
  }

  componentDidMount(){
    //alert("Hello");
    const obj = {
        eid : 1025,
    }
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = () => {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            const data = JSON.parse(xhttp.response);
            console.log(data.length,data);
            this.setState({
              requests : data[0].leaveRequests,
            })
        }
    };
    xhttp.open("POST",url+"/home/getAppliedLeaves", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(obj));
  }

  render(){

    const leaveItems = this.state.requests.map((item) => {
      return (<LeaveRequestItem
      handleClick={this.handleClickOpen}
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
          <span style={closeStyle}></span>
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            Let Google help apps determine location. This means sending anonymous location data to
            Google, even when no apps are running.
          </DialogContentText>
        </DialogContent>
        <Divider variant="middle" />
        <DialogTitle>Response</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Let Google help apps determine location. This means sending anonymous location data to
            Google, even when no apps are running.
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={this.handleClose} color="primary">
            Disagree
          </Button>
          <Button onClick={this.handleClose} color="primary" autoFocus>
            Agree
          </Button>
        </DialogActions>
      </Dialog>
      <Table className={this.classes.table}>
      <TableHead>
      <label style={labelStyle}>Status:</label>
        <select>
          <option>All</option>
          <option>Pending</option>
          <option>Accepted</option>
        </select>
        <label style={labelStyle}>Leave Type:</label>
        <select>
          <option>All</option>
          <option>Sick Leave</option>
          <option>Vacation</option>
        </select>
        <label style={labelStyle}>Start Date:</label>
        <input type="date"/>
        <label style={labelStyle}>End Date:</label>
        <input type="date"/>
      </TableHead>
      <TableBody>
        <List className={this.classes.root}>
          {leaveItems}
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