package Project.leaveManagement.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Project.leaveManagement.dao.EmployeeDetailsDAO;
import Project.leaveManagement.entity.Employee;
import Project.leaveManagement.entity.LeaveRequests;
import Project.leaveManagement.entity.LeaveStatus;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDetailsDAO employeeDetailsDAO;
	
	@Autowired
	private LeaveRequestResponseService service;
	
	public List<Employee> getTeamDetails(int eid) {
		Employee obj = employeeDetailsDAO.getEmployeeFromID(eid);
		return employeeDetailsDAO.getTeamDetails(obj.getManager_id(), eid);
	}

	public List<Employee> getAppliedLeaves(int eid, LeaveStatus status, Date startDate, Date endDate, String typeOfLeave) {
		return employeeDetailsDAO.getAppliedLeaves(eid, status, startDate, endDate, typeOfLeave);
	}

	public Employee getEmployee(int eid) {
		Employee obj = employeeDetailsDAO.getEmployeeFromID(eid);
		List<LeaveRequests> requests = obj.getLeaveRequests();
		int nonProcessedLeaves = 0;
		for(LeaveRequests request : requests) {
			int count = service.calculateNoOfLeaves(request.getLeaveStart(), request.getLeaveEnd());
			request.setNoOfLeaves(count);
			if(request.getLeaveStatus()==LeaveStatus.NOT_PROCESSED)
				nonProcessedLeaves += count;
		}
		
		obj.setNonProcessedLeaves(nonProcessedLeaves);
		return obj;
	}

}
