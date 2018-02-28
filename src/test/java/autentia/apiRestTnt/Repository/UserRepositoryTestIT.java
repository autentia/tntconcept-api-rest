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

import autentia.apiRestTnt.Model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@Transactional
public class UserRepositoryTestIT {
	
	@Autowired
	private UserRepository userRepository;
	
	private User user;
	
	@Before
	public void setUp() {
		this.user = new User();
		this.user.setLogin("login");
		this.user.setPassword("password");
		
		userRepository.save(user);
	}
	
	@Test
	public void findOneShouldReturnUserFromDB(){
		User searchedUser = userRepository.findOne(1);
		
		assertEquals(searchedUser.getLogin(),"login");
	}
	
	@Test
	public void saveShouldReturnUserAfterSaving() {
		User userToSave = new User ();
		userToSave.setLogin("user");
		
		User savedUser = userRepository.save(userToSave);
		
		assertEquals(savedUser, userToSave);
	}
	
	@Test
	public void deleteShouldDeleteUserFromDB() {
		userRepository.delete(user);
		User result = userRepository.findOne(1);
		
		assertEquals(result,null);
	}
	
	@Test
	public void getUserByLoginShouldReturnUserByLoginFromDB() {
		final String login = "login";
		
		User result = userRepository.getUserByLogin(login);
		
		assertEquals(result,user);
	}

}
