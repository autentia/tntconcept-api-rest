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
