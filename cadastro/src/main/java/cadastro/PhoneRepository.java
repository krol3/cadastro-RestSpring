package cadastro;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "phone", path = "phone", exported = false)
public interface PhoneRepository extends PagingAndSortingRepository<Phone, Long> {

	List<User> findByDdd(@Param("name") String name) throws DataAccessException;

	List<Phone> findById(Integer phoneId) throws DataAccessException;

}