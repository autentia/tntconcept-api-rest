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


package com.autentia.tnt.api.rest.services;

import com.autentia.tnt.api.rest.model.ProjectRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autentia.tnt.api.rest.model.Project;
import com.autentia.tnt.api.rest.repository.ProjectRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	@Transactional
	public List<ProjectRole> getProjectRolesByProjectId(Integer projectId) {
		Project project = getProjectById(projectId);
		return initializeLazyProjects(project);
	}

	private List<ProjectRole> initializeLazyProjects(Project project) {
		project.getProjectRoles().size();
		return project.getProjectRoles();
	}

	public Project getProjectByName(String name) {
		return projectRepository.findByName(name);
	}

}
