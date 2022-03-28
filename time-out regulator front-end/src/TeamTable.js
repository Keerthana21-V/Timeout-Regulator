import React,{Component} from 'react';
import "./resources/css/modalBox.css"

import getUrl from "./getUrl"
const url = getUrl();

class TeamTable extends Component{
  constructor()
  {
    super();
  }
 componentDidMount(){
   
  const obj = {
    SessionId : this.props.SessionId,
  }

  this.setState({
      SessionId : this.props.SessionId,
  })

	var xhttp=new XMLHttpRequest();
	xhttp.onreadystatechange=function(){

    if(this.readyState == 4 && this.status == 200)
		{
      var myarr=JSON.parse(xhttp.responseText);
      alert(xhttp.responseText)
      alert("hello")
      alert(myarr[0].leaveRequests)
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
            cell2.innerHTML=myarr[i].leaveRequests==""||myarr[i].leaveRequests[0]==true?"yes":"no";
        }
        row = table.insertRow(-1);
        var cell = row.insertCell(-1);
        cell.innerHTML = "-";
        
        var cell2 = row.insertCell(-1);
        cell2.innerHTML="-";

        var dvTable = document.getElementById("showData");
        dvTable.innerHTML = "";
        dvTable.appendChild(table);
    }


		//
		//var email=document.getElementById("email");
		//email.value=myarr.email;
		}
	
	
	xhttp.open("POST",url+"/home/getTeamList/1018",true);
	xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.send(JSON.stringify(obj));

  }
  render()
  {
  return (
    <div className="modal-content" >
        <div className="modal-header">
            <h2>Team Members</h2>
        </div>
        <div className="modal-body" id="showData">
            
        </div> 
    </div>
  );
}
}

export default TeamTable;