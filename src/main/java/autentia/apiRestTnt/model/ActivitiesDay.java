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

import java.util.Date;
import java.util.List;

public class ActivitiesDay {
	
	private Date date;
	
	private Double total_hours;
	
	private List<Activity> activities;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getTotal_hours() {
		return total_hours;
	}

	public void setTotal_hours(Double total_hours) {
		this.total_hours = total_hours;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activities == null) ? 0 : activities.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((total_hours == null) ? 0 : total_hours.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ActivitiesDay))
			return false;
		ActivitiesDay other = (ActivitiesDay) obj;
		if (activities == null) {
			if (other.activities != null)
				return false;
		} else if (!activities.equals(other.activities))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (total_hours == null) {
			if (other.total_hours != null)
				return false;
		} else if (!total_hours.equals(other.total_hours))
			return false;
		return true;
	}
	
	
	
	

}
