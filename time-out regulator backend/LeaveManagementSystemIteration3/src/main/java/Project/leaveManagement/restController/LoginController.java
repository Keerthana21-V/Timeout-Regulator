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
public class LoginController {
	
	@Autowired
	private EmployeeDetailsDAO employeeDetailsDAO;
	
	@PostMapping("/performLogin")
	public String doLogin(
			@RequestBody HashMap<String, Object> userDetails) {
		
		String username = (String)userDetails.get("username");
		String password = (String)userDetails.get("password");
		//String username = "gouvoty";
		//String password = "Goutham@oty";
		
		Employee e = employeeDetailsDAO
				.getEmployeeFromUsername(username);
		//System.out.println(e);
		if(e!=null)
		{
		if(e.getPasswd().equals(password)) {
			
			//HttpSession session = request.getSession(true);
			String sessionId = SessionManager.createSession();
			DummySession d = SessionManager.getSession(sessionId);
			
			d.setAttribute("eid",e.getEid());
			d.setAttribute("mid",e.getManager_id());
			//d.setAttribute("employee",e);
			
			SessionManager.storeSession(d);
			System.out.println(sessionId);
			return sessionId;
		}
		else
			return "hello";
		}
		else
		{
			return "hello";
		}

	}
	
}
