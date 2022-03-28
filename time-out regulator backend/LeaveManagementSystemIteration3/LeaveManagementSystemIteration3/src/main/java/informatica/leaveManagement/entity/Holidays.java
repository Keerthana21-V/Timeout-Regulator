package Project.leaveManagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// Entity class mapped with Holidays table...
// This table gives the information about public holidays...
@Entity
@Table(name="holidays")
public class Holidays {
	// Attributes..
	@Column(name="day_of_week")
	private String day;									// Day of the week
	
	@Id
	@Temporal(TemporalType.DATE)						// Maps java.util.Date to java.sql.Date
	@Column(name="date_of_holiday")
	private Date date_of_holiday;						// Date of the holiday
	
	@Column(name="holiday")
	private String holiday;								// Holiday....

	// Getters and Setters...
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Date getDate() {
		return date_of_holiday;
	}

	public void setDate(Date date) {
		this.date_of_holiday = date;
	}

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
}
