package Project.leaveManagement.service;

import java.sql.Date;

public interface LeaveRequestResponseService {

	public boolean approveLeave(int leaveId, String managerComment);
	public boolean rejectLeave(int leaveId, String managerComment);
	public int calculateNoOfLeaves(Date startDate, Date endDate);
	public int calculateNonWeekendHolidays(Date startDate, Date endDate);
	public int calculateWorkingDaysExcludingWeekends(Date startDate, Date endDate);
}
