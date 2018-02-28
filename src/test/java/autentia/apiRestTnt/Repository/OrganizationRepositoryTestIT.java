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

package autentia.apiRestTnt.Repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import autentia.apiRestTnt.Model.Organization;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@Transactional
public class OrganizationRepositoryTestIT {
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	Organization organization;
	
	@Before
	public void setUp() {
		this.organization = new Organization();
		
		organization.setName("Nuestra empresa");
		
		organizationRepository.save(organization);
	}
	
	@Test
	public void findOneShouldReturnOrganizationFromDB() {
		final Integer id = 1;
		
		Organization organizationSearched = organizationRepository.findOne(id);
		
		assertEquals(organizationSearched.getName(),"Nuestra empresa");
	}
	
	@Test
	public void saveShouldReturnOrganizationAfterSaving() {
		Organization organizationToSave = new Organization();
		organization.setName("Indefinida");
		
		Organization savedOrganization = organizationRepository.save(organizationToSave);
		
		assertEquals(savedOrganization,organizationToSave);
	}
	
	@Test
	public void deleteShouldDeleteOrganizationFromDB() {
		organizationRepository.delete(this.organization);
		
		Organization result = organizationRepository.findOne(1);
		assertEquals(result,null);
	}
	
	@Test 
	public void findAllShouldReturnAllOrganizationsFromDB() {
		
		List<Organization> allOrganizations = organizationRepository.findAll();
		
		assertEquals(allOrganizations.size(),1);
	}
	
	

}
