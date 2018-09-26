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


package com.autentia.tnt.api.rest.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.autentia.tnt.api.rest.model.ProjectRole;
import com.autentia.tnt.api.rest.services.ProjectRoleService;

public class ProjectRoleControllerTest {
	
	private final ProjectRoleService projectRoleService = mock(ProjectRoleService.class);
	
	private final ProjectRoleController projectRoleController = new ProjectRoleController(projectRoleService);
	
	@Test
	public void getProjectRoleByIdShouldReturnProjectRoleFromService() {
		final ProjectRole projectRoleToReturn = mock(ProjectRole.class);
		final ProjectRole returnedProjectRole = mock(ProjectRole.class);
		
		when(projectRoleService.getProjectRoleById(projectRoleToReturn.getId())).thenReturn(returnedProjectRole);
		
		final ProjectRole result = projectRoleController.getProjectRoleById(projectRoleToReturn.getId());
		
		assertThat(result,is(returnedProjectRole));
	}

}
