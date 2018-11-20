/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.api.rest.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.autentia.tnt.api.rest.model.User;
import com.autentia.tnt.api.rest.repository.UserRepository;

public class UserServiceTest {

    private UserRepository userRepository = mock(UserRepository.class);

    private UserService userService = new UserService(userRepository);

    @Test
    public void getUserByLoginShouldReturnUserFromRepository() {
        SecurityContext secContext = mock(SecurityContext.class);
        Authentication auth = mock(Authentication.class);
        User authenticatedUser = mock(User.class);
        UserDetails userDetails = mock(UserDetails.class);
        final String login = "iperez";
        SecurityContextHolder.setContext(secContext);

        when(secContext.getAuthentication()).thenReturn(auth);
        when(auth.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn(login);
        when(userRepository.getUserByLogin(login)).thenReturn(authenticatedUser);

        User result = userService.getUserByLogin("");

        assertThat(result, is(authenticatedUser));

    }

}
