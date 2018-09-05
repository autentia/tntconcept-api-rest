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

package autentia.apiRestTnt.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import autentia.apiRestTnt.model.ActivitiesDay;
import autentia.apiRestTnt.model.Activity;
import autentia.apiRestTnt.model.User;
import autentia.apiRestTnt.services.ActivityService;
import autentia.apiRestTnt.services.UserService;

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
		
		
		when(activityService.calculateHours(any(Date.class), any(Date.class), any(Integer.class))).thenReturn(workedHours);
		when(activityService.getActivitiesByDay(any(Date.class), any(Date.class), any(Integer.class))).thenReturn(activities);
		when(userService.getUserByLogin()).thenReturn(userAuthenticated);
		
		returnedActivitiesDay.setActivities(activities);
		returnedActivitiesDay.setTotal_hours(workedHours);
		returnedActivitiesDay.setDate(Date.from(startDay.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		final ActivitiesDay result = activitiesDayController.getActivitiesByDay(startDay);
		assertThat(result,is(returnedActivitiesDay)); 		 
	}
	
	@Test
	public void getActivitiesByDatesShouldReturnActivitiesDayBetweenTwoDates() {
		Long workedHours = 10L;
		List<Activity> activities = Arrays.asList(mock(Activity.class));
		User userAuthenticated = mock(User.class);
		
		when(activityService.calculateHours(any(Date.class), any(Date.class), any(Integer.class))).thenReturn(workedHours);
		when(activityService.getActivitiesByDay(any(Date.class), any(Date.class), any(Integer.class))).thenReturn(activities);
		when(userService.getUserByLogin()).thenReturn(userAuthenticated);
		
		final List<ActivitiesDay> result = activitiesDayController.getActivitiesByDates(startDay,endDay);
		
		assertTrue(result.size() == 2);
	}
	

}
