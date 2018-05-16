/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package autentia.apiRestTnt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class Activity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Date startDate;
	
	@Column
	private Integer duration;
	
	@Column
	private String description;

	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "roleId")
	private ProjectRole projectRole;

	@Column
	private Integer userId;

	@Column
	private Boolean billable;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insertDate")
	@CreationTimestamp
	@JsonIgnore
	private Date insertDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate")
	@UpdateTimestamp
	@JsonIgnore
	private Date updateDate;


	public Activity(){

	}

	public Activity(Date startDate, Integer duration, String description, Boolean billable, Integer userId, ProjectRole projectRole) {
		this.startDate = startDate;
		this.duration = duration;
		this.description = description;
		this.billable = billable;
		this.userId = userId;
		this.projectRole = projectRole;
	}

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

	public Boolean getBillable() {
		return billable;
	}

	public void setBillable(Boolean billable) {
		this.billable = billable;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProjectRole getProjectRole() {
		return projectRole;
	}

	public void setProjectRole(ProjectRole projectRole) {
		this.projectRole = projectRole;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Activity activity = (Activity) o;
		return Objects.equals(id, activity.id) &&
				Objects.equals(startDate, activity.startDate) &&
				Objects.equals(duration, activity.duration) &&
				Objects.equals(description, activity.description) &&
				Objects.equals(projectRole, activity.projectRole) &&
				Objects.equals(userId, activity.userId) &&
				Objects.equals(billable, activity.billable);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, startDate, duration, description, projectRole, userId, billable);
	}
}
