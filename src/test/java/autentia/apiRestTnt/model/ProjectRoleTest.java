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

package autentia.apiRestTnt.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ProjectRoleTest {
	
	private ProjectRole projectRole;
	
	@Before
	public void setUp() {
		projectRole = new ProjectRole();
	}
	
	@Test
	public void getAndSetProjectRoleTest() {
		projectRole.setName("Role");
		//projectRole.setProjectId(1);
		
		assertEquals(projectRole.getName(),"Role");
		//assertTrue(projectRole.getProjectId() == 1);
	}

}
