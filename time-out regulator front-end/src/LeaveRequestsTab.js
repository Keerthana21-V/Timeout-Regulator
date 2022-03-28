import React from 'react';
import "./resources/css/index.css"
import Inbox from "./Inbox"

const tabStyle = {
    backgroundColor: "#ff9945",
    height: "100%",
    width: "75%",
    padding : "10px",
    marginTop : "-24px",
    marginLeft : "-24px",
    position : "fixed",
};

function LeaveRequestsTab() {
    return (
        <div style={tabStyle}>
            <div>
                <Inbox/>
            </div>
        </div>
    );
}

export default LeaveRequestsTab;