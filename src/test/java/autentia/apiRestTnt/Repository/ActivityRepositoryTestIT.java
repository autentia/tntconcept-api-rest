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

package autentia.apiRestTnt.Repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import autentia.apiRestTnt.Model.Activity;
import autentia.apiRestTnt.Model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@Transactional
public class ActivityRepositoryTestIT {

	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private Activity activity;
	
	private User user;
	
	@Before
	public void setUp() throws ParseException {
		this.activity = new Activity();
		this.activity.setDescription("Prueba");
		this.activity.setUserId(1);
		SimpleDateFormat format = new SimpleDateFormat("yy-mm-dd");
		final Date startDay = format.parse("2018-02-08");
		this.activity.setStartDate(startDay);
		this.activity.setDuration(60);
		
		this.user = new User();
		this.user.setLogin("login");
		this.user.setPassword("password");
		
		userRepository.save(user);
		activityRepository.save(activity);
	}
	
	@Test
	public void findOneShouldReturnActivityFromDB() {
		final Integer id = 1;
		Activity searchedActivity = activityRepository.findOne(id);
		
		assertEquals(searchedActivity.getDescription(),"Prueba");
		
	}
	
	@Test
	public void saveShouldReturnActivityAfterSaving() {
		Activity activityToSave = new Activity();
		activityToSave.setDescription("Horas facturables");
		
		Activity savedActivity = activityRepository.save(activityToSave);
		
		assertEquals(savedActivity,activityToSave);
	}
	
	@Test
	public void deleteShouldDeleteActivityFromDB() {
		activityRepository.delete(this.activity);
		
		Activity result = activityRepository.findOne(1);
		
		assertEquals(result,null);
	}
	
	@Test
	public void getActivitiesByDayShouldReturnActivitiesByWorkerAtDayFromDB() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yy-mm-dd");
		final Date startDay = format.parse("2018-02-08");
		final Date endDay = format.parse("2018-02-09");
		List<Activity> activitiesByDay = activityRepository.getActivitiesByDay(startDay, endDay, 1);
		
		assertEquals(activitiesByDay.size(),1);
		
	}
	
	@Test
	public void calculateHoursShouldReturnWorkedHoursByWorkerAtDayFromDB() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yy-mm-dd");
		final Date startDay = format.parse("2018-02-08");
		final Date endDay = format.parse("2018-02-09");
		Integer workedHours = activityRepository.calculateHours(startDay, endDay, 1);
		
		assertTrue(workedHours == 1);
	}
	
	@Test
	public void findAllShouldReturnAllActivitiesFromDB() {
		List<Activity> allActivities = activityRepository.findAll();
		
		assertEquals(allActivities.size(),1);
	}
}
