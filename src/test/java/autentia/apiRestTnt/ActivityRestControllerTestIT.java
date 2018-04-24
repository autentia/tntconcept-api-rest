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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import autentia.apiRestTnt.Controller.ActivityController;
import autentia.apiRestTnt.Model.Activity;
import autentia.apiRestTnt.Repository.ActivityRepository;
import autentia.apiRestTnt.Services.ActivityService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class ActivityRestControllerTestIT {

	@Value("${local.server.port}")
	private int port;
	
	private TestRestTemplate restTemplate = new TestRestTemplate("admin","adminadmin");
	
	private ActivityRepository activityRepository;
	
	private final ActivityService activityService = new ActivityService(activityRepository);
	 
	@Autowired
	private final ActivityController activityController = new ActivityController(activityService);
	
	@Test
	public void shouldReturnActivityDetails() {
		final Integer id = 1;
		Activity activity = activityController.getActivity(id);
		final ResponseEntity<Activity> response = restTemplate.getForEntity(getBaseUrl() + "/api/activities/{activityId}",
				Activity.class,id);
		
		final Activity result = response.getBody();

		assertSame(activity.getId(), result.getId());
		assertEquals(activity.getDescription(), result.getDescription());
		assertEquals(activity.getBilliable(), result.getBilliable());
		assertEquals(activity.getDuration().intValue(), result.getDuration().intValue());
	}
	
	@Test
	public void shouldReturnActivityAfterSaving() {
		Activity activityToSave = new Activity();
		activityToSave.setBilliable(true);
		activityToSave.setDescription("Test");
		activityToSave.setDuration(60);
		
		Activity savedActivity = activityController.addActivity(activityToSave);
		
		final ResponseEntity<Activity> response = restTemplate.postForEntity(getBaseUrl() + "/api/addActivity",activityToSave,
				Activity.class);
		
		final Activity result = response.getBody();
		
		assertEquals(result.getBilliable(),savedActivity.getBilliable());
		assertEquals(result.getDescription(),savedActivity.getDescription());
		assertEquals(result.getDuration(),savedActivity.getDuration());
	}
	
	private String getBaseUrl() {
        return "http://localhost:" + port;
    }
	
}
