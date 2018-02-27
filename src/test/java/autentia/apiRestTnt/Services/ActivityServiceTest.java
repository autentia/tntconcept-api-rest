package autentia.apiRestTnt.Services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import autentia.apiRestTnt.Model.Activity;
import autentia.apiRestTnt.Repository.ActivityRepository;

public class ActivityServiceTest {
	private final ActivityRepository activityRepository = mock(ActivityRepository.class);

	private final ActivityService activityService = new ActivityService(activityRepository);
	
	private Date startDay;
	private Date endDay;
	private Integer userId;
	
	@Before
	public void setUp() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yy-mm-dd");
		this.startDay = format.parse("2018-02-08");
		this. endDay = format.parse("2018-02-09");
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
		when(activityRepository.save(activityToSave)).thenReturn(savedActivity);

		final Activity result = activityService.saveActivity(activityToSave);

		assertThat(result, is(savedActivity));
	}
	
	@Test
	public void getActivityShouldReturnActivityFromService() {
		final Activity activityToReturn = mock(Activity.class);
		final Activity returnedActivity = mock(Activity.class);
		when(activityRepository.findOne(activityToReturn.getId())).thenReturn(returnedActivity);
		
		final Activity result = activityService.getActivityById(activityToReturn.getId());
		assertThat(result, is(returnedActivity));
	}
	
	@Test
	public void calculateHoursShouldReturnWorkedHoursByUserByDayFromRepository() {
		final Integer workedHours = 10;
		
		when(activityRepository.calculateHours(startDay, endDay, userId)).thenReturn(workedHours);
		
		final Integer result = activityService.calculateHours(startDay, endDay, userId);
		assertThat(result,is(workedHours));
	}
	
	@Test
	public void getActivitiesByDayShouldReturnActivitiesByDayFromRepository() {
		final List<Activity> activities = Arrays.asList(mock(Activity.class));
		
		when(activityRepository.getActivitiesByDay(startDay, endDay, userId)).thenReturn(activities);
		
		final List<Activity> result = activityService.getActivitiesByDay(startDay, endDay, userId);
		assertThat(result,is(activities));
	}

}
