package Project.leaveManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="employeeLoginDetails")
@Immutable
public class EmployeeLoginDetails {

	@Id
	@Column(name="eid")
	private int eid;
	
	@Column(name="euname")
	private String euname;
	
	@Column(name="passwd")
	private String passwd;

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEuname() {
		return euname;
	}

	public void setEuname(String euname) {
		this.euname = euname;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {
		return "EmployeeLoginDetails [eid=" + eid + ", euname=" + euname + ", passwd=" + passwd + "]";
	}
	
}
