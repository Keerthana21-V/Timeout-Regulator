package Project.leaveManagement.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.ParamDef;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import Project.leaveManagement.config.JsonDateSerializer;

@Entity
@Table(name="leave_requests")
@FilterDefs({
	@FilterDef(name="pastLeaves", parameters = @ParamDef(name="currentDate", type="date"), defaultCondition = ":currentDate>leaveEnd"),
	@FilterDef(name="currentlyOnLeave", parameters = @ParamDef(name="currentDate", type="date"),defaultCondition = ":currentDate<=leaveEnd and :currentDate>=leaveStart"),
	@FilterDef(name="FutureLeaves", parameters = @ParamDef(name="currentDate", type="date"), defaultCondition = ":currentDate<leaveStart"),
	@FilterDef(name="statusOfLeaves", parameters = { 
			@ParamDef(name="status", type="integer")
	}, defaultCondition = ":status=leaveStatus"),
	@FilterDef(name="onlyTopRow", defaultCondition = "order by leaveStart"),
	@FilterDef(name="knowTeam", defaultCondition = "leaveStatus=2 and ((currentDate()>=leaveStart and currentDate()<=leaveEnd) or currentDate()<leaveStart) order by leaveStart")
})
public class LeaveRequests {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="leaveId")
	private int leaveId;
	
	//@Column(name="eid")
	//private int eid;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name="appliedDate")
	private Date appliedDate;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name="leaveStart")
	private Date leaveStart;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name="leaveEnd")
	private Date leaveEnd;
	
	@Column(name="typeOfLeave")
	private String typeOfLeave;
	
	@Column(name="empComment")
	private String employeeComment;
	
	@Column(name="managerComment")
	private String managerComment;
	
	@Enumerated(EnumType.STRING)
	@Column(name="leaveStatus")
	private LeaveStatus leaveStatus;

	@Formula(value = "case when curdate()>=leaveStart and curdate()<=leaveEnd then 0 else 1 end")
	@JsonSerialize
	private boolean available;
	
	@Transient
	private int noOfLeaves;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name="eid")
	@JsonIgnoreProperties("leaveRequests")
	private Employee employee;
	
	public int getNoOfLeaves() {
		return noOfLeaves;
	}

	public void setNoOfLeaves(int noOfLeaves) {
		this.noOfLeaves = noOfLeaves;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	
	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	/*public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}*/

	public Date getAppliedDate() {
		return appliedDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}

	public Date getLeaveStart() {
		return leaveStart;
	}

	public void setLeaveStart(Date leave_start) {
		this.leaveStart = leave_start;
	}

	public Date getLeaveEnd() {
		return leaveEnd;
	}

	public void setLeaveEnd(Date leave_end) {
		this.leaveEnd = leave_end;
	}

	public String getTypeOfLeave() {
		return typeOfLeave;
	}

	public void setTypeOfLeave(String typeOfLeave) {
		this.typeOfLeave = typeOfLeave;
	}

	public String getEmployeeComment() {
		return employeeComment;
	}

	public void setEmployeeComment(String employeeComment) {
		this.employeeComment = employeeComment;
	}

	public String getManagerComment() {
		return managerComment;
	}

	public void setManagerComment(String managerComment) {
		this.managerComment = managerComment;
	}

	public LeaveStatus getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(LeaveStatus leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	@Override
	public String toString() {
		return "LeaveRequests [leaveId=" + leaveId + ", appliedDate=" + appliedDate + ", leave_start="
				+ leaveStart + ", leave_end=" + leaveEnd + ", typeOfLeave=" + typeOfLeave + ", employeeComment="
				+ employeeComment + ", managerComment=" + managerComment + ", leaveStatus=" + leaveStatus + "]";
	}

	public LeaveRequests(Date appliedDate, Date leaveStart, Date leaveEnd, String typeOfLeave,
			String employeeComment, LeaveStatus leaveStatus) {
		this.appliedDate = appliedDate;
		this.leaveStart = leaveStart;
		this.leaveEnd = leaveEnd;
		this.typeOfLeave = typeOfLeave;
		this.employeeComment = employeeComment;
		this.leaveStatus = leaveStatus;
	}
	
	public LeaveRequests() {
		
	}
	
}
