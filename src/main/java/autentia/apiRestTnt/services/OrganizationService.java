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
import java.util.Optional;

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

	public Optional<Organization> getOrganizationById(Integer organizationId) {
		return organizationRepository.findById(organizationId);
	}

	@Transactional
	public List<Project> getProjectsByOrganizationId(Integer organizationId) {
		Organization organization = getOrganizationById(organizationId)
			.orElseThrow(() -> new IllegalArgumentException(("The requested organizationId ["+organizationId+"] does not exist.")));
		return initializeLazyProjects(organization);
	}

	private List<Project> initializeLazyProjects(Organization organization) {
		organization.getProjects().size();
		return organization.getProjects();
	}

	public List<Organization> getOrganizations(){
		return organizationRepository.findAll();
	}

	public Organization getOrganizationByName(String name) {
		return organizationRepository.findByName(name);
	}
}
