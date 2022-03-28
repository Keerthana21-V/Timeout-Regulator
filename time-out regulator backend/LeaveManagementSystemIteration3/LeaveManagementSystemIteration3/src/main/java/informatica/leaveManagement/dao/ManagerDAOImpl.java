package Project.leaveManagement.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
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
public class ManagerDAOImpl implements ManagerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public List<Integer> getManagersUnderSome(int manager_id){
		Session sess = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = sess.getCriteriaBuilder();
		CriteriaQuery<Integer> cq = builder.createQuery(Integer.class);
		Root<Employee> root = cq.from(Employee.class);
		cq.multiselect(root.get("eid"));
		cq.where(builder.equal(root.get("manager_id"), manager_id));
		Query<Integer> query = sess.createQuery(cq);
		List<Integer> managers = query.getResultList();
		System.out.println(managers.toString());
		return managers;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public List<LeaveRequests> getAllLeaveRequests(int manager_id, LeaveStatus status, Date leaveStart, Date leaveEnd,
			String typeOfLeave) {
		Session sess = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = sess.getCriteriaBuilder();
		CriteriaQuery<LeaveRequests> cq = builder.createQuery(LeaveRequests.class);
		Root<LeaveRequests> root = cq.from(LeaveRequests.class);
		Join<LeaveRequests, Employee> join = (Join) root.fetch("employee", JoinType.INNER);
		//root.join("employee", JoinType.INNER);
		//Root<Employee> emp = cq.from(Employee.class);
		cq.select(root).distinct(true);
		Predicate andClause = builder.and(builder.equal(join.get("manager_id"), manager_id), 
				builder.notEqual(root.get("leaveStatus"), LeaveStatus.REVOKED));
		
		if(leaveStart!=null)
			andClause = builder.and(andClause, builder.greaterThanOrEqualTo(root.<Date>get("leaveStart"), leaveStart));
		
		if(leaveEnd!=null)
			andClause = builder.and(andClause, builder.lessThanOrEqualTo(root.<Date>get("leaveEnd"), leaveEnd));
		
		if(status!=null) {
			if(status==LeaveStatus.ACCEPTED) {
				andClause = builder.and(andClause, builder.or(
						builder.equal(root.get("leaveStatus"), status),
						builder.equal(root.get("leaveStatus"), LeaveStatus.EXTENDED))
						);
			}
			else
				andClause = builder.and(andClause, builder.equal(root.get("leaveStatus"), status));
		}
		if(typeOfLeave!=null)
			andClause = builder.and(andClause, builder.equal(root.get("typeOfLeave"), typeOfLeave));
		In<Integer> inClause = builder.in(join.<Integer>get("manager_id"));
		List<Integer> managers = getManagersUnderSome(manager_id);
		
		for(Integer manager: managers) {
			inClause.in(manager);
		}
		Predicate andClause2 = builder.and(builder.equal(root.get("leaveStatus"), LeaveStatus.ESCALATED), join.get("manager_id").in(managers));
		Predicate orClause = builder.or(andClause, andClause2);
		cq.where(orClause);
		
		Query<LeaveRequests> query = sess.createQuery(cq);
		List<LeaveRequests> allLeaves = query.getResultList();
		
		return allLeaves;
	}
	
	

}
