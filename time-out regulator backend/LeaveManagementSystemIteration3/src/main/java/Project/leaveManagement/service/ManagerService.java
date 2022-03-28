package Project.leaveManagement.service;

import java.sql.Date;
import java.util.List;

import Project.leaveManagement.entity.Employee;
import Project.leaveManagement.entity.LeaveRequests;
import Project.leaveManagement.entity.LeaveStatus;

public interface ManagerService {
	public List<Employee> getTeamDetails(int eid);
	public List<LeaveRequests> getAllLeaveRequests(int manager_id, LeaveStatus leaveStatus, Date leaveStart, Date leaveEnd, String typeOfLeave);
}
