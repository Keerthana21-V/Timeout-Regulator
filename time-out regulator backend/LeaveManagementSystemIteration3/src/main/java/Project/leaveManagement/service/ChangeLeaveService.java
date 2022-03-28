package Project.leaveManagement.service;

import java.sql.Date;

public interface ChangeLeaveService {
	public boolean revoke(int leaveId);
	public int extendLeave(int leaveId, Date leaveEnd, String empComment);
}
