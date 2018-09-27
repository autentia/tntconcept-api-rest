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

package com.autentia.tnt.api.rest.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.autentia.tnt.api.rest.model.ProjectRole;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@Transactional
public class ProjectRoleRepositoryTestIT {
	
	@Autowired
	ProjectRoleRepository projectRoleRepository;
	
	@Test
	public void findOneShouldReturnProjectRoleFromDB() {
		final Integer id = 1;
		
		ProjectRole searchedProjectRole = projectRoleRepository.findById(id).get();

		assertTrue(searchedProjectRole.getId() == id);
	}
	
	@Test
	public void saveShouldReturnProjectRoleAfterSaving() {
		ProjectRole projectRoleToSave = new ProjectRole();
		projectRoleToSave.setName("PermisoExtraordinario");
		
		ProjectRole savedProjectRole = projectRoleRepository.save(projectRoleToSave);
		
		assertEquals(savedProjectRole,projectRoleToSave);
		
	}
	
	@Test
	public void deleteShouldDeleteProjectRoleFromDB() {
		final String name = "Vacaciones";
		
		ProjectRole projectRoleToDelete = projectRoleRepository.findByName(name);
		
		projectRoleRepository.delete(projectRoleToDelete);
		ProjectRole result = projectRoleRepository.findByName(name);
		
		assertEquals(result,null);
	}
	
	@Test
	public void findByNameShouldReturnProjectRoleFromDB() {
		final String name = "Vacaciones";
		
		ProjectRole searchedProjectRole = projectRoleRepository.findByName(name);
		
		assertEquals(searchedProjectRole.getName(),"Vacaciones");
	}
	

}
