package autentia.apiRestTnt.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Activity {
	
	@Id
	private Integer id;
	
	@Column
	private Date startDate;
	
	@Column
	private Integer duration;
	
	@Column
	private String description;
	
	@Column
	private Boolean billable;
	
	@Column
	private Integer userId;
	
	@Column
	private Integer roleId;

	public Integer getId() {
		return id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Boolean getBilliable() {
		return billable;
	}

	public void setBilliable(Boolean billiable) {
		this.billable = billiable;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	

}
