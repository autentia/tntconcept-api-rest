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

import java.util.List;

import autentia.apiRestTnt.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autentia.apiRestTnt.model.Organization;
import autentia.apiRestTnt.repository.OrganizationRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationService {

	private OrganizationRepository organizationRepository;
	
	@Autowired
	public OrganizationService(OrganizationRepository organizationRepository) {
		super();
		this.organizationRepository = organizationRepository;
	}

//	public Organization getOrganizationById(Integer organizationId) {
//		return organizationRepository.findOne(organizationId);
//	}
	@Transactional
	public List<Organization> getOrganizations(){
		List<Organization> orgs = organizationRepository.findAll();
		fillProjects(orgs);
		return orgs;
	}

	private void fillProjects(List<Organization> orgs) {
		for (Organization o: orgs) {
			List<Project> projs = o.getProjects();
			fillProjectRoles(projs);
		}
	}

	private void fillProjectRoles(List<Project> projs) {
		for (Project p: projs) {
			p.getProjectRoles().size();
		}
	}

	public Organization getOrganizationByName(String name) {
		return organizationRepository.findByName(name);
	}
}
