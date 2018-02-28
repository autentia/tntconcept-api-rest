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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import autentia.apiRestTnt.Model.ActivitiesDay;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ActivitiesDayRestControllerTestIT {
	
	@Value("${local.server.port}")
	private int port;
	
	private TestRestTemplate restTemplate = new TestRestTemplate("admin","adminadmin");
	
	@Test
	public void shouldReturnActivitiesBetweenTwoDatesByWorker() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yy-mm-dd");
		Date startDate = format.parse("2018-02-08");
		Date endDate = format.parse("2018-02-09");
				
		
		final ResponseEntity<ActivitiesDay[]> response = restTemplate.getForEntity(getBaseUrl() + "/activitiesByDates",
				ActivitiesDay[].class,startDate,endDate);
		
		final ActivitiesDay[] activitiesDays = response.getBody();
		
		assertEquals(activitiesDays[0].getDate(),startDate);
		assertTrue(activitiesDays[0].getTotal_hours() == 3);
		
		assertEquals(activitiesDays.length,1);
	}
	
	@Test
	public void shouldReturnActivitiesByWorkerAtDay() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yy-mm-dd");
		Date startDate = format.parse("2018-02-08");
		
		final ResponseEntity<ActivitiesDay> response = restTemplate.getForEntity(getBaseUrl() + "/activitiesByDay",
				ActivitiesDay.class,startDate);
		
		final ActivitiesDay activitiesDay = response.getBody();
		
		assertEquals(activitiesDay.getDate(),startDate);
		assertTrue(activitiesDay.getTotal_hours() == 3);
		
		assertEquals(activitiesDay.getActivities().size(),2);
	}
	
	private String getBaseUrl() {
        return new StringBuilder("http://localhost:").append(port).toString();
    }

}
