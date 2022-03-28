package Project.leaveManagement.entity;

public enum LeaveStatus {
	NOT_PROCESSED(1), ACCEPTED(2), REJECTED(3), REVOKED(4), EXTENDED(5), ESCALATED(6);
	
	private int val;
	
	LeaveStatus(int val) {
		this.val=val;
	}
	
	public int getVal() {
		return this.val;
	}
}
