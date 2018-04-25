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
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import autentia.apiRestTnt.Controller.ActivityController;
import autentia.apiRestTnt.Model.Activity;
import autentia.apiRestTnt.Repository.ActivityRepository;
import autentia.apiRestTnt.Services.ActivityService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
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
		final Activity result = restTemplate.getForEntity(getBaseUrl() + "/api/activity/{activityId}",
				Activity.class,id).getBody();

		assertSame(activity.getId(), result.getId());
		assertEquals(activity.getDescription(), result.getDescription());
		assertEquals(activity.getBillable(), result.getBillable());
		assertEquals(activity.getDuration().intValue(), result.getDuration().intValue());
	}
	
	@Test
	@Transactional
	public void shouldReturnActivityAfterSaving() {
		Activity activityToSave = new Activity();
		activityToSave.setBillable(true);
		activityToSave.setDescription("Test");
		activityToSave.setDuration(60);

		Activity savedActivity = activityController.addActivity(activityToSave);

		final Activity result = restTemplate.postForEntity(getBaseUrl() + "/api/activity",activityToSave,
				Activity.class).getBody();
		
		assertEquals(result.getBillable(),activityToSave.getBillable());
		assertEquals(result.getDescription(),activityToSave.getDescription());
		assertEquals(result.getDuration(),activityToSave.getDuration());
	}

	@Test
	public void shouldHaveEqualDescriptionAfterEditing() {
		Activity activityToEdit = activityController.getActivity(11);
		activityToEdit.setDescription("test");

		Activity editedActivity = activityController.editActivity(activityToEdit);

		assertEquals(editedActivity.getDescription(),editedActivity.getDescription());
		assertEquals(editedActivity.getBillable(),editedActivity.getBillable());
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void shouldThrowExceptionAfterDeleting() throws InvalidDataAccessApiUsageException {
		final Integer id = 11;

		activityController.deleteActivity(id);

		restTemplate.delete(getBaseUrl() + "/api/activity/{activityId}",Activity.class,id);
	}
	
	private String getBaseUrl() {
        return "http://localhost:" + port;
    }
	
}
