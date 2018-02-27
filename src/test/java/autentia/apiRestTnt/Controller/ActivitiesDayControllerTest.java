package autentia.apiRestTnt.Controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
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

import autentia.apiRestTnt.Model.ActivitiesDay;
import autentia.apiRestTnt.Model.Activity;
import autentia.apiRestTnt.Services.ActivityService;

public class ActivitiesDayControllerTest {
	
	private final ActivityService activityService = mock(ActivityService.class);
	
	private final ActivitiesDayController activitiesDayController = new ActivitiesDayController(activityService);
	
	private LocalDateTime startDay;
	private LocalDateTime endDay;
	
	@Before
	public void setUp() throws ParseException {
		this.startDay = LocalDateTime.of(2018,2,8,00,00,00);
		this.endDay = LocalDateTime.of(2018,2,9,00,00,00);
	}
	
	@Test
	public void getActivitiesByDayShouldReturnActivitiesDay() {
		Integer workedHours = 10;
		List<Activity> activities = Arrays.asList(mock(Activity.class));
		ActivitiesDay returnedActivitiesDay = new ActivitiesDay();
		
		
		when(activityService.calculateHours(any(Date.class), any(Date.class), any(Integer.class))).thenReturn(workedHours);
		when(activityService.getActivitiesByDay(any(Date.class), any(Date.class), any(Integer.class))).thenReturn(activities);
		
		returnedActivitiesDay.setActivities(activities);
		returnedActivitiesDay.setTotal_hours(workedHours);
		returnedActivitiesDay.setDate(Date.from(startDay.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		final ActivitiesDay result = activitiesDayController.getActivitiesByDay(startDay);
		assertTrue(result.equals(returnedActivitiesDay)); 		 
	}
	
	@Test
	public void getActivitiesByDatesShouldReturnActivitiesDayBetweenTwoDates() {
		Integer workedHours = 10;
		List<Activity> activities = Arrays.asList(mock(Activity.class));
		
		when(activityService.calculateHours(any(Date.class), any(Date.class), any(Integer.class))).thenReturn(workedHours);
		when(activityService.getActivitiesByDay(any(Date.class), any(Date.class), any(Integer.class))).thenReturn(activities);
		
		
		final List<ActivitiesDay> result = activitiesDayController.getActivitiesByDates(startDay,endDay);
		
		assertTrue(result.size() == 2);
	}
	

}
