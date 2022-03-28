package Project.leaveManagement.dao;

import java.sql.Date;

public interface LeaveRequestDAO {

	public void createLeaveRequest(int eid, 
			Date leaveStart, Date leaveEnd, 
			String typeOfLeave, String employeeComment);
	public boolean checkLeave(int managerId,int leaveId);
}
