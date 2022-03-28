import React,{Component} from 'react';
import { type } from 'os';

class LeaveForm extends Component{
    constructor()
    {
        super();
        this.state = {
          SessionId : "",
        }
    }
    componentDidMount()
    {
        const sessionId = this.props.location.state.SessionId;

        const obj = {
          SessionId : sessionId,
        }

        this.setState({
          SessionId : sessionId,
        })

        var xhttp=new XMLHttpRequest();
        xhttp.onreadystatechange=function(){
            if(this.readyState == 4 && this.status == 200)
            {
            alert(this.responseText)
            var myarr=JSON.parse(xhttp.response);
            alert(myarr.email)
            
            alert(myarr.leaveDetails.vacationLeaves);
            document.getElementById("email").value=myarr.email;
            
            //leave.value=myarr.leaveDetails.vacationLeaves;
            }
        
        }
        xhttp.open("POST","http://localhost:8080/LeaveManagementSystemIteration3/home/getEmployee/1019",true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send(JSON.stringify(obj));
    
    
    }
    
    render(){
  const button= {
    textAlign: "right",
    padding: "15px 32px",
    textDecoration: "none",
    display: "inline-block",
    fontSize: "16px",
  marginLeft: "200px"
  }
  const enddate={marginLeft: "150px"}
  
  return (
    
      <form>
        <div style={{backgroundColor:"#ADD8E6"}}><h1>Leave Application Form</h1></div>
 <div > <br/><p><label>To: </label>
  
  <input id="email" class="w3-input" type="text"  name="to" readonly/></p><br/></div>
 
  <p><label>Type of Leave: </label><select id="leave_type" name="leaveType" class="custom-select mb-3">
      <option value="vacationLeave" selected>vacation leave</option>
      <option value="sickLeave" >sick leave</option>
      </select></p><br/>
  
  <label>Start Date: </label>
  <input id="start_date" type="date" name="start_date"/>
  
  <label style={enddate} class="end-date">End Date: </label>
  <input  id="end_date" type="date" name="End_date" />
  
  <br/><br/>
  <label>Body:</label><br/>
  <br/><textarea name="message" rows="10" cols="80" placeholder="Mention your reason..." id="body"></textarea>
  <br/><br/>
 <button style={button} type="button" onClick={function(){
   if(document.getElementById("start_date").value=="" && document.getElementById("end_date").value=="")
   {
     alert("all fields must be entered")
   }
   else if(document.getElementById("start_date").value>document.getElementById("end_date").value)
   {
     alert("Leave start date is greater than leave end date")
   }
   else if(document.getElementById("leave_type").value=="sickLeave")
   {
     alert("hello");
     var today = new Date();
   
     var date = new Date(today),
    mnth = ("0" + (date.getMonth() + 1)).slice(-2),
    day = ("0" + date.getDate()).slice(-2);
  //alert([date.getFullYear(), mnth, day].join("-"));
    // alert(document.getElementById("start_date").value);
     if(document.getElementById("start_date").value>[date.getFullYear(), mnth, day].join("-")  )
     {
       alert("wrong date");
     }
     else{
      alert("hi");
     var xhttp = new XMLHttpRequest();
     //var e = document.getElementById("leave_type");
     var obj={
       "eid":"1019",
       "typeOfLeave":document.getElementById("leave_type").value,
       "leaveStart":document.getElementById("start_date").value,
       "leaveEnd":document.getElementById("end_date").value,
       "employeeComment":document.getElementById("body").value,
     };
     alert(obj.leaveStart);
     xhttp.onreadystatechange=function(){
       if(this.readyState == 4 && this.status == 200)
         {
         alert("hi");
   }
       
     }
     xhttp.open("POST","http://localhost:8080/LeaveManagementSystemIteration3/request/requestLeave",true);
     xhttp.setRequestHeader("Content-type", "application/json");
     xhttp.send(JSON.stringify(obj));
 
    }

   
   
   }
   else if(document.getElementById("leave_type").value=="vacationLeave")
   {
    var today = new Date();
   
    var date = new Date(today),
   mnth = ("0" + (date.getMonth() + 1)).slice(-2),
   day = ("0" + date.getDate()).slice(-2);
 //alert([date.getFullYear(), mnth, day].join("-"));
   // alert(document.getElementById("start_date").value);
    if(document.getElementById("start_date").value<[date.getFullYear(), mnth, day].join("-")  )
    {
      alert("wrong date");
    }
    else{
      alert("hi");
     var xhttp = new XMLHttpRequest();
     //var e = document.getElementById("leave_type");
     var obj={
       "eid":"1019",
       "typeOfLeave":document.getElementById("leave_type").value,
       "leaveStart":document.getElementById("start_date").value,
       "leaveEnd":document.getElementById("end_date").value,
       "employeeComment":document.getElementById("body").value,
     };
     alert(obj.leaveStart);
     xhttp.onreadystatechange=function(){
       if(this.readyState == 4 && this.status == 200)
         {
         alert("hi");
   }
       
     }
     xhttp.open("POST","http://localhost:8080/LeaveManagementSystemIteration3/request/requestLeave",true);
     xhttp.setRequestHeader("Content-type", "application/json");
     xhttp.send(JSON.stringify(obj));
 
    }

   }
   
  }} 
   value="send" class="form-submit submitButton">send</button>
</form>

    
  );
}
}

export default LeaveForm;
