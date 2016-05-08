package cadastro.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import cadastro.User;

public interface CadastroService {
	public enum SortOrder {
		ASCENDING, DESCENDING
	}

	public User registerUser(User user) throws DataAccessException;

	public User findUserByEmailAddress(String email) throws DataAccessException;

	public User findUser(Long id) throws DataAccessException;

	public User saveLastLoginUser(User user) throws DataAccessException;

	public User findById(long id) throws DataAccessException;

	public List<User> findUsers(int pageNumber, int pageSize) throws DataAccessException;

}
