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

import com.autentia.tnt.api.rest.model.DTO.ActivityDTO;
import com.autentia.tnt.api.rest.repository.UserRepository;
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
import static org.mockito.Mockito.mock;


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

	private final UserService userService = new UserService(mock(UserRepository.class));

	@Autowired
	private final ActivityController activityController = new ActivityController(activityService,userService);
	
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
		ActivityDTO activityDTOtoSave = new ActivityDTO();
		activityDTOtoSave.setBillable(true);
		activityDTOtoSave.setDescription("Test");
		activityDTOtoSave.setDuration(60);
		activityDTOtoSave.setRoleId(1);

		final Activity result = restTemplate.postForEntity(getBaseUrl() + "/api/activity",activityDTOtoSave,
				Activity.class).getBody();
		
		assertEquals(result.getBillable(),activityDTOtoSave.getBillable());
		assertEquals(result.getDescription(),activityDTOtoSave.getDescription());
		assertEquals(result.getDuration(),activityDTOtoSave.getDuration());

		assertNotNull(activityService.getActivityById(result.getId()));
	}

	@Test
	public void shouldHaveEqualDescriptionAfterEditing() {
		ActivityDTO activityDTOtoEdit = new ActivityDTO();
		activityDTOtoEdit.setId(11);
		activityDTOtoEdit.setBillable(true);
		activityDTOtoEdit.setDescription("test");
		activityDTOtoEdit.setDuration(60);
		activityDTOtoEdit.setRoleId(1);

		final Activity result = restTemplate.postForEntity(getBaseUrl() + "/api/activity",activityDTOtoEdit,
				Activity.class).getBody();


		assertEquals(result.getBillable(),activityDTOtoEdit.getBillable());
		assertEquals(result.getDescription(),activityDTOtoEdit.getDescription());
		assertEquals(result.getDuration(),activityDTOtoEdit.getDuration());

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
