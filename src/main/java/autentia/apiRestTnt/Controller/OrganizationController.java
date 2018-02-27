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

package autentia.apiRestTnt.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import autentia.apiRestTnt.Model.Organization;
import autentia.apiRestTnt.Services.OrganizationService;

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

}
