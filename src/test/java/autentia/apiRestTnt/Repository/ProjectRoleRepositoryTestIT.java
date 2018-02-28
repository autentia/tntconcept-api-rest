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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import autentia.apiRestTnt.Model.ProjectRole;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@Transactional
public class ProjectRoleRepositoryTestIT {
	
	@Autowired
	ProjectRoleRepository projectRoleRepository;
	
	private ProjectRole projectRole;
	
	@Before
	public void setUp() {
		projectRole = new ProjectRole();
		projectRole.setName("Vacaciones");
		
		projectRoleRepository.save(projectRole);
	}
	
	@Test
	public void findOneShouldReturnProjectRoleFromDB() {
		final Integer id = 1; 
		
		ProjectRole searchedProjectRole = projectRoleRepository.findOne(id);
		
		assertEquals(searchedProjectRole.getName(),"Vacaciones");
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
		projectRoleRepository.delete(projectRole);
		ProjectRole result = projectRoleRepository.findOne(1);
		
		assertEquals(result,null);
	}
	

}
