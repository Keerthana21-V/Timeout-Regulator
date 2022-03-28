package Project.leaveManagement.restController;

import java.util.HashMap;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import Project.leaveManagement.service.LeaveRequestResponseService;

@CrossOrigin
@RestController
@RequestMapping("/email")
public class RestEmailController {

	//@Autowired
	//private LeaveRequestResponseService leaveRequestService;
	
	@PostMapping("/sendToManager")
	public void sendEmailToManager(@RequestBody HashMap<String, Object> map) {
		
	}
}
