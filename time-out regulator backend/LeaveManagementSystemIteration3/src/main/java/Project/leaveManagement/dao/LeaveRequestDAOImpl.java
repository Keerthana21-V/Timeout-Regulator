package Project.leaveManagement.dao;

import java.sql.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Project.leaveManagement.entity.Employee;
import Project.leaveManagement.entity.LeaveRequests;
import Project.leaveManagement.entity.LeaveStatus;

@Repository
public class LeaveRequestDAOImpl implements LeaveRequestDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void createLeaveRequest(int eid, Date leaveStart, Date leaveEnd, String typeOfLeave, String employeeComment) {
		Session sess = sessionFactory.getCurrentSession();
		java.util.Date date = new java.util.Date();
		Date currDate = new Date(date.getTime());
		
		Employee obj = sess.get(Employee.class, eid);
		LeaveRequests lr = new LeaveRequests(currDate, leaveStart, leaveEnd, typeOfLeave, employeeComment, LeaveStatus.NOT_PROCESSED);
		obj.add(lr);
		sess.save(obj);
		
	}
	
	@Transactional
	public boolean checkLeave(int leaveId, int manager_id) {
        Session sess = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = sess.getCriteriaBuilder();
        CriteriaQuery<LeaveRequests> cq = builder.createQuery(LeaveRequests.class);
        Root<LeaveRequests> root = cq.from(LeaveRequests.class);
       
        Join<LeaveRequests, Employee> join = (Join) root.fetch("employee", JoinType.INNER);
        cq.select(root);
        cq.where(builder.and(builder.equal(root.get("leaveId"), leaveId),
                builder.equal(join.get("manager_id"), manager_id)));
        Query<LeaveRequests> query = sess.createQuery(cq);
        LeaveRequests lr = query.uniqueResult();
        if(lr==null)
            return false;
        return true;
    }

}
