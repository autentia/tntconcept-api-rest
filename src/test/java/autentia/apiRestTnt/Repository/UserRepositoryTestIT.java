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
import static org.junit.Assert.assertTrue;

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
	
	@Test
	public void findOneShouldReturnUserFromDB(){
		final Integer id = 1;
		
		User searchedUser = userRepository.findOne(id);
		
		assertTrue(searchedUser.getId() == id);
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
		final String login = "aortiz";
		User userToDelete = userRepository.getUserByLogin(login);
		userRepository.delete(userToDelete);
		
		User result = userRepository.getUserByLogin(login);
		
		assertEquals(result,null);
	}
	
	@Test
	public void getUserByLoginShouldReturnUserByLoginFromDB() {
		final String login = "admin";
		
		User searchedUser = userRepository.getUserByLogin(login);
		
		assertEquals(searchedUser.getLogin(),login);
	}

}
