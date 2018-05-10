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


package autentia.apiRestTnt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autentia.apiRestTnt.model.Project;
import autentia.apiRestTnt.repository.ProjectRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {
	
	private ProjectRepository projectRepository;
	
	@Autowired
	public ProjectService(ProjectRepository projectRepository) {
		super();
		this.projectRepository = projectRepository;
	}

	public Project getProjectById(Integer projectId) {
		return projectRepository.findById(projectId)
				.orElseThrow(()->new IllegalArgumentException("The requested projectId ["+projectId+"] does not exist."));
	}

	public Project getProjectByName(String name) {
		return projectRepository.findByName(name);
	}
	
//	public List<Project> getOpenProjectsByOrganization(Integer organizationId){
//		return projectRepository.getOpenProjectsByOrganization(organizationId);
//	}

}
