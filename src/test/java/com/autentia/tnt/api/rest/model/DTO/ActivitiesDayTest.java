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

package com.autentia.tnt.api.rest.model.DTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.autentia.tnt.api.rest.model.Activity;
import com.autentia.tnt.api.rest.model.DTO.ActivitiesDay;
import org.junit.Before;
import org.junit.Test;

public class ActivitiesDayTest {
	
	private ActivitiesDay activitiesDay;
	
	@Before
	public void setUp() {
		activitiesDay = new ActivitiesDay();
	}
	
	@Test
	public void getAndSetActivitiesDayTest() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		activitiesDay.setDate(format.parse("2018-02-26"));
		activitiesDay.setTotal_hours(10L);
		List<Activity> activities = new ArrayList<>();
		activities.add(new Activity());
		activitiesDay.setActivities(activities);
		
		
		assertEquals(activitiesDay.getDate(),format.parse("2018-02-26"));
		assertEquals(activitiesDay.getActivities().size(),1);
		assertTrue(activitiesDay.getTotal_hours() == 10);
	}

}
