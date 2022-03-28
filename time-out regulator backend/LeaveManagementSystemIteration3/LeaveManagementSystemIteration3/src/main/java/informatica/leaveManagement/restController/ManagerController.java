package Project.leaveManagement.restController;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Project.leaveManagement.entity.Employee;
import Project.leaveManagement.entity.LeaveRequests;
import Project.leaveManagement.entity.LeaveStatus;
import Project.leaveManagement.service.ManagerService;
import Project.leaveManagement.session.DummySession;
import Project.leaveManagement.session.SessionManager;
@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;
	
	@PostMapping("/getHistory")
	public List<LeaveRequests> getHistory(
			@RequestBody HashMap<String, Object> filters){
		
		String sessionId = (String)filters.get("SessionId");
		DummySession d = SessionManager.getSession(sessionId);
		
		int manager_id = (Integer) d.getAttribute("managerId");
		LeaveStatus leaveStatus = null;
		
		Date leaveStart = null;
		Date leaveEnd = null;
		String status = (String) filters.get("leaveStatus");
		String startString = (String) filters.get("startDate");
		String endString = (String) filters.get("endDate");
		String typeOfLeave = (String) filters.get("typeOfLeave");
		
		if(startString!=null)
			leaveStart = Date.valueOf(startString);
		if(endString!=null)
			leaveEnd = Date.valueOf(endString);
		if(status!=null)
			leaveStatus = LeaveStatus.valueOf(status);
		
		return managerService.getAllLeaveRequests(manager_id, leaveStatus, leaveStart, leaveEnd, typeOfLeave);
	}
	
	@PostMapping("/getTeamList")
	public List<Employee> getTeamList(
			@RequestBody HashMap<String, Object> filters){
		String sessionId = (String)filters.get("SessionId");
		DummySession d = SessionManager.getSession(sessionId);
		int manager_id = (Integer) d.getAttribute("managerId");
		return managerService.getTeamDetails(manager_id);
	}
}
