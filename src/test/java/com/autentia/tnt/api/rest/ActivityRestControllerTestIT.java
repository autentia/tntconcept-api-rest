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

package com.autentia.tnt.api.rest;

import com.autentia.tnt.api.rest.services.ProjectRoleService;
import com.autentia.tnt.api.rest.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.autentia.tnt.api.rest.controller.ActivityController;
import com.autentia.tnt.api.rest.model.Activity;
import com.autentia.tnt.api.rest.repository.ActivityRepository;
import com.autentia.tnt.api.rest.services.ActivityService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ActivityRestControllerTestIT {

	@Value("${local.server.port}")
	private int port;
	
	private TestRestTemplate restTemplate = new TestRestTemplate("admin","adminadmin");

	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private ActivityService activityService;

	private final UserService userService = new UserService(null);
	private final ProjectRoleService projectRoleService = new ProjectRoleService((null));
	 
	@Autowired
	private final ActivityController activityController = new ActivityController(activityService,projectRoleService,userService);
	
	@Test
	public void shouldReturnActivityDetails() {
		final Integer id = 12;

		final Activity result = restTemplate.getForEntity(getBaseUrl() + "/api/activity/{activityId}",
				Activity.class,id).getBody();

		assertSame(12, result.getId());
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

		final Activity result = restTemplate.postForEntity(getBaseUrl() + "/api/activity?roleId=1",activityToSave,
				Activity.class).getBody();
		
		assertEquals(result.getBillable(),activityToSave.getBillable());
		assertEquals(result.getDescription(),activityToSave.getDescription());
		assertEquals(result.getDuration(),activityToSave.getDuration());

		assertNotNull(activityService.getActivityById(result.getId()));
	}

	@Test
	public void shouldHaveEqualDescriptionAfterEditing() {
		Activity activityToEdit = activityService.getActivityById(11);
		activityToEdit.setDescription("test");

		activityService.saveActivity(activityToEdit);

		Activity editedActivity = activityController.editActivity(6,activityToEdit);

		assertEquals(editedActivity.getDescription(),"test");
	}

	@Test(expected = IllegalArgumentException.class)
	@Transactional
	public void shouldBeNullAfterDeleting() {
		final Integer id = 11;

		restTemplate.delete(getBaseUrl() + "/api/activity/"+id);

		activityService.getActivityById(11);
	}


	
	private String getBaseUrl() {
        return "http://localhost:" + port;
    }
	
}