import React from 'react';
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';

import TeamTable from "./TeamTable";
import LeaveRequestsTab from './LeaveRequestsTab';
import { blockStatement } from '@babel/types';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <Typography
      component="div"
      role="tabpanel"
      hidden={value !== index}
      id={`nav-tabpanel-${index}`}
      aria-labelledby={`nav-tab-${index}`}
      {...other}
    >
      <Box p={3}>{children}</Box>
    </Typography>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.any.isRequired,
  value: PropTypes.any.isRequired,
};

function a11yProps(index) {
  return {
    id: `nav-tab-${index}`,
    'aria-controls': `nav-tabpanel-${index}`,
  };
}

function LinkTab(props) {
  return (
    <Tab
      component="a"
      onClick={event => {
        event.preventDefault();
      }}
      {...props}
    />
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1,
    backgroundColor: theme.palette.background.paper,
  },
}));

export default function NavTabs(props) {
  const classes = useStyles();
  const [value, setValue] = React.useState(0);

  function handleChange(event, newValue) {
    setValue(newValue);
  }

  function handlePageChange(){
    alert(JSON.stringify(props.SessionId));
  }
  const leaveColumn={float: "right",
  width: "40%",
  marginRight: "10%",
  fontSize:"20px"

  }
  const leaveRow={
    margin:"auto",
    marginTop : "20px",
    content: "",
  display: "table",
  clear: "both"

  }
  const leaveCard={boxShadow:" 0 4px 8px 0 rgba(0, 0, 0, 0.2)",
  padding:"20px",
  textAlign: "center",
  width:"100%",
  backgroundColor: "#f1f1f1"}
  const leaveNum={fontFamily:"Calibri",
  fontSize:"20px"}

  return (
    <div className={[classes.root]}>
      <AppBar position="static">
        <Tabs
          variant="fullWidth"
          value={value}
          onChange={handleChange}
          aria-label="nav tabs example"
        >
          <LinkTab label="leave details" href="/drafts" {...a11yProps(0)} />
          <LinkTab label="history of leaves" href="/trash" {...a11yProps(1)} />
        </Tabs>
      </AppBar>
      <TabPanel value={value} index={0}>
     
    <div style={{backgroundColor: "#ff9945",
    height: "100%",
    width: "75%",
    padding : "10px",
    marginTop : "-24px",
    marginLeft : "-24px",
    position : "fixed",
    }}>
        <TeamTable SessionId={props.SessionId}/>
        <div class="leaveRow" style={leaveRow}>
        <div class="leaveColumn" style={leaveColumn}>
            <div class="leaveCard" style={leaveCard}>
              <h1 class="leaveNum" style={leaveNum}>10</h1>
              <p>Paid Leaves</p>
            </div>
        </div>
        <div class="leaveColumn" style={leaveColumn}>
            <div class="leaveCard" style={leaveCard}>
              <h1 class="leaveNum" style={leaveNum}>15</h1>
              <p>Loss of Pay</p>
            </div>
        </div>
        
        </div>
        
        <Link to={{
          pathname :"/home/leaveform",
          state : {
            SessionId : props.SessionId,
          }
        }}
        style={{backgroundColor: "#46679f",
          width: "20%",
          padding: "15px",
          fontSize: "22px",
          margin: "auto",
          textDecoration : "none",
          color : "white",
          display : "block",
          marginTop : "10px",
          boxShadow: "5px 5px 18px #888888",
          textAlign : "center",
        }}>
            Apply Leave
        </Link>
        
    </div>

      </TabPanel>
      <TabPanel value={value} index={1}>
        <LeaveRequestsTab/>
      </TabPanel>
      
    </div>
  );
}
