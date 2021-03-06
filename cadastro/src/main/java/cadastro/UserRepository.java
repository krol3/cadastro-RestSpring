package cadastro;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	public List<User> findByName(@Param("name") String name) throws DataAccessException;

	public List<User> findByEmail(@Param("email") String email) throws DataAccessException;

	public User findById(@Param("id") Long id) throws DataAccessException;

}