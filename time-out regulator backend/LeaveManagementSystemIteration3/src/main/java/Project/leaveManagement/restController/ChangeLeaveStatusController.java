package Project.leaveManagement.restController;

import java.sql.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Project.leaveManagement.service.ChangeLeaveService;

@CrossOrigin
@RestController
@RequestMapping("/change")
public class ChangeLeaveStatusController {

	@Autowired
	private ChangeLeaveService changeLeaveService;
	
	@PostMapping("/revoke/{leaveId}")
	public boolean revokeLeave(@PathVariable int leaveId) {
		return changeLeaveService.revoke(leaveId);
	}
	
	@PostMapping("/extend")
	public void extendLeave(@RequestBody HashMap<String, Object> map) {
		int leaveId = (Integer) map.get("leaveId");
		String endDate = (String) map.get("leaveEnd");
		String empComment = (String) map.get("empComment");
		Date leaveEnd = null;
		if(endDate!=null)
			leaveEnd = Date.valueOf(endDate);
		
		changeLeaveService.extendLeave(leaveId, leaveEnd, empComment);
	}
}
