package autentia.apiRestTnt.Services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import autentia.apiRestTnt.Model.Project;
import autentia.apiRestTnt.Repository.ProjectRepository;

public class ProjectServiceTest {
	
	private final ProjectRepository projectRepository = mock(ProjectRepository.class);
	private final ProjectService projectService = new ProjectService(projectRepository);
	
	@Test
	public void getProjectByIdShouldReturnProjectFromRepository() {
		final Project projectToReturn = mock(Project.class);
		final Project returnedProject = mock(Project.class);
		
		when(projectRepository.findOne(projectToReturn.getId())).thenReturn(returnedProject);
		
		final Project result = projectService.getProjectById(projectToReturn.getId());
		assertThat(result,is(returnedProject));
	}

}
