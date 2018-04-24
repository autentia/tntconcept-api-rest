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

import autentia.apiRestTnt.Model.Project;
import autentia.apiRestTnt.Repository.ProjectRepository;

import java.util.Optional;

public class ProjectServiceTest {
	
	private final ProjectRepository projectRepository = mock(ProjectRepository.class);
	private final ProjectService projectService = new ProjectService(projectRepository);
	
	@Test
	public void getProjectByIdShouldReturnProjectFromRepository() {
		final Project projectToReturn = mock(Project.class);
		final Project returnedProject = mock(Project.class);
		
		when(projectRepository.findById(projectToReturn.getId())).thenReturn(Optional.ofNullable(returnedProject));
		
		final Project result = projectService.getProjectById(projectToReturn.getId());
		assertThat(result,is(returnedProject));
	}
	
	@Test
	public void getOrganizationByNameShouldReturnOrganizationFromRepository() {
		final String name = "Project";
		final Project project = mock(Project.class);
		
		when(projectRepository.findByName(name)).thenReturn(project);
		
		final Project result = projectService.getProjectByName(name);
		
		assertThat(result,is(project));
	}

}
