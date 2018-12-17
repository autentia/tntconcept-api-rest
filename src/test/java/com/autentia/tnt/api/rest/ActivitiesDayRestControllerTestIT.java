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

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import com.autentia.tnt.api.rest.services.ProjectRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.autentia.tnt.api.rest.controller.ActivitiesDayController;
import com.autentia.tnt.api.rest.model.DTO.ActivitiesDay;
import com.autentia.tnt.api.rest.repository.ActivityRepository;
import com.autentia.tnt.api.rest.repository.UserRepository;
import com.autentia.tnt.api.rest.services.ActivityService;
import com.autentia.tnt.api.rest.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class ActivitiesDayRestControllerTestIT {
	
	@Value("${local.server.port}")
	private int port;
	
	private TestRestTemplate restTemplate = new TestRestTemplate("admin","adminadmin");

	
	@Test
	public void shouldReturnActivitiesBetweenTwoDatesByWorker() throws ParseException {

		final ResponseEntity<ActivitiesDay[]> response = restTemplate.getForEntity(getBaseUrl() + "/api/activities?startDate=2018-02-08&endDate=2018-02-09",
				ActivitiesDay[].class);


		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void shouldReturnActivitiesByWorkerAtDay() throws ParseException {

		final ResponseEntity<ActivitiesDay> response = restTemplate.getForEntity(getBaseUrl() + "/api/activitiesByDay?date=2018-02-08T00:00",
				ActivitiesDay.class);


		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	private String getBaseUrl() {
        return "http://localhost:" + port;
    }

}
