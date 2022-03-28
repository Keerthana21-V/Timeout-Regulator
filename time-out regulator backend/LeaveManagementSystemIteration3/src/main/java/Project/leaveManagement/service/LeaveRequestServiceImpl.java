package Project.leaveManagement.service;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Project.leaveManagement.dao.HolidaysDAO;
import Project.leaveManagement.dao.LeaveResponseDAO;
import Project.leaveManagement.dao.UpdationOfEmployeeDAO;
import Project.leaveManagement.entity.LeaveRequests;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestResponseService {


	@Autowired
	private UpdationOfEmployeeDAO updateEmployeeDAO;
	
	@Autowired
	private LeaveResponseDAO leaveResponseDAO;
	
	@Autowired
	private HolidaysDAO holidaysDAO;
	
	public boolean approveLeave(int leaveId, String managerComment) {
		LeaveRequests lr = leaveResponseDAO.approveLeave(leaveId, managerComment);
		int noOfLeaves = calculateNoOfLeaves(lr.getLeaveStart(), lr.getLeaveEnd());
		updateEmployeeDAO.updateEmpLeaveDetails(lr.getEmployee().getEid(), noOfLeaves);
		return true;
	}

	public boolean rejectLeave(int leaveId, String managerComment) {
		leaveResponseDAO.rejectLeave(leaveId, managerComment);
		return true;
	}

	public int calculateNoOfLeaves(Date startDate, Date endDate) {
		int nonWeekendHolidays = calculateNonWeekendHolidays(startDate, endDate);
		int workingDaysExcludingWeekends = calculateWorkingDaysExcludingWeekends(startDate, endDate);
		
		return workingDaysExcludingWeekends-nonWeekendHolidays;
	}

	public int calculateNonWeekendHolidays(Date startDate, Date endDate) {
		return holidaysDAO.calculateWorkingDaysExcludingWeekends(startDate, endDate);
	}

	public int calculateWorkingDaysExcludingWeekends(Date startDate, Date endDate) {
		int workingDaysExcludingWeekends=0;
		
		Calendar c1 = Calendar.getInstance();
		c1.setTime(startDate);
		
		Calendar c2 = Calendar.getInstance();
		c2.setTime(endDate);
//		if(c1.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY || c1.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY ||
//				c2.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY || c2.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
//			workingDaysExcludingWeekends=0;
		do{
			if(c1.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY && c1.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY)
				workingDaysExcludingWeekends++;
			c1.add(Calendar.DATE, 1);
				
		}while(c2.compareTo(c1)>=0);
		return workingDaysExcludingWeekends;
	}
	
}
