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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import autentia.apiRestTnt.Model.Activity;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@Transactional
public class ActivityRepositoryTestIT {

	@Autowired
	private ActivityRepository activityRepository;
	
	@Test
	public void findOneShouldReturnActivityFromDB() {
		final Integer id = 1;
		Activity searchedActivity = activityRepository.findOne(id);
		
		assertTrue(searchedActivity.getId() == 1);
		
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
		
		Activity activityToDelete = activityRepository.findOne(1);
		
		activityRepository.delete(activityToDelete);
		
		Activity result = activityRepository.findOne(1);
		
		assertEquals(result,null);
	}
	
	@Test
	public void getActivitiesByDayShouldReturnActivitiesByWorkerAtDayFromDB() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		final Date startDay = format.parse("2018-02-08");
		final Date endDay = format.parse("2018-02-09");
		final Integer userId = 1;
		
		List<Activity> activitiesByDay = activityRepository.getActivitiesByDay(startDay, endDay, userId);
		
		assertTrue(activitiesByDay.get(0).getUserId() == userId);
		
	}
	
	@Test
	public void calculateHoursShouldReturnWorkedHoursByWorkerAtDayFromDB() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		final Date startDay = format.parse("2018-02-08");
		final Date endDay = format.parse("2018-02-09");
		final Integer userId = 1;
		Integer workedHours = 0;
		
		List<Activity> activitiesByDay = activityRepository.getActivitiesByDay(startDay, endDay, userId);
		for (Activity activity: activitiesByDay) {
			workedHours += activity.getDuration()/60;
		}
		Integer result = activityRepository.calculateHours(startDay, endDay, userId);
		
		assertTrue(workedHours == result);
	}
	
	@Test
	public void findAllShouldReturnAllActivitiesFromDB() {
		Activity activityToCompare = activityRepository.findOne(1);
		
		List<Activity> allActivities = activityRepository.findAll();
		
		assertTrue(allActivities.contains(activityToCompare));
	}
}
