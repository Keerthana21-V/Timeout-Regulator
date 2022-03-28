package Project.leaveManagement.dao;

import java.sql.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Project.leaveManagement.entity.Holidays;

@Repository
public class HolidaysDAOImpl implements HolidaysDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public int calculateWorkingDaysExcludingWeekends(Date sqlStart, Date sqlEnd) {
		Session sess = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = sess.getCriteriaBuilder();
		CriteriaQuery<Long> cq = builder.createQuery(Long.class);
		Root<Holidays> root = cq.from(Holidays.class);
		cq.select(builder.count(root));
		Predicate andClause = builder.and(builder.notEqual(root.get("day"), "Sunday"), builder.notEqual(root.get("day"), "Saturday"),
				builder.greaterThanOrEqualTo(root.<Date>get("date_of_holiday"), sqlStart), 
				builder.lessThanOrEqualTo(root.<Date>get("date_of_holiday"), sqlEnd));
		cq.where(andClause);
		
		Query<Long> query  =sess.createQuery(cq);
		Long count = query.uniqueResult();
		return count.intValue();
	}

}
