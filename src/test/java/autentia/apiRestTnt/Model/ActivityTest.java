package autentia.apiRestTnt.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

public class ActivityTest {
	
	private Activity activity;
	
	@Before
	public void setUp() {
		this.activity = new Activity();
	}
	
	@Test
	public void getAndSetActivityTest() throws ParseException {
		activity.setBilliable(true);
		activity.setDescription("Description");
		activity.setDuration(10);
		activity.setRoleId(1);
		activity.setUserId(1);
		SimpleDateFormat format = new SimpleDateFormat("yy-mm-dd");
		activity.setStartDate(format.parse("2018-02-26"));
		
		
		assertEquals(activity.getBilliable(),true);
		assertEquals(activity.getDescription(),"Description");
		assertTrue(activity.getDuration() == 10);
		assertTrue(activity.getRoleId() == 1);
		assertTrue(activity.getUserId() == 1);
		assertEquals(activity.getStartDate(),format.parse("2018-02-26"));
		
	}

}
