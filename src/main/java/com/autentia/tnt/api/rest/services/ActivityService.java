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

package com.autentia.tnt.api.rest.services;

import java.util.Date;
import java.util.List;

import com.autentia.tnt.api.rest.model.DTO.ActivityDTO;
import com.autentia.tnt.api.rest.model.ProjectRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autentia.tnt.api.rest.model.Activity;
import com.autentia.tnt.api.rest.repository.ActivityRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivityService {

	private ActivityRepository activityRepository;
	private ProjectRoleService projectRoleService;

	@Autowired
	public ActivityService(ActivityRepository activityRepository, ProjectRoleService projectRoleService) {
		super();
		this.activityRepository = activityRepository;
		this.projectRoleService = projectRoleService;
	}

	public Activity getActivityById(Integer activityId) {
		return activityRepository.findById(activityId)
				.orElseThrow(() -> new IllegalArgumentException(("The requested activityId ["+activityId+"] does not exist.")));
	}

	public List<Activity> getActivitiesByDay(Date startDay, Date endDay,Integer userId) {
		return activityRepository.getActivitiesByDay(startDay,endDay, userId);
	}

	public List<Activity> getActivities(){
		return activityRepository.findAll();
	}

	@Transactional
	public Activity saveActivity(Activity activity) {
		return activityRepository.save(activity);
	}

	@Transactional
	public Activity saveActivity(ActivityDTO activityDTO, Integer userID) {
		Activity activityToSave = activityDTOtoActivity(activityDTO, userID);
		return activityRepository.save(activityToSave);
	}

	public Long calculateHours(Date startDay, Date endDay, Integer userId) {
		return activityRepository.calculateHours(startDay, endDay, userId).orElse(0L);
	}

	public List<Date> datesWithActivities(Date startDate, Date endDate, Integer userId) {
		return activityRepository.datesWithActivities(startDate, endDate, userId);
	}

	public void deleteActivityById(Integer id){
		activityRepository.deleteById(id);
	}

	private Activity activityDTOtoActivity(ActivityDTO activityDTO, Integer userID) {
		Activity activity = new Activity();
		activity.setId(activityDTO.getId());
		activity.setStartDate(activityDTO.getStartDate());
		activity.setDuration(activityDTO.getDuration());
		activity.setDescription(activityDTO.getDescription());
		activity.setBillable(activityDTO.getBillable());

		ProjectRole projectRole = projectRoleService.getProjectRoleById(activityDTO.getRoleId());
		activity.setUserId(userID);
		activity.setProjectRole(projectRole);

		return activity;
	}
}
