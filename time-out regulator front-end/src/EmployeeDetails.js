import React,{Component} from "react"
import profileImg from  "./resources/images/img.png"
import "./resources/css/Home.css"

import getUrl from "./getUrl"

const url = getUrl();

class EmployeeDetails extends Component
{

    constructor() {
        super();
        this.state = {
            SessionId : "",
            employee : {}
        }
    }

    componentDidMount(){

        const obj = {
            SessionId : this.props.SessionId,
        }

        this.setState({
            SessionId : this.props.SessionId,
        })
        alert(this.props.SessionId);
        const xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = () => {
            if (xhttp.readyState === 4 && xhttp.status === 200) {
                const data = JSON.parse(xhttp.response);
                this.setState({
                    employee : data,
                })
            }
        };
        xhttp.open("POST",url+"/home/getEmployee/1018", true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send(JSON.stringify(obj));
    }

    render(){
        const {employee} = this.state; 
        return(
            <div>
            <div className='sidenav' style={{height: "100%",
            width: "100%",
            overflowX: "hidden",
            paddingTop: "50px",
            textAlign: "center",
            display : "inline",
        }}>
                <div className="profileCard" style={{
            maxWidth: "300px",
            margin: "auto",
            textAlign: "center",
            color : "black",
            backgroundColor: "white",
            padding: "10px",
            marginTop: "20%"
        }}>
                
                <img src={profileImg} style={{width:"100%",borderRadius:"50%"}} />
                    <h1>{employee.ename}</h1>
                    <p style={{fontSize:"20px"}}>{employee.email}</p> 
                    <p><button style={{
                        border: "none",
                        outline: "0",
                        display: "inline-block",
                        padding: "10px",
                        color: "white",
                        backgroundColor: "#dd7723",
                        textAlign: "center",
                        cursor: "pointer",
                        width: "100%",
                        fontSize: "26px",
                        boxShadow: "0 4px 8px 0 rgba(0, 0, 0, 0.2)"
                        
                    } 
                    } id='myBtn'>
                        Logout
                    </button></p>
                </div>
            </div>
            </div>
        )
    }
}

export default EmployeeDetails;
