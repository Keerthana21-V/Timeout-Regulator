package Project.leaveManagement.restController;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Project.leaveManagement.entity.Employee;
import Project.leaveManagement.entity.LeaveStatus;
import Project.leaveManagement.service.EmployeeService;
import Project.leaveManagement.session.DummySession;
import Project.leaveManagement.session.SessionManager;

@CrossOrigin
@RestController
@RequestMapping("/home")
public class RestHomeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/getEmployee")
	public Employee getEmployee(@RequestBody HashMap<String,Object> hmap) {
		
		String sessionId = (String)hmap.get("SessionId");
		DummySession d = SessionManager.getSession(sessionId);
		System.out.println(">>>>>>>>HERE"+d.getAttribute("eid"));
		int eid=(Integer) d.getAttribute("eid");
		System.out.println(eid);
		//System.out.println(d.getAttribute("employee"));
		
		//System.out.println( employeeService.getEmployee(eid));
		return employeeService.getEmployee(eid);
	}
	
	@PostMapping("/getTeamList")
	public List<Employee> getTeamList(@RequestBody HashMap<String,Object> hmap){
		String sessionId = (String)hmap.get("SessionId");
		DummySession d = SessionManager.getSession(sessionId);
		System.out.println(">>>>>>>>Hello>>>>>>>>"+d.getAttribute("eid"));
		int eid=(Integer) d.getAttribute("eid");
		System.out.println(eid);
		
		return employeeService.getTeamDetails(eid);
	}
	
	@PostMapping("/getAppliedLeaves")
	public List<Employee> getAppliedLeaves(@RequestBody HashMap<String, Object> filters){
		String status = (String) filters.get("status");
		System.out.println((String) filters.get("status"));
		int eid = (Integer) filters.get("eid");
		String typeOfLeave = (String) filters.get("typeOfLeave");
		Date startDate = null;
		Date endDate = null;
		LeaveStatus leaveStatus = null;
		System.out.println(eid);
		String startString = (String) filters.get("startDate");
		String endString = (String) filters.get("endDate");
		
		if(startString!=null)
			startDate = Date.valueOf(startString);
		if(endString!=null)
			endDate = Date.valueOf(endString);
		if(status!=null)
			leaveStatus = LeaveStatus.valueOf(status);
		return employeeService.getAppliedLeaves(eid, leaveStatus, startDate, endDate, typeOfLeave);
		
	}
	@PostMapping("/logout")
	public void  logout(@RequestBody HashMap<String, Object> hmap)
	{
		String session=(String) hmap.get("SessionId");
		 SessionManager.deleteSession(session);
		
	}
}
