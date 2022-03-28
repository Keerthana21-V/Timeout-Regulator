package Project.leaveManagement.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Project.leaveManagement.entity.LeaveRequests;
import Project.leaveManagement.entity.LeaveStatus;

@Repository
public class LeaveResponseDAOImpl implements LeaveResponseDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public LeaveRequests approveLeave(int leaveId, String managerComment) {
		Session sess = sessionFactory.getCurrentSession();
		LeaveRequests lr = sess.get(LeaveRequests.class, leaveId);
		lr.setLeaveStatus(LeaveStatus.ACCEPTED);
		lr.setManagerComment(managerComment);
		sess.save(lr);
		return lr;

	}
	
	@Transactional
	public void rejectLeave(int leaveId, String managerComment) {
		Session sess = sessionFactory.getCurrentSession();
		LeaveRequests lr = sess.get(LeaveRequests.class, leaveId);
		lr.setLeaveStatus(LeaveStatus.REJECTED);
		lr.setManagerComment(managerComment);
		sess.save(lr);
	}

}
