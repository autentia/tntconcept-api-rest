package autentia.apiRestTnt.Model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private User user; 
	
	@Before
	public void setUp() {
		this.user = new User();
	}
	
	@Test
	public void getAndSetUserTest() {
		user.setLogin("Login");
		user.setPassword("Password");
		
		assertEquals(user.getLogin(),"Login");
		assertEquals(user.getPassword(),"Password");
	}

}
