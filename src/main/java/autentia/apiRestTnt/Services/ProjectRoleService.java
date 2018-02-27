package autentia.apiRestTnt.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autentia.apiRestTnt.Model.ProjectRole;
import autentia.apiRestTnt.Repository.ProjectRoleRepository;

@Service
public class ProjectRoleService {

	private ProjectRoleRepository projectRoleRepository;
	
	@Autowired
	public ProjectRoleService(ProjectRoleRepository projectRoleRepository) {
		super();
		this.projectRoleRepository = projectRoleRepository;
	}

	public ProjectRole getProjectRoleById(Integer projectRoleId) {
		return projectRoleRepository.findOne(projectRoleId);
	}
	
//	public List<ProjectRole> getProjectRolesByProject(Integer projectId){
//		return projectRoleRepository.findByProjectId(projectId);
//	}
}
