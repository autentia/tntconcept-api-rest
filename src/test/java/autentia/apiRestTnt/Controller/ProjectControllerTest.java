package autentia.apiRestTnt.Controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import autentia.apiRestTnt.Model.Project;
import autentia.apiRestTnt.Services.ProjectService;

public class ProjectControllerTest {
	
	private final ProjectService projectService = mock(ProjectService.class);
	
	private final ProjectController projectController = new ProjectController(projectService);
	
	@Test
	public void getProjectByIdShouldReturnProjectFromService() {
		final Project projectToReturn = mock(Project.class);
		final Project returnedProject = mock(Project.class);
		
		when(projectService.getProjectById(projectToReturn.getId())).thenReturn(returnedProject);
		
		final Project result = projectController.getProjectById(projectToReturn.getId());
		assertThat(result,is(returnedProject));
	}

	
}
