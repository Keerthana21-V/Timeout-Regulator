package Project.leaveManagement.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
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
import Project.leaveManagement.entity.EmployeeLoginDetails;
import Project.leaveManagement.entity.LeaveRequests;
import Project.leaveManagement.entity.LeaveStatus;

@Repository
public class EmployeeDetailsDAOImpl implements EmployeeDetailsDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public Employee getEmployeeFromID(int eid) {
		Session sess = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = sess.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = builder.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		root.fetch("leaveRequests", JoinType.LEFT);
		cq.select(root);
		cq.where(builder.equal(root.get("eid"), eid));
		
		Query<Employee> query = sess.createQuery(cq);
		Employee obj = query.uniqueResult();
		return obj;
		//return new Employee();
	}

	@Transactional
	public Integer getEmpIdFromUname(String euname, String passwd) {
		Session sess = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = sess.getCriteriaBuilder();
		CriteriaQuery<Integer> cq = builder.createQuery(Integer.class);
		Root<EmployeeLoginDetails> root = cq.from(EmployeeLoginDetails.class);
		cq.multiselect(root.get("eid"));
		Query<Integer> query = sess.createQuery(cq);
		Integer eid = query.getSingleResult();
		return eid;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public List<Employee> getTeamDetails(int manager_id, int eid) {
		Session sess = sessionFactory.getCurrentSession();
		sess.enableFilter("knowTeam");
		CriteriaBuilder builder = sess.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = builder.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		//Join<Employee, LeaveRequests> join = root.join("leaveRequests", JoinType.INNER);
		
		Join<Employee, LeaveRequests> join = (Join) root.fetch("leaveRequests", JoinType.LEFT);
		java.util.Date date = new java.util.Date();
		Date currDate = new Date(date.getTime());
		cq.select(root).distinct(true);
		//cq.orderBy(builder.desc(leaveRoot.get("leaveStart")));
//		Predicate andClause = builder.and(builder.lessThanOrEqualTo(join.<Date>get("leaveStart"), currDate),
//				builder.greaterThanOrEqualTo(join.<Date>get("leaveEnd"), currDate));
//		Predicate futureClause = builder.or(andClause, builder.greaterThanOrEqualTo(join.<Date>get("leaveStart"), currDate));
		Predicate finalAND = builder.and(builder.notEqual(root.get("eid"), eid), 
				builder.equal(root.get("manager_id"), manager_id));
		//Predicate finalClause = builder.or(builder.equal(root.get("leaveRequests"), null), finalAND);
		cq.where(finalAND);//.orderBy(builder.asc(leaveRoot.get("leaveStart")));
		
		Query<Employee> query = sess.createQuery(cq);
		List<Employee> team = query.getResultList();
//		for(Employee obj: team) {
//			List<LeaveRequests> lr = obj.getLeaveRequests();
//			if(lr!=null && lr.size()>=1)
//				lr.subList(1, lr.size()).clear();
//			System.out.println(lr.size());
//		}
		return team;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Transactional
	public List<Employee> getAppliedLeaves(int eid, LeaveStatus status, Date startDate, Date endDate, String typeOfLeave) {
		Session sess = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = sess.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = builder.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		
		Join<Employee, LeaveRequests> join = (Join) root.fetch("leaveRequests", JoinType.INNER);
		//Fetch<Employee, LeaveRequests> join = root.fetch("leaveRequests", JoinType.INNER);
		//Root<LeaveRequests> leaveRoot = cq.from(LeaveRequests.class);
		cq.select(root).distinct(true);
		Predicate andClause = builder.equal(root.get("eid"), eid);
		if(startDate!=null)
			andClause = builder.and(builder.greaterThanOrEqualTo( join.<Date>get("leaveStart"), startDate));
		if(endDate!=null)
			andClause = builder.and(andClause, builder.lessThanOrEqualTo(join.<Date>get("leaveEnd"), endDate));
		if(typeOfLeave!=null)
			andClause = builder.and(andClause, builder.equal(join.get("typeOfLeave"), typeOfLeave));
		if(status!=null) {
			if(status==LeaveStatus.ACCEPTED)
				andClause = builder.and(andClause, builder.or(
						builder.equal(join.get("leaveStatus"), status),
						builder.equal(join.get("leaveStatus"), LeaveStatus.EXTENDED))
						);
			else
				andClause = builder.and(andClause, builder.equal(join.get("leaveStatus"), status));
		}
		cq.where(andClause);
		Query<Employee> query = sess.createQuery(cq);
		List<Employee> appliedLeaves = query.getResultList();
		return appliedLeaves;
	}
	
	@Transactional
	public Employee getEmployeeFromUsername(String username) {
		
		Session sess = sessionFactory.getCurrentSession();
		
		Employee e = (Employee)sess
				.createQuery("from Employee where euname = :uname ")
				.setParameter("uname", username)
				.uniqueResult();
		
		return e;
	}


}
