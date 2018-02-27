package autentia.apiRestTnt.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ProjectTest {
	
	private Project project;
	
	@Before
	public void setUp() {
		project = new Project();
	}
	
	@Test
	public void getAndSetProjectTest() {
		project.setName("Modificar Proyecto");
		project.setOpen(false);
		project.setOrganizationId(2);
		List<ProjectRole> projectRoleList = new ArrayList<>();
		ProjectRole projectRole = new ProjectRole();
		projectRole.setName("Test");
		projectRoleList.add(projectRole);
		project.setProjectRoles(projectRoleList);
		assertEquals(project.getName(),"Modificar Proyecto");
		assertEquals(project.getOpen(),false);
		assertTrue(project.getOrganizationId() == 2);
		assertEquals(project.getProjectRoles().size(),projectRoleList.size());
	}
	
}
