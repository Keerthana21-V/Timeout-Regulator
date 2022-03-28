package Project.leaveManagement.restController;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Project.leaveManagement.dao.LeaveRequestDAO;
import Project.leaveManagement.service.LeaveRequestResponseService;
import Project.leaveManagement.session.DummySession;
import Project.leaveManagement.session.SessionManager;

@CrossOrigin
@RestController
@RequestMapping("/response")
public class ResponseController {
	
	@Autowired
	private LeaveRequestResponseService leaveResponseService;
	
	@Autowired
	private LeaveRequestDAO leaveRequestDAO;
	
	public boolean validateRequest(
			HashMap<String, String> leaveData) {
		Integer leaveId = Integer.parseInt(leaveData.get("leaveId"));
		String responseBody = (String)leaveData.get("responseBody");
		
		String sessionId = (String)leaveData.get("SessionId");
		DummySession d = SessionManager.getSession(sessionId);
		Integer managerId = (Integer)d.getAttribute("managerId");
		
		return leaveRequestDAO.checkLeave(managerId, leaveId);
	}
	
	@PostMapping("/approve")
	public boolean approveLeave(
			@RequestBody HashMap<String, String> leaveData) {
		Integer leaveId = Integer.parseInt(leaveData.get("leaveId"));
		String responseBody = (String)leaveData.get("responseBody");
		
		/*boolean flag = validateRequest(leaveData);
		System.out.println(">>>>>>>>>>>>>HERE"+flag);*/
		//if(flag)
			return leaveResponseService.approveLeave(
					leaveId, responseBody);
		//return false;
	}
	
	@PostMapping("/reject")
	public boolean rejectLeave(
			@RequestBody HashMap<String, String> leaveData) {
		Integer leaveId = Integer.parseInt(leaveData.get("leaveId"));
		String responseBody = (String)leaveData.get("responseBody");
		
		//if(validateRequest(leaveData))
			return leaveResponseService.rejectLeave(
					leaveId, responseBody);
		//return false;
	}
	
}
