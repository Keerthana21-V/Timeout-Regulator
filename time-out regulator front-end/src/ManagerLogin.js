import React from 'react';
import "./resources/css/managerLoginPage.css"

function ManagerLogin() {
  return (
    <div className="mainbody managerContainer">
    <div class="login-page">
        <div class="form">
            <form class="login-form"
                action='perform_login' method='POST'>
            <input type="text" placeholder="username" name="username"/>
            <input type="password" placeholder="password" name="password"/>
            <button type="submit">login</button>
            </form>
        </div>
    </div>
    </div>
  );
}

export default ManagerLogin;
