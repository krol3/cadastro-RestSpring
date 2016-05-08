package cadastro.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cadastro.Phone;
import cadastro.PhoneRepository;
import cadastro.User;
import cadastro.UserRepository;

@Service
public class CadastroServiceImpl implements CadastroService {

	private static final Logger LOG = LoggerFactory.getLogger(CadastroServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PhoneRepository phoneRepository;

	@Override
	public User registerUser(User user) {

		List<User> findUser = userRepository.findByEmail(user.getEmail());
		boolean isFound = (findUser.size() > 0 ? true : false);

		if (isFound) {
			return null;
		}

		if (user.getPhones() != null) {
			for (Phone phone : user.getPhones()) {
				Phone createdPhone = phoneRepository.save(phone);
				phone.setId(createdPhone.getId());
				LOG.info("xx> " + phone.getDdd());
			}
		}

		// Default fields
		user.setCreated(new Date());
		UUID idOne = UUID.randomUUID();
		user.setToken(idOne.toString());
		user.setModified(new Date());

		User saved = userRepository.save(user);

		return saved;
	}

	@Override
	public User findUser(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User saveLastLoginUser(User user) {

		user.setLast_login(new Date());
		User saved = userRepository.save(user);

		return saved;
	}

	@Override
	public List<User> findUsers(int pageNumber, int pageSize) {
		// Create a Pageable object for the requested page number and page size
		Pageable pageable = new PageRequest(pageNumber, pageSize);

		// Retrieve a page of users
		Page<User> page = userRepository.findAll(pageable);

		// Returns the list of users
		return page.getContent();
	}

	@Override
	public User findById(long id) throws DataAccessException {
		// TODO Auto-generated method stub
		return userRepository.findById(new Long(id));
	}

	@Override
	public User findUserByEmailAddress(String email) throws DataAccessException {
		List<User> findUser = userRepository.findByEmail(email);
		if (findUser.size() == 0)
			return null;
		else
			return findUser.get(0);
	}

}
