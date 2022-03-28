package Project.leaveManagement.dao;

import Project.leaveManagement.entity.LeaveRequests;

public interface LeaveResponseDAO {

	public LeaveRequests approveLeave(int leaveId, String managerComment);
	public void rejectLeave(int leaveId, String managerComment);
}
