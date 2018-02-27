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

import autentia.apiRestTnt.Model.Project;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@Transactional
public class ProjectRepositoryTestIT {
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Before
	public void setUp() {
		Project project = new Project();
		project.setName("Vacaciones");
		projectRepository.save(project);
	}
	
	@Test
	public void findOneShouldReturnProjectFromDB() {
		final Integer id = 1;
		Project resultProject = projectRepository.findOne(id);
		
		assertEquals(resultProject.getName(),"Vacaciones");
	}
}
