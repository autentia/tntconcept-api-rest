package autentia.apiRestTnt.Services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import autentia.apiRestTnt.Model.User;
import autentia.apiRestTnt.Repository.UserRepository;

public class UserServiceTest {
	
	private UserRepository userRepository = mock(UserRepository.class);
	
	private UserService userService = new UserService(userRepository);
	
//	@Test
//	public void getUserByLoginShouldReturnUserFromRepository() {
//		User authenticatedUser = mock(User.class);
//		UserDetails userDetails = mock(UserDetails.class);
//		final String login = "iperez";
//		
//		when(any(Authentication.class).getPrincipal()).thenReturn(userDetails);
//		when(userRepository.getUserByLogin(login)).thenReturn(authenticatedUser);
//		
//		User result = userService.getUserByLogin();
//		
//		assertEquals(result,is(authenticatedUser));
//		
//	}

}
