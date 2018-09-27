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

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.autentia.tnt.api.rest.controller.ActivitiesDayController;
import com.autentia.tnt.api.rest.model.ActivitiesDay;
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

	private ActivityRepository activityRepository;

	private UserRepository userRepository;
	
	private final UserService userService = new UserService(userRepository);
	private final ActivityService activityService = new ActivityService(activityRepository);

	@Autowired
	private final ActivitiesDayController activitiesDayController = new ActivitiesDayController(activityService,userService);
	
	@Test
	public void shouldReturnActivitiesBetweenTwoDatesByWorker() throws ParseException {
		LocalDateTime startDate = LocalDateTime.of(2018,2,8,00,00,00);
		LocalDateTime endDate = LocalDateTime.of(2018,2,9,00,00,00);

		SecurityContext secContext = mock(SecurityContext.class);
		Authentication auth = mock(Authentication.class);
		UserDetails userDetails = mock(UserDetails.class);
		final String login = "admin";
		SecurityContextHolder.setContext(secContext);

		when(secContext.getAuthentication()).thenReturn(auth);
		when(auth.getPrincipal()).thenReturn(userDetails);
		when(userDetails.getUsername()).thenReturn(login);

		List<ActivitiesDay> activitiesDay = activitiesDayController.getActivitiesByDates(startDate, endDate);

		final ResponseEntity<ActivitiesDay[]> response = restTemplate.getForEntity(getBaseUrl() + "/api/activitiesByDates?startDate=2018-02-08T00:00:00&endDate=2018-02-09T00:00:00",
				ActivitiesDay[].class);

		final ActivitiesDay[] result = response.getBody();


		assertEquals(result[0].getTotal_hours(), activitiesDay.get(0).getTotal_hours());
		
		assertEquals(result.length,activitiesDay.size());
	}
	
	@Test
	public void shouldReturnActivitiesByWorkerAtDay() throws ParseException {
		LocalDateTime date = LocalDateTime.of(2018,2,8,00,00,00);

		SecurityContext secContext = mock(SecurityContext.class);
		Authentication auth = mock(Authentication.class);
		UserDetails userDetails = mock(UserDetails.class);
		final String login = "admin";
		SecurityContextHolder.setContext(secContext);
		
		when(secContext.getAuthentication()).thenReturn(auth);
		when(auth.getPrincipal()).thenReturn(userDetails);
		when(userDetails.getUsername()).thenReturn(login);
		
		ActivitiesDay activitiesDay = activitiesDayController.getActivitiesByDay(date);
		
		final ResponseEntity<ActivitiesDay> response = restTemplate.getForEntity(getBaseUrl() + "/api/activitiesByDay?date=2018-02-08T00:00",
				ActivitiesDay.class);
		
		final ActivitiesDay result = response.getBody();

		assertEquals(result.getTotal_hours(), activitiesDay.getTotal_hours());
		assertEquals(result.getActivities().size(),activitiesDay.getActivities().size());
		
	}
	
	private String getBaseUrl() {
        return "http://localhost:" + port;
    }

}
