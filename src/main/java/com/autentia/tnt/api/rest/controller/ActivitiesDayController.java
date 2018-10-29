/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L. Copyright (C) 2007 Autentia Real Bussiness
 * Solution S.L. This program is free software: you can redistribute it and/or modify it under the terms of the GNU General
 * Public License as published by the Free Software Foundation, either version 3 of the License. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of
 * the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.api.rest.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.autentia.tnt.api.rest.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autentia.tnt.api.rest.model.DTO.ActivitiesDay;
import com.autentia.tnt.api.rest.model.User;
import com.autentia.tnt.api.rest.services.ActivityService;
import com.autentia.tnt.api.rest.services.UserService;

@RestController
@RequestMapping("/api")
public class ActivitiesDayController {

	private ActivityService activityService;

	private UserService userService;

	@Autowired
	public ActivitiesDayController(ActivityService activityService, UserService userService) {
		super();
		this.activityService = activityService;
		this.userService = userService;
	}

	@GetMapping("/activities")
	public List<ActivitiesDay> getActivitiesByDates(
			@RequestParam(name = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
			@RequestParam(name = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
		User user = userService.getUserByLogin();
		Date parsedStartDate = Date.from(startDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date parsedFinalDate = Date.from(endDate.plusDays(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		final List<Activity> activities = activityService.getActivitiesByDay(parsedStartDate, parsedFinalDate, user.getId());
		List<ActivitiesDay> activitiesDayList = new ArrayList<>();
		LocalDate dateIncrement = startDate;
		while((startDate.isEqual(dateIncrement)) || (endDate.isAfter(dateIncrement))
				|| (endDate.isEqual(dateIncrement))) {

			LocalDate finalDateIncrement = dateIncrement;
			List<Activity> activitiesByDateIncrement =
					activities.stream()
							.filter(activity -> activity.getStartDate().getDate() == finalDateIncrement.getDayOfMonth())
							.collect(Collectors.toList());

			ActivitiesDay activitiesDay = new ActivitiesDay();
			activitiesDay.setDate(Date.from(finalDateIncrement.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
			activitiesDay.setActivities(activitiesByDateIncrement);
			if (activitiesByDateIncrement.isEmpty()){
				activitiesDay.setTotal_hours(0L);
			} else {
				Long totalMinutes = activitiesByDateIncrement.stream().mapToLong(Activity::getDuration).sum();
				activitiesDay.setTotal_hours(totalMinutes);
			}

			activitiesDayList.add(activitiesDay);

			dateIncrement = dateIncrement.plusDays(1);
		}
		return activitiesDayList;
	}

	@GetMapping("/activitiesByDay")
	public ActivitiesDay getActivitiesByDay(
			@RequestParam(name = "date", required = true) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime date) {

		ActivitiesDay activitiesDay = new ActivitiesDay();
		User user = userService.getUserByLogin();

		activitiesDay.setDate(Date.from(date.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));

		Date startDay = Date.from(date.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDay = Date.from(date.plusDays(1).toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());

		activitiesDay.setTotal_hours(activityService.calculateHours(startDay, endDay));
		activitiesDay.setActivities(activityService.getActivitiesByDay(startDay, endDay, user.getId()));

		return activitiesDay;
	}

	@GetMapping("/activitiesTime")
	public double getActivitiesTime(
			@RequestParam("startDate") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime endDate) {

		User user = userService.getUserByLogin();
		Date startDay = Date.from(startDate.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDay = Date.from(endDate.toLocalDate().atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());
		return activityService.calculateHours(startDay, endDay);
	}

	@GetMapping("/imputedDays")
	public List<Date> imputedDays(
			@RequestParam("startDate") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime endDate){
		User user = userService.getUserByLogin();
		Date startDay = Date.from(startDate.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDay = Date.from(endDate.toLocalDate().atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());
		return activityService.datesWithActivities(startDay, endDay, user.getId());
	}
}
