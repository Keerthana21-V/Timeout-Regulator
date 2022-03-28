package Project.leaveManagement.restController;

import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Project.leaveManagement.session.SessionManager;

@CrossOrigin
@RestController
public class LogoutController {
	
	@PostMapping("/performLogout")
	public boolean doLogin(
			@RequestBody HashMap<String, Object> hmap) {
		String sessionId = (String)hmap.get("SessionId");
		System.out.println(">>>>>>>>>>SESSIONID "+sessionId);
		return SessionManager.deleteSession(sessionId);
	}

}
