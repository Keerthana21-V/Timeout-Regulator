import React from "react";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

import LoginPage from "./LoginPage"
import ManagerLogin from "./ManagerLogin"
import Home from './Home';
//import Inbox from './Inbox';
//import ManagerLogin from './ManagerLogin';
//import LeaveRequestsTab from './LeaveRequestsTab';
//import DisplayRequestModal from "./DisplayRequestModal"
//import NavTabs from './NavTabs';
//import EmployeeDetails from './EmployeeDetails';
import ManagerNavTabs from './ManagerNavTabs';
import LeaveForm from "./LeaveForm";

function BasicExample() {
  return (
    <Router>
        <Route exact path="/" component={LoginPage} />
        <Route exact path="/manager" component={ManagerLogin} />
        <Route exact path="/home" component={Home} />
        <Route exact path="/home/leaveform" component={LeaveForm} />
        <Route exact path="/manager/home" component={ManagerNavTabs}/>
    </Router>
  );
}

/*
<div>
        <ul>
          <li>
            <Link to="/employee">Employee</Link>
          </li>
          <li>
            <Link to="/manager">Manager</Link>
          </li>
        </ul>
        <hr />
</div>
*/

export default BasicExample;
