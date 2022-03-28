package Project.leaveManagement.dao;

import java.sql.Date;
import java.util.List;

import Project.leaveManagement.entity.LeaveRequests;
import Project.leaveManagement.entity.LeaveStatus;

public interface ManagerDAO {

	public List<LeaveRequests> getAllLeaveRequests(int manager_id, LeaveStatus status, Date leaveStart, Date leaveEnd, String typeOfLeave);
}
