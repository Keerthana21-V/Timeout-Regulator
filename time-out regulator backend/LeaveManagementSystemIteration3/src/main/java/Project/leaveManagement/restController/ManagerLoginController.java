package Project.leaveManagement.restController;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Project.leaveManagement.dao.EmployeeDetailsDAO;
import Project.leaveManagement.entity.Employee;
import Project.leaveManagement.session.DummySession;
import Project.leaveManagement.session.SessionManager;

@CrossOrigin
@RestController
public class ManagerLoginController {
	
	@Autowired
	private EmployeeDetailsDAO employeeDetailsDAO;
	
	@PostMapping("/performManagerLogin")
	public String doLogin(
			@RequestBody HashMap<String, Object> userDetails) {
		
		String username = (String)userDetails.get("username");
		String password = (String)userDetails.get("password");
		
		Employee e = employeeDetailsDAO
				.getEmployeeFromUsername(username);
		
		if(e.getPasswd().equals(password)) {
			
			String sessionId = SessionManager.createSession();
			DummySession d = SessionManager.getSession(sessionId);
			
			d.setAttribute("managerId",e.getEid());
			
			SessionManager.storeSession(d);
			System.out.println(sessionId);
			return sessionId;
		}
		else
			return "";
	}
	
}
