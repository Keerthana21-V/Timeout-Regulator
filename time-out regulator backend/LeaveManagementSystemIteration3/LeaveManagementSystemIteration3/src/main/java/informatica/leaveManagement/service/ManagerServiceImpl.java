package Project.leaveManagement.service;

import java.sql.Date;
//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Project.leaveManagement.dao.EmployeeDetailsDAO;
import Project.leaveManagement.dao.ManagerDAO;
import Project.leaveManagement.entity.Employee;
import Project.leaveManagement.entity.LeaveRequests;
import Project.leaveManagement.entity.LeaveStatus;

@Service
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	private EmployeeDetailsDAO employeeDAO;
	
	@Autowired
	private ManagerDAO managerDAO;
	
	public List<Employee> getTeamDetails(int eid) {
		System.out.println("Manager Is "+eid);
		return employeeDAO.getTeamDetails(eid, eid);
	}

	public List<LeaveRequests> getAllLeaveRequests(int manager_id, LeaveStatus leaveStatus, Date leaveStart,
			Date leaveEnd, String typeOfLeave) {
		
		return managerDAO.getAllLeaveRequests(
				manager_id, leaveStatus, leaveStart, 
				leaveEnd, typeOfLeave);
	}

}
