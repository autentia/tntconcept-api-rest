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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.autentia.tnt.api.rest.model.User;
import org.junit.Before;
import org.junit.Test;

import com.autentia.tnt.api.rest.model.Activity;
import com.autentia.tnt.api.rest.repository.ActivityRepository;

public class ActivityServiceTest {
	private final ActivityRepository activityRepository = mock(ActivityRepository.class);
	private final ProjectRoleService projectRoleService = mock(ProjectRoleService.class);
	private final UserService userService = mock(UserService.class);

	private final ActivityService activityService = new ActivityService(activityRepository, projectRoleService, userService);
	
	private Date startDay;
	private Date endDay;
	private Integer userId;
	
	@Before
	public void setUp() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		this.startDay = format.parse("2018-02-08");
		this.endDay = format.parse("2018-02-09");
		userId = 1;
	}
	
	@Test
	public void getActivitiesShouldReturnActivitiesFromRepository() {
		final List<Activity> activities = Arrays.asList(mock(Activity.class));
		when(activityRepository.findAll()).thenReturn(activities);

		final List<Activity> result = activityService.getActivities();

		assertThat(result, is(activities));
	}

	@Test
	public void addActivityShouldReturnActivityAfterSaving() {
		final Activity activityToSave = mock(Activity.class);
		final Activity savedActivity = mock(Activity.class);
		final User mockUser = mock(User.class);
		when(activityRepository.save(activityToSave)).thenReturn(savedActivity);
		when(userService.getUserByLogin()).thenReturn(mockUser);
		when(mockUser.getDepartmentId()).thenReturn(1);

		final Activity result = activityService.saveActivity(activityToSave);

		assertThat(result, is(savedActivity));
	}
	
	@Test
	public void getActivityShouldReturnActivityFromService() {
		final Activity activityToReturn = mock(Activity.class);
		final Activity returnedActivity = mock(Activity.class);
		when(activityRepository.findById(activityToReturn.getId())).thenReturn(Optional.ofNullable(returnedActivity));
		
		final Activity result = activityService.getActivityById(activityToReturn.getId());
		assertThat(result, is(returnedActivity));
	}
	
//	@Test
//	public void calculateHoursShouldReturnWorkedHoursByUserByDayFromRepository() {
//		final Optional<Double> workedHours = Optional.of(10.0);
//
//		when(activityRepository.calculateHours(startDay, endDay, userId)).thenReturn(workedHours);
//
//		final Double result = activityService.calculateHours(startDay, endDay, userId);
//		assertThat(result,is(workedHours));
//	}
	
	@Test
	public void getActivitiesByDayShouldReturnActivitiesByDayFromRepository() {
		final List<Activity> activities = Arrays.asList(mock(Activity.class));
		
		when(activityRepository.getActivitiesByDay(startDay, endDay, userId)).thenReturn(activities);
		
		final List<Activity> result = activityService.getActivitiesByDay(startDay, endDay, userId);
		assertThat(result,is(activities));
	}

	@Test
	public void deleteActivityShouldDeleteActivityFromDatabase(){
		final Integer id = 12;
		doNothing().when(activityRepository).deleteById(id);

		activityService.deleteActivityById(id);

		verify(activityRepository).deleteById(id);
	}

}
