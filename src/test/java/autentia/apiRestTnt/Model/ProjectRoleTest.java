package autentia.apiRestTnt.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ProjectRoleTest {
	
	private ProjectRole projectRole;
	
	@Before
	public void setUp() {
		projectRole = new ProjectRole();
	}
	
	@Test
	public void getAndSetProjectRoleTest() {
		projectRole.setName("Role");
		projectRole.setProjectId(1);
		assertEquals(projectRole.getName(),"Role");
		assertTrue(projectRole.getProjectId() == 1);
	}

}
