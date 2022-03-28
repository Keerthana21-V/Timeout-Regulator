package Project.leaveManagement.restController;

import java.sql.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Project.leaveManagement.dao.LeaveRequestDAO;
import Project.leaveManagement.session.DummySession;
import Project.leaveManagement.session.SessionManager;

@CrossOrigin
@RestController
@RequestMapping("/request")
public class RequestController {

	@Autowired
	private LeaveRequestDAO leaveRequestDAO;
	
	@PostMapping("/requestLeave")
	public boolean requestLeave(@RequestBody HashMap<String, String> leaveData) {
		try {
			String Sessionid = leaveData.get("sessionid");
			DummySession d = SessionManager.getSession(Sessionid);
			System.out.println(">>>>>>>>HERE"+d.getAttribute("eid"));
			int eid=(Integer) d.getAttribute("eid");

			String typeOfLeave = leaveData.get("typeOfLeave");
			Date leaveStart = Date.valueOf(leaveData.get("leaveStart"));
			Date leaveEnd = Date.valueOf(leaveData.get("leaveEnd"));
			String employeeComment = leaveData.get("employeeComment");
			
			leaveRequestDAO.createLeaveRequest(eid, leaveStart, leaveEnd, typeOfLeave, employeeComment);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
