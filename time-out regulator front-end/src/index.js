import React from 'react';
import ReactDOM from 'react-dom';
//import LoginPage from './LoginPage';
//import TeamTable from './TeamTable';
import "./resources/css/index.css"
import * as serviceWorker from './serviceWorker';
//import Home from './Home';
//import Inbox from './Inbox';
//import ManagerLogin from './ManagerLogin';
//import LeaveRequestsTab from './LeaveRequestsTab';
//import DisplayRequestModal from "./DisplayRequestModal"
//import NavTabs from './NavTabs';
//import EmployeeDetails from './EmployeeDetails';
//import ManagerNavTabs from './ManagerNavTabs';
import App from "./App"

ReactDOM.render(<App/>, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
