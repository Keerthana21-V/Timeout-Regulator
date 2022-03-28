package Project.leaveManagement.dao;

import java.sql.Date;

public interface HolidaysDAO {
	public int calculateWorkingDaysExcludingWeekends(Date sqlStart, Date sqlEnd);
}
