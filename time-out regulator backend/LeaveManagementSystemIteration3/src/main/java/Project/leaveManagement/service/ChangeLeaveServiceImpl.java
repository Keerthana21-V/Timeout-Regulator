package Project.leaveManagement.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Project.leaveManagement.dao.ChangeLeaveDAO;
import Project.leaveManagement.entity.LeaveRequests;

@Service
public class ChangeLeaveServiceImpl implements ChangeLeaveService {

	@Autowired
	private ChangeLeaveDAO changeLeave;
	
	public boolean revoke(int leaveId) {
		return changeLeave.revoke(leaveId);
	}

	public int extendLeave(int leaveId, Date leaveEnd, String empComment) {
		LeaveRequests lr = changeLeave.getLeaveRequestFromLeaveID(leaveId);
		long millis=System.currentTimeMillis();
		Date currDate=new Date(millis);
		int eid = lr.getEmployee().getEid();
		if(currDate.after(lr.getLeaveStart()) && currDate.before(lr.getLeaveEnd())) {
			return changeLeave.createLeaveRequest(eid, currDate, lr.getLeaveEnd(), leaveEnd, lr.getTypeOfLeave(), empComment);
		}
		changeLeave.extendLeave(leaveId, currDate, leaveEnd, empComment);
		return lr.getLeaveId();
	}

}
