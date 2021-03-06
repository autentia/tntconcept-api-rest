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

package com.autentia.tnt.api.rest.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.autentia.tnt.api.rest.model.ProjectRole;
import org.junit.Before;
import org.junit.Test;

import com.autentia.tnt.api.rest.model.DTO.ActivitiesDay;
import com.autentia.tnt.api.rest.model.Activity;
import com.autentia.tnt.api.rest.model.User;
import com.autentia.tnt.api.rest.services.ActivityService;
import com.autentia.tnt.api.rest.services.UserService;

public class ActivitiesDayControllerTest {
	
	private final ActivityService activityService = mock(ActivityService.class);
	
	private final UserService userService = mock(UserService.class);
	
	private final ActivitiesDayController activitiesDayController = new ActivitiesDayController(activityService,userService);
	
	private LocalDateTime startDay;
	private LocalDateTime endDay;
	
	@Before
	public void setUp() throws ParseException {
		this.startDay = LocalDateTime.of(2018,2,8,00,00,00);
		this.endDay = LocalDateTime.of(2018,2,9,00,00,00);
	}
	
	@Test
	public void getActivitiesByDayShouldReturnActivitiesDay() {
		Long workedHours = 10L;
		List<Activity> activities = Arrays.asList(mock(Activity.class));
		User userAuthenticated = mock(User.class);
		
		ActivitiesDay returnedActivitiesDay = new ActivitiesDay();


		when(userService.getUserByLogin("admin")).thenReturn(userAuthenticated);
		when(activityService.calculateTotalUserHoursBetweenDays(any(Date.class), any(Date.class), eq(userAuthenticated))).thenReturn(workedHours);
		when(activityService.getActivitiesByDateRange(any(Date.class), any(Date.class), any(Integer.class))).thenReturn(activities);
		
		returnedActivitiesDay.setActivities(activities);
		returnedActivitiesDay.setTotal_hours(workedHours);
		returnedActivitiesDay.setDate(Date.from(startDay.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		final ActivitiesDay result = activitiesDayController.getActivitiesByDay(startDay, () -> "admin");
		assertThat(result,is(returnedActivitiesDay)); 		 
	}
	
	@Test
	public void getActivitiesByDatesShouldReturnActivitiesDayBetweenTwoDates() {
		Long workedHours = 10L;
		List<Activity> activities = Arrays.asList(new Activity(new Date(), 60, "Test", false, 1, new ProjectRole(), 1));
		User userAuthenticated = mock(User.class);

		Principal principal = () -> "admin";

		when(activityService.calculateTotalUserHoursBetweenDays(any(Date.class), any(Date.class), any(User.class))).thenReturn(workedHours);
		when(activityService.getActivitiesByDateRange(any(Date.class), any(Date.class), any(Integer.class))).thenReturn(activities);
		when(userService.getUserByLogin("admin")).thenReturn(userAuthenticated);
		
		final List<ActivitiesDay> result = activitiesDayController.getActivitiesByDates(startDay.toLocalDate(),endDay.toLocalDate(), principal);
		
		assertTrue(result.size() == 2);
	}
	

}
