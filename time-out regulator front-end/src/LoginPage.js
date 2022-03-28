import React from 'react';
import "./resources/css/loginStyle.css"

import getUrl from "./getUrl"
//import { browserHistory } from 'react-router'

const url = getUrl();

class LoginPage extends React.Component {

	constructor(){
		super();
		this.state = {
			username : "",
			password : "",
		}
		this.handleChange = this.handleChange.bind(this)
		this.handleLogin = this.handleLogin.bind(this)
	}

	handleChange(event){
		const {name,value} = event.target;
		this.setState({
			[name] : value
		})
	}

	handleLogin(event){
		event.preventDefault();	

		const obj = {
			username : this.state.username,
			password : this.state.password
		  }
		  //alert("Hello");
		  const xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = () => {
			  //alert("Hello");
			  if (xhttp.readyState === 4 && xhttp.status === 200) {
					const data = JSON.parse(xhttp.response);
					//alert("Hello");
					console.log(data);
					this.props.history.replace({
						pathname: "/home",
						state: {
							SessionId: data,
						}
					})
			  }
		  };
		  xhttp.open("POST",url+"/performLogin", true);
		  xhttp.setRequestHeader("Content-type", "application/json");
		  xhttp.send(JSON.stringify(obj));
	}

	render(){
		return (
			<div className="mainbody">
			<div className="login">
					<h1>Login</h1>
					<form name='f' onSubmit={this.handleLogin}> 
						<label htmlFor="username">
							<i className="fas fa-user"></i>
						</label>
						<input type="text" name="username" 
						onChange={this.handleChange} 
						placeholder="Username" id="username" 
						value={this.state.username} required/>
						<label htmlFor="password">
							<i className="fas fa-lock"></i>
						</label>
						<input type="password" name="password" 
						onChange={this.handleChange}
						placeholder="Password" id="password" 
						value={this.state.password} required/>
						<input type="submit" value="Login"/>
					</form>
				</div>
				</div>
		);
	}
}

export default LoginPage;
