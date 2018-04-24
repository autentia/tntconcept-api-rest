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

package autentia.apiRestTnt.Services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import autentia.apiRestTnt.Model.ProjectRole;
import autentia.apiRestTnt.Repository.ProjectRoleRepository;

import java.util.Optional;

public class ProjectRoleServiceTest {
	
	private final ProjectRoleRepository projectRoleRepository = mock(ProjectRoleRepository.class);
	
	private final ProjectRoleService projectRoleService = new ProjectRoleService(projectRoleRepository);
	
	@Test
	public void getProjectRoleByIdShouldReturnProjectRoleFromRepository() {
		final ProjectRole projectRoleToReturn = mock(ProjectRole.class);
		final ProjectRole returnedProjectRole = mock(ProjectRole.class);
		
		when(projectRoleRepository.findById(projectRoleToReturn.getId())).thenReturn(Optional.ofNullable(returnedProjectRole));
		
		final ProjectRole result = projectRoleService.getProjectRoleById(projectRoleToReturn.getId());
		
		assertThat(result,is(returnedProjectRole));
	}
	
	@Test
	public void getOrganizationByNameShouldReturnOrganizationFromRepository() {
		final String name = "ProjectRole";
		final ProjectRole projectRole = mock(ProjectRole.class);
		
		when(projectRoleRepository.findByName(name)).thenReturn(projectRole);
		
		final ProjectRole result = projectRoleService.getProjectRoleByName(name);
		
		assertThat(result,is(projectRole));
	}
}
