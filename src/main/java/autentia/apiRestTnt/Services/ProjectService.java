package autentia.apiRestTnt.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autentia.apiRestTnt.Model.Project;
import autentia.apiRestTnt.Repository.ProjectRepository;

@Service
public class ProjectService {
	
	private ProjectRepository projectRepository;
	
	@Autowired
	public ProjectService(ProjectRepository projectRepository) {
		super();
		this.projectRepository = projectRepository;
	}

	public Project getProjectById(Integer projectId) {
		return projectRepository.findOne(projectId);
	}
	
//	public List<Project> getOpenProjectsByOrganization(Integer organizationId){
//		return projectRepository.getOpenProjectsByOrganization(organizationId);
//	}

}
