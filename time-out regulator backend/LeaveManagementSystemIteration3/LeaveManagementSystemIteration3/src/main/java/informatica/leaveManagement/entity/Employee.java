package Project.leaveManagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="employee")
@JsonIgnoreProperties({"euname", "passwd"})
public class Employee {

	@Id
	@Column(name="eid")
	private int eid;
	
	@Column(name="euname")
	private String euname;
	
	@Column(name="ename")
	private String ename;
	
	@Column(name="email")
	private String email;
	
	@Column(name="manager_id")
	private int manager_id;
	
	//@Transient
	@Column(name="passwd")
	private String passwd;

	public String getPasswd() {
		return passwd;
	}

	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="eid")
	
	private LeaveDetails leaveDetails;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	//@Fetch(FetchMode.SUBSELECT)
	
	@Filters({
		@Filter(name="statusOfLeaves"),
		@Filter(name="pastLeaves"),
		@Filter(name="currentlyOnLeave"),
		@Filter(name="FutureLeaves"),
		@Filter(name="onlyTopRow")
	})
	@JsonIgnoreProperties("employee")
	@OrderBy("leaveStart ASC")
	
	private List<LeaveRequests> leaveRequests;
	
	@Transient
	private int nonProcessedLeaves;
	
	public int getNonProcessedLeaves() {
		return nonProcessedLeaves;
	}

	public void setNonProcessedLeaves(int nonProcessedLeaves) {
		this.nonProcessedLeaves = nonProcessedLeaves;
	}

	public int getEid() {
		return eid;
	}

	public String getEuname() {
		return euname;
	}

	public String getEname() {
		return ename;
	}

	public String getEmail() {
		return email;
	}

	public int getManager_id() {
		return manager_id;
	}

	public LeaveDetails getLeaveDetails() {
		return leaveDetails;
	}

	public void setLeaveDetails(LeaveDetails leaveDetails) {
		this.leaveDetails = leaveDetails;
	}
	
	public List<LeaveRequests> getLeaveRequests() {
		return leaveRequests;
	}

	public void add(LeaveRequests lr) {
		if(leaveRequests==null)
			leaveRequests = new ArrayList<LeaveRequests>();
		lr.setEmployee(this);
		leaveRequests.add(lr);
	}
	
	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", email=" + email + ", manager_id=" + manager_id + "]";
	}
	
	
}
