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

package autentia.apiRestTnt.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

public class ActivityTest {
	
	private Activity activity;
	
	@Before
	public void setUp() {
		this.activity = new Activity();
	}
	
	@Test
	public void getAndSetActivityTest() throws ParseException {
		activity.setBillable(true);
		activity.setDescription("Description");
		activity.setDuration(10);
		activity.setRoleId(1);
		activity.setUserId(1);
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		activity.setStartDate(format.parse("2018-02-26"));
		
		
		assertEquals(activity.getBillable(),true);
		assertEquals(activity.getDescription(),"Description");
		assertTrue(activity.getDuration() == 10);
		assertTrue(activity.getRoleId() == 1);
		assertTrue(activity.getUserId() == 1);
		assertEquals(activity.getStartDate(),format.parse("2018-02-26"));
		
	}

}
