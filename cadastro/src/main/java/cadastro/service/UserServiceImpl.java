package cadastro.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

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
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PhoneRepository phoneRepository;

	@Override
	public void addUser(User user) {

		User findUser = userRepository.findById(user.getId());
		boolean isFound = findUser == null ? false : true;

		if (isFound)
			return;

		// Hibernate.initialize(user);
		// Hibernate.initialize(user.getPhones());

		if (user.getPhones() != null) {
			for (Phone phone : user.getPhones()) {
				Phone createdPhone = phoneRepository.save(phone);
				phone.setId(createdPhone.getId());
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx> " + phone.getDdd());
			}
		}

		userRepository.save(user);

	}

	@Override
	public User getUser(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User findUserByEmailAddress(String emailAddress) {
		return userRepository.findByEmail(emailAddress);
	}

	@Override
	public List<User> findUsers() {
		List<User> users = new ArrayList<User>();
		for (User user : userRepository.findAll()) {
			users.add(user);
		}
		return users;
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
	public void removeUser(Long id) {
		userRepository.delete(id);
	}

	@Override
	@Transactional
	public void savePhone(Phone phone) throws DataAccessException {
		phoneRepository.save(phone);
	}

	@Override
	public Collection<Phone> findPhones() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(long id) throws DataAccessException {
		// TODO Auto-generated method stub
		return userRepository.findById(new Long(id));
	}

}
