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

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ProjectTest {
	
	private Project project;
	
	@Before
	public void setUp() {
		project = new Project();
	}
	
	@Test
	public void getAndSetProjectTest() {
		project.setName("Modificar Proyecto");
		project.setOpen(false);
		//project.setOrganizationId(2);
		List<ProjectRole> projectRoleList = new ArrayList<>();
		ProjectRole projectRole = new ProjectRole();
		projectRole.setName("Test");
		projectRoleList.add(projectRole);
		project.setProjectRoles(projectRoleList);
		
		assertEquals(project.getName(),"Modificar Proyecto");
		assertEquals(project.getOpen(),false);
		//assertTrue(project.getOrganizationId() == 2);
		assertEquals(project.getProjectRoles().size(),projectRoleList.size());
	}
	
}
