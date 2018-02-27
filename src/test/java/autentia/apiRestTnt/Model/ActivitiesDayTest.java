package autentia.apiRestTnt.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ActivitiesDayTest {
	
	private ActivitiesDay activitiesDay;
	
	@Before
	public void setUp() {
		activitiesDay = new ActivitiesDay();
	}
	
	@Test
	public void getAndSetActivitiesDayTest() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yy-mm-dd");
		activitiesDay.setDate(format.parse("2018-02-26"));
		activitiesDay.setTotal_hours(10);
		List<Activity> activities = new ArrayList<>();
		activities.add(new Activity());
		activitiesDay.setActivities(activities);
		
		
		assertEquals(activitiesDay.getDate(),format.parse("2018-02-26"));
		assertEquals(activitiesDay.getActivities().size(),1);
		assertTrue(activitiesDay.getTotal_hours() == 10);
	}

}
