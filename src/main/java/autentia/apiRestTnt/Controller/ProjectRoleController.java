package autentia.apiRestTnt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import autentia.apiRestTnt.Model.ProjectRole;
import autentia.apiRestTnt.Services.ProjectRoleService;

@RestController
@RequestMapping("/api")
public class ProjectRoleController {
	
	private ProjectRoleService projectRoleService;
	
	@Autowired
	public ProjectRoleController(ProjectRoleService projectRoleService) {
		super();
		this.projectRoleService = projectRoleService;
	}

	@GetMapping("/projectsRole/{projectRoleId}")
	public ProjectRole getProjectRoleById(@PathVariable("projectRoleId")Integer projectRoleId) {
		return projectRoleService.getProjectRoleById(projectRoleId);
	}
	

}
