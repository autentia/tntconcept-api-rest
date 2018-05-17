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

import autentia.apiRestTnt.services.ProjectRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import autentia.apiRestTnt.controller.ActivityController;
import autentia.apiRestTnt.model.Activity;
import autentia.apiRestTnt.repository.ActivityRepository;
import autentia.apiRestTnt.services.ActivityService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ActivityRestControllerTestIT {

	@Value("${local.server.port}")
	private int port;
	
	private TestRestTemplate restTemplate = new TestRestTemplate("admin","adminadmin");
	
	private ActivityRepository activityRepository;
	
	private final ActivityService activityService = new ActivityService(activityRepository);
	private final ProjectRoleService projectRoleService = new ProjectRoleService((null));
	 
	@Autowired
	private final ActivityController activityController = new ActivityController(activityService,projectRoleService);
	
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
	public void shouldThrowAccessDenied() {
		final Integer id = 11;
		TestRestTemplate restTemplateWithoutAccess = new TestRestTemplate("iperez","holahola");

		ResponseEntity<Activity> activityEntity = restTemplateWithoutAccess.getForEntity(getBaseUrl() + "/api/activity/{activityId}", Activity.class,id);
		assertEquals(HttpStatus.FORBIDDEN, activityEntity.getStatusCode());
	}
	
	@Test
	@Transactional
	public void shouldReturnActivityAfterSaving() {
		Activity activityToSave = new Activity();
		activityToSave.setBillable(true);
		activityToSave.setDescription("Test");
		activityToSave.setDuration(60);

		Activity savedActivity = activityController.addActivity(1,activityToSave);

		final Activity result = restTemplate.postForEntity(getBaseUrl() + "/api/activity?roleId=1",activityToSave,
				Activity.class).getBody();
		
		assertEquals(result.getBillable(),activityToSave.getBillable());
		assertEquals(result.getDescription(),activityToSave.getDescription());
		assertEquals(result.getDuration(),activityToSave.getDuration());
	}

	@Test
	public void shouldHaveEqualDescriptionAfterEditing() {
		Activity activityToEdit = activityController.getActivity(11);
		activityToEdit.setDescription("test");

		Activity editedActivity = activityController.editActivity(1,activityToEdit);

		assertEquals(editedActivity.getDescription(),editedActivity.getDescription());
		assertEquals(editedActivity.getBillable(),editedActivity.getBillable());
	}

	@Test(expected = NullPointerException.class)
	public void shouldBeNullAfterDeleting() throws NullPointerException {
		final Integer id = 11;

		restTemplate.delete(getBaseUrl() + "/api/activity/{activityId}",Activity.class,id);

		activityService.getActivityById(11);

	}


	
	private String getBaseUrl() {
        return "http://localhost:" + port;
    }
	
}
