package Project.leaveManagement.dao;

import java.sql.Date;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Project.leaveManagement.entity.Employee;
import Project.leaveManagement.entity.LeaveRequests;
import Project.leaveManagement.entity.LeaveStatus;

@Repository
public class ChangeLeaveDAOImpl implements ChangeLeaveDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public boolean revoke(int leaveId) {
		Session sess =sessionFactory.getCurrentSession();
		LeaveRequests lr = sess.get(LeaveRequests.class, leaveId);
		lr.setLeaveStatus(LeaveStatus.REVOKED);
		sess.save(lr);
		return false;
	}

	@Transactional
	public LeaveRequests getLeaveRequestFromLeaveID(int leaveId) {
		Session sess =sessionFactory.getCurrentSession();
		LeaveRequests lr = sess.get(LeaveRequests.class, leaveId);
		return lr;
	}

	@Transactional
	public int createLeaveRequest(int eid, Date appliedDate, Date startDate, Date endDate, String type,
			String empComment) {
		Session sess = sessionFactory.getCurrentSession();
		sess.enableFilter("noLeaveRequest");
		LeaveRequests lr = new LeaveRequests(appliedDate, startDate, endDate, type, empComment, LeaveStatus.NOT_PROCESSED);
		Employee obj = sess.get(Employee.class, eid);
		obj.add(lr);
		sess.save(obj);
		return lr.getLeaveId();
	}

	@Transactional
	public boolean extendLeave(int leaveId, Date appliedDate, Date endDate, String empComment) {
		Session sess = sessionFactory.getCurrentSession();
		try {
			LeaveRequests lr = sess.get(LeaveRequests.class, leaveId);
			lr.setAppliedDate(appliedDate);
			lr.setLeaveEnd(endDate);
			lr.setEmployeeComment(empComment);
			lr.setLeaveStatus(LeaveStatus.REJECTED);
			sess.save(lr);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}


}
