package autentia.apiRestTnt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import autentia.apiRestTnt.Model.Project;
import autentia.apiRestTnt.Services.ProjectService;

@RestController
@RequestMapping("/api")
public class ProjectController {

	private ProjectService projectService;	
	
	@Autowired
	public ProjectController(ProjectService projectService) {
		super();
		this.projectService = projectService;
	}

	@GetMapping("/projects/{projectId}")
	public Project getProjectById(@PathVariable("projectId") Integer projectId) {
		return projectService.getProjectById(projectId);
	}

}
