package Project.leaveManagement.service;

import java.sql.Date;
import java.util.List;

import Project.leaveManagement.entity.Employee;
import Project.leaveManagement.entity.LeaveStatus;

public interface EmployeeService {

	public List<Employee> getTeamDetails(int eid);
	public List<Employee> getAppliedLeaves(int eid, LeaveStatus status, Date startDate, Date endDate, String typeOfLeave);
	public Employee getEmployee(int eid);
}
