package Project.leaveManagement.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Project.leaveManagement.entity.LeaveDetails;

@Repository
public class UpdationOfEmployeeDAOImpl implements UpdationOfEmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public boolean updateEmpLeaveDetails(int eid, int noOfLeaves) {
		Session sess = sessionFactory.getCurrentSession();
		LeaveDetails obj = sess.get(LeaveDetails.class, eid);
		obj.setVacationLeaves(obj.getVacationLeaves()-noOfLeaves);
		sess.save(obj);
		return true;
	}

}
