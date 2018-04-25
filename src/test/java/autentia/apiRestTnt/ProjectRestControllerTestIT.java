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

import autentia.apiRestTnt.Controller.ProjectController;
import autentia.apiRestTnt.Model.Project;
import autentia.apiRestTnt.Repository.ProjectRepository;
import autentia.apiRestTnt.Services.ProjectService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class ProjectRestControllerTestIT {
	
	@Value("${local.server.port}")
	private int port;
	
	private TestRestTemplate restTemplate = new TestRestTemplate("admin","adminadmin");
	
	private ProjectRepository projectRepository;
	private final ProjectService projectService = new ProjectService(projectRepository);
	@Autowired
	private final ProjectController projectController = new ProjectController(projectService);
	
	@Test
	public void shouldReturnProjectDetails() {
		final Integer id = 1;
		
		Project project = projectController.getProjectById(id);
		
		final ResponseEntity<Project> response = restTemplate.getForEntity(getBaseUrl() + "/api/project/{projectId}",
				Project.class,id);
		
		final Project result = response.getBody();

		assertSame(result.getId(), project.getId());
		assertEquals(result.getName(), project.getName());
		assertEquals(result.getOpen(),project.getOpen());
	}
	
	private String getBaseUrl() {
        return "http://localhost:" + port;
    }
}
