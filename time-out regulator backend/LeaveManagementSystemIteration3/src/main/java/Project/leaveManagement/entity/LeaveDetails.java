package Project.leaveManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="emp_leave_details")
public class LeaveDetails {

	@Id
	@Column(name="eid")
	private int eid;
	
//	@Column(name="sick_leaves")
//	private int sickLeaves;
//	
	@Column(name="vacation_leaves")
	private int vacationLeaves;
	
	@Column(name="loss_of_pay")
	private int lossOfPay;

	public int getEid() {
		return eid;
	}

//	public int getSickLeaves() {
//		return sickLeaves;
//	}
//
//	public void setSickLeaves(int sickLeaves) {
//		this.sickLeaves = sickLeaves;
//	}

	public int getVacationLeaves() {
		return vacationLeaves;
	}

	public void setVacationLeaves(int vacationLeaves) {
		this.vacationLeaves = vacationLeaves;
	}

	public int getLossOfPay() {
		return lossOfPay;
	}

	public void setLossOfPay(int lossOfPay) {
		this.lossOfPay = lossOfPay;
	}
	
//	@Override
//	public String toString() {
//		return "LeaveDetails [eid=" + eid + ", sickLeaves=" + sickLeaves + ", vacationLeaves=" + vacationLeaves
//				+ ", lossOfPay=" + lossOfPay + "]";
//	}
}
