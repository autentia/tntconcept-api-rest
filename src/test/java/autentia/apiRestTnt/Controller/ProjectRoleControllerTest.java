package autentia.apiRestTnt.Controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import autentia.apiRestTnt.Model.ProjectRole;
import autentia.apiRestTnt.Services.ProjectRoleService;

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
