package autentia.apiRestTnt.Controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import autentia.apiRestTnt.Model.Activity;
import autentia.apiRestTnt.Services.ActivityService;

public class ActivityControllerTest {

	private final ActivityService activityService = mock(ActivityService.class);

	private final ActivityController activityController = new ActivityController(activityService);

	@Test
	public void getActivitiesShouldReturnActivitiesFromService() {
		final List<Activity> activities = Arrays.asList(mock(Activity.class));
		when(activityService.getActivities()).thenReturn(activities);

		final List<Activity> result = activityController.getActivities();

		assertThat(result, is(activities));
	}

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
