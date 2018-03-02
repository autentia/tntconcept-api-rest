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

package autentia.apiRestTnt.Controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import autentia.apiRestTnt.Model.Activity;
import autentia.apiRestTnt.Services.ActivityService;

public class ActivityControllerTest {

	private final ActivityService activityService = mock(ActivityService.class);

	private final ActivityController activityController = new ActivityController(activityService);


	@Test
	public void addActivityShouldReturnActivityAfterSaving() {
		final Activity activityToSave = mock(Activity.class);
		final Activity savedActivity = mock(Activity.class);
		when(activityService.saveActivity(activityToSave)).thenReturn(savedActivity);

		final Activity result = activityController.addActivity(activityToSave);

		assertThat(result, is(savedActivity));
	}
	
	@Test
	public void getActivityShouldReturnActivityFromService() {
		final Activity activityToReturn = mock(Activity.class);
		final Activity returnedActivity = mock(Activity.class);
		when(activityService.getActivityById(activityToReturn.getId())).thenReturn(returnedActivity);
		
		final Activity result = activityController.getActivity(activityToReturn.getId());
		assertThat(result, is(returnedActivity));
	}
}
