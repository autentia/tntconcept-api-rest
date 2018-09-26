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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autentia.tnt.api.rest.model.ProjectRole;
import com.autentia.tnt.api.rest.repository.ProjectRoleRepository;

@Service
public class ProjectRoleService {

	private ProjectRoleRepository projectRoleRepository;
	
	@Autowired
	public ProjectRoleService(ProjectRoleRepository projectRoleRepository) {
		super();
		this.projectRoleRepository = projectRoleRepository;
	}

	public ProjectRole getProjectRoleById(Integer projectRoleId) {
		return projectRoleRepository.findById(projectRoleId)
				.orElseThrow(()->new IllegalArgumentException("The requested projectRoleId ["+projectRoleId+"] does not exist."));
	}
	
	public ProjectRole getProjectRoleByName(String name) {
		return projectRoleRepository.findByName(name);
	}
	
//	public List<ProjectRole> getProjectRolesByProject(Integer projectId){
//		return projectRoleRepository.findByProjectId(projectId);
//	}
}
