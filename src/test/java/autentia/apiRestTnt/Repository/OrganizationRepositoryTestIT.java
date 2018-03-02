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
import static org.junit.Assert.assertTrue;

import java.util.List;

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
	
	@Test
	public void findOneShouldReturnOrganizationFromDB() {
		final Integer id = 1;
		
		Organization organizationSearched = organizationRepository.findOne(id);
		
		assertTrue(organizationSearched.getId() == 1);
	}
	
	@Test
	public void saveShouldReturnOrganizationAfterSaving() {
		Organization organizationToSave = new Organization();
		organizationToSave.setName("Indefinida");
		
		Organization savedOrganization = organizationRepository.save(organizationToSave);
		
		assertEquals(savedOrganization,organizationToSave);
	}
	
	@Test
	public void deleteShouldDeleteOrganizationFromDB() {
		final String name = "Indefinida";
		
		Organization organizationToDelete = organizationRepository.findByName(name);
		
		organizationRepository.delete(organizationToDelete);
		
		Organization result = organizationRepository.findByName(name);
		
		assertEquals(result,null);
	}
	
	@Test 
	public void findAllShouldReturnAllOrganizationsFromDB() {
		final String name = "Indefinida";
		
		Organization organizationToCompare = organizationRepository.findByName(name);
		List<Organization> allOrganizations = organizationRepository.findAll();
		
		assertTrue(allOrganizations.contains(organizationToCompare));
	}
	
	@Test
	public void findByNameShouldReturnOrganizationFromDB() {
		final String name = "Nuestra empresa";
		
		Organization organizationSearched = organizationRepository.findByName(name);
		
		assertEquals(organizationSearched.getName(),"Nuestra empresa");
	}
	
	

}
