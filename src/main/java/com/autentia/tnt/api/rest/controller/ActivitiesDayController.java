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
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autentia.tnt.api.rest.model.ActivitiesDay;
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

	@GetMapping("/activitiesByDates")
	public List<ActivitiesDay> getActivitiesByDates(
			@RequestParam(name = "startDate", required = true) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam(name = "endDate", required = true) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime endDate) {

		List<ActivitiesDay> activitiesDayList = new ArrayList<>();
		LocalDateTime localDateTimeIncrement = startDate;

		while ((startDate.isEqual(localDateTimeIncrement)) || (endDate.isAfter(localDateTimeIncrement))
				|| (endDate.isEqual(localDateTimeIncrement))) {

			ActivitiesDay activitiesDay = getActivitiesByDay(localDateTimeIncrement);

			if (activitiesDay.getTotal_hours() != null) {
				activitiesDayList.add(activitiesDay);
			}

			localDateTimeIncrement = localDateTimeIncrement.plusDays(1);
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

		activitiesDay.setTotal_hours(activityService.calculateHours(startDay, endDay, user.getId()));
		activitiesDay.setActivities(activityService.getActivitiesByDay(startDay, endDay, user.getId()));

		return activitiesDay;
	}

	@GetMapping("/activitiesTime")
	public double getActivitiesTime(
			@RequestParam("startDate") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime endDate,
			@RequestParam("userId") Integer userId) {

		Date startDay = Date.from(startDate.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDay = Date.from(endDate.toLocalDate().atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());
		return activityService.calculateHours(startDay, endDay, userId);
	}

	@GetMapping("/imputedDays")
	public List<Date> imputedDays(
			@RequestParam("startDate") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime endDate,
			@RequestParam("userId") Integer userId){
		Date startDay = Date.from(startDate.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDay = Date.from(endDate.toLocalDate().atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());
		return activityService.datesWithActivities(startDay, endDay, userId);
	}
}
