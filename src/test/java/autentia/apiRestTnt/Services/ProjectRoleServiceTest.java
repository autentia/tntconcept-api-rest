package autentia.apiRestTnt.Services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import autentia.apiRestTnt.Model.ProjectRole;
import autentia.apiRestTnt.Repository.ProjectRoleRepository;

public class ProjectRoleServiceTest {
	
	private final ProjectRoleRepository projectRoleRepository = mock(ProjectRoleRepository.class);
	
	private final ProjectRoleService projectRoleService = new ProjectRoleService(projectRoleRepository);
	
	@Test
	public void getProjectRoleByIdShouldReturnProjectRoleFromRepository() {
		final ProjectRole projectRoleToReturn = mock(ProjectRole.class);
		final ProjectRole returnedProjectRole = mock(ProjectRole.class);
		
		when(projectRoleRepository.findOne(projectRoleToReturn.getId())).thenReturn(returnedProjectRole);
		
		final ProjectRole result = projectRoleService.getProjectRoleById(projectRoleToReturn.getId());
		
		assertThat(result,is(returnedProjectRole));
	}
}
