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

package autentia.apiRestTnt.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autentia.apiRestTnt.Model.Activity;
import autentia.apiRestTnt.Repository.ActivityRepository;

@Service
public class ActivityService {

	ActivityRepository activityRepository;
	
	@Autowired
	public ActivityService(ActivityRepository activityRepository) {
		super();
		this.activityRepository = activityRepository;
	}

	public Activity getActivityById(Integer activityId) {
		return activityRepository.findOne(activityId);
	}
	
	public List<Activity> getActivitiesByDay(Date startDay, Date endDay,Integer userId) {
		return activityRepository.getActivitiesByDay(startDay,endDay, userId);
	}
	
	public List<Activity> getActivities(){
		return activityRepository.findAll();
	}
	
	public Activity saveActivity(Activity activity) {
		return activityRepository.save(activity);
	}
	
	public Integer calculateHours(Date startDay, Date endDay, Integer userId) {
		return activityRepository.calculateHours(startDay, endDay, userId);
	}
}
