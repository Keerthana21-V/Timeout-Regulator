package Project.leaveManagement.dao;

import java.sql.Date;
import java.util.List;

import Project.leaveManagement.entity.Employee;
import Project.leaveManagement.entity.LeaveStatus;

public interface EmployeeDetailsDAO {

	public Employee getEmployeeFromID(int eid);
	public Employee getEmployeeFromUsername(String username);
	public Integer getEmpIdFromUname(String euname, String passwd);
	public List<Employee> getTeamDetails(int manager_id, int eid);
	public List<Employee> getAppliedLeaves(int eid, LeaveStatus status, Date startDate, Date endDate, String typeOfLeave);
}
