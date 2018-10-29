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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.autentia.tnt.api.rest.model.DTO.ActivityDTO;
import com.autentia.tnt.api.rest.model.User;
import com.autentia.tnt.api.rest.services.ProjectRoleService;
import com.autentia.tnt.api.rest.services.UserService;
import org.junit.Test;

import com.autentia.tnt.api.rest.model.Activity;
import com.autentia.tnt.api.rest.services.ActivityService;

public class ActivityControllerTest {

	private final ActivityService activityService = mock(ActivityService.class);

	private final UserService userService = mock(UserService.class);

	private final ActivityController activityController = new ActivityController(activityService, userService);


	@Test
	public void addActivityShouldReturnActivityAfterSaving() {
		final ActivityDTO activityDTOToSave = mock(ActivityDTO.class);
		final Activity savedActivity = mock(Activity.class);
		final User userToReturn = mock(User.class);
		when(userToReturn.getId()).thenReturn(1);
		when(userService.getUserByLogin()).thenReturn(userToReturn);
		when(userService.getUserByLogin().getId()).thenReturn(1);
		when(activityService.saveActivity(activityDTOToSave)).thenReturn(savedActivity);

		final Activity result = activityController.addActivity(activityDTOToSave);
		assertThat(result, is(savedActivity));
	}
	
	@Test
	public void getActivityShouldReturnActivityFromService() {
		final Activity activityToReturn = mock(Activity.class);
		final User userToReturn = mock(User.class);
		when(userToReturn.getId()).thenReturn(1);
		when(activityToReturn.getUserId()).thenReturn(1);
		when(userService.getUserByLogin()).thenReturn(userToReturn);
		when(userService.getUserByLogin().getId()).thenReturn(1);
		when(activityService.getActivityById(2)).thenReturn(activityToReturn);


		final Activity result = activityController.getActivity(2);
		assertThat(result, is(activityToReturn));
	}
}
