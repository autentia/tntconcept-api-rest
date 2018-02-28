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

package autentia.apiRestTnt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import autentia.apiRestTnt.Model.Activity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ActivityRestControllerTestIT {

	@Value("${local.server.port}")
	private int port;
	
	private TestRestTemplate restTemplate = new TestRestTemplate("admin","adminadmin");
	
	@Test
	public void shouldReturnActivityDetails() {
		final ResponseEntity<Activity> response = restTemplate.getForEntity(getBaseUrl() + "/activities/{activityId}",
				Activity.class,1);
		
		final Activity activity = response.getBody();
		
		assertTrue(activity.getId() == 1);
		assertEquals(activity.getDescription(), "Prueba");
		assertEquals(activity.getBilliable(), true);
		assertTrue(activity.getDuration() == 360);
	}
	
	@Test
	public void shouldReturnAllActivities() {
		final ResponseEntity<Activity[]> response = restTemplate.getForEntity(getBaseUrl() + "/activities" ,
				Activity[].class);
		
		final Activity[] activities = response.getBody();
		
		assertTrue(activities[0].getId() == 1);
		assertEquals(activities[0].getDescription(), "Prueba");
		assertEquals(activities[0].getBilliable(), true);
		assertTrue(activities[0].getDuration() == 360);
	}
	
	@Test
	public void shouldReturnActivityAfterSaving() {
		Activity activityToSave = new Activity();
		activityToSave.setBilliable(true);
		activityToSave.setDescription("Test");
		activityToSave.setDuration(60);
		
		final ResponseEntity<Activity> response = restTemplate.postForEntity(getBaseUrl() + "/addActivity",activityToSave,
				Activity.class);
		
		Activity savedActivity = response.getBody();
		
		assertEquals(savedActivity,activityToSave);
	}
	
	private String getBaseUrl() {
        return new StringBuilder("http://localhost:").append(port).toString();
    }
	
}
