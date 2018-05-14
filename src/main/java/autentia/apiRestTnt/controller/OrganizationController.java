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

package autentia.apiRestTnt.controller;

import java.util.ArrayList;
import java.util.List;

import autentia.apiRestTnt.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import autentia.apiRestTnt.model.Organization;
import autentia.apiRestTnt.services.OrganizationService;

@RestController
@RequestMapping("/api")
public class OrganizationController {
	
	private OrganizationService organizationService;

	@Autowired
	public OrganizationController(OrganizationService organizationService) {
		super();
		this.organizationService = organizationService;
	}

	@GetMapping("/organizations")
	public List<Organization> getOrganizations(){
		List<Organization> organizations = organizationService.getOrganizations();
		return organizations;
	}

	@GetMapping("/organization/{id}/projects")
	public List<Project> getOrganizationProjects(@PathVariable Integer id){
		return organizationService.getProjectsByOrganizationId(id);
	}

}
