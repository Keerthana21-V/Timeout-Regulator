package Project.leaveManagement.dao;

import java.sql.Date;

import Project.leaveManagement.entity.LeaveRequests;

public interface ChangeLeaveDAO {

	public boolean revoke(int leaveId);
	public LeaveRequests getLeaveRequestFromLeaveID(int leaveId);
	public int createLeaveRequest(int eid, Date appliedDate, Date startDate, Date endDate, String type, String empComment);
	public boolean extendLeave(int leaveId, Date appliedDate, Date endDate, String empComment);
}
