package cadastro.service;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import cadastro.Phone;
import cadastro.User;

public interface UserService {
	public enum SortOrder {
		ASCENDING, DESCENDING
	}

	public void addUser(User user) throws DataAccessException;

	public User getUser(Long id) throws DataAccessException;

	public User findUserByEmailAddress(String emailAddress) throws DataAccessException;

	public List<User> findUsers() throws DataAccessException;

	public User findById(long id) throws DataAccessException;

	public List<User> findUsers(int pageNumber, int pageSize) throws DataAccessException;

	public void removeUser(Long id) throws DataAccessException;

	void savePhone(Phone phone) throws DataAccessException;

	Collection<Phone> findPhones() throws DataAccessException;
}
