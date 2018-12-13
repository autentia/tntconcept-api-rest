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

package com.autentia.tnt.api.rest.controller;

import java.util.List;

import com.autentia.tnt.api.rest.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autentia.tnt.api.rest.model.Organization;
import com.autentia.tnt.api.rest.services.OrganizationService;

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
		return organizationService.getOrganizations();
	}

	@GetMapping("/organization/{id}/projects")
	public List<Project> getOrganizationProjects(@PathVariable Integer id){
		return organizationService.getOpenProjectsByOrganizationId(id);
	}

}
