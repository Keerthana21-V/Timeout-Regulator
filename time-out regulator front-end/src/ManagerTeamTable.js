import React,{Component} from 'react';
import "./resources/css/modalBox.css"

class ManagerTeamTable extends Component {
  componentDidMount(){
    
    var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange=function(){
      
      
      if(this.readyState == 4 && this.status == 200)
      {
        var myarr=JSON.parse(xhttp.responseText);
        alert(xhttp.responseText)
        alert("hello")
       // alert(myarr[2].leaveRequests[0].available)
        var table = document.createElement("TABLE");
        var row = table.insertRow(-1);
        var heading=["name","present"];
        for(var i=0;i<heading.length;i++)
        {
        var headerCell = document.createElement("TH");
        headerCell.innerHTML = heading[i];
       row.appendChild(headerCell);
        }
        for (var i = 0; i < myarr.length; i++) {
          row = table.insertRow(-1);
              var cell = row.insertCell(-1);
              cell.innerHTML = myarr[i].ename;
              var cell2 = row.insertCell(-1);
              cell2.innerHTML=myarr[i].leaveRequests==""|| myarr[i].leaveRequests[0].available?"YES":"NO";
          }
          var dvTable = document.getElementById("showData");
          dvTable.innerHTML = "";
          dvTable.appendChild(table);
      }
  
  
      //
      //var email=document.getElementById("email");
      //email.value=myarr.email;
      }
    
    
    xhttp.open("GET","http://localhost:8080/LeaveManagementSystemIteration3/home/getTeamUnderManager/1021",true);
    //xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
  
    }
    render(){
  return (
    <div className="modal-content" style={{width:"75%"}}>
        <div className="modal-header">
            <h2>Team Members</h2>
        </div>
        <div className="modal-body" id="showData">
            
       </div> 
    </div>
  );
    }
}

export default ManagerTeamTable;