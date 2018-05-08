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

package autentia.apiRestTnt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import autentia.apiRestTnt.controller.ProjectRoleController;
import autentia.apiRestTnt.model.ProjectRole;
import autentia.apiRestTnt.repository.ProjectRoleRepository;
import autentia.apiRestTnt.services.ProjectRoleService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class ProjectRoleRestControllerTestIT {
	@Value("${local.server.port}")
	private int port;
	
	private TestRestTemplate restTemplate = new TestRestTemplate("admin","adminadmin");
	
	private ProjectRoleRepository projectRoleRepository;
	
	private final ProjectRoleService projectRoleService = new ProjectRoleService(projectRoleRepository);
	
	@Autowired
	private final ProjectRoleController projectRoleController = new ProjectRoleController(projectRoleService);
	
	@Test
	public void shouldReturnProjectRoleDetails() {
		final Integer id = 1;
		ProjectRole projectRole = projectRoleController.getProjectRoleById(id);
		final ResponseEntity<ProjectRole> response = restTemplate.getForEntity(getBaseUrl() + "/api/projectsRole/{projectRoleId}",
				ProjectRole.class,id);
		
		final ProjectRole result = response.getBody();

		assertSame(result.getId(), projectRole.getId());
		assertEquals(result.getName(), projectRole.getName());
	}
	
	private String getBaseUrl() {
        return "http://localhost:" + port;
    }
}
