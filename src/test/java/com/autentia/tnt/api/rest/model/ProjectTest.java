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

package com.autentia.tnt.api.rest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
		
		assertEquals(project.getName(),"Modificar Proyecto");
		assertEquals(project.getOpen(),false);
		//assertTrue(project.getOrganizationId() == 2);
	}
	
}
