package cadastro.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;

import cadastro.Application;
import cadastro.Phone;
import cadastro.User;

@SpringApplicationConfiguration(classes = Application.class)
public class CadastroServiceImplTest {

	private static ApplicationContext applicationContext;
	private static CadastroService cadastroService;

	@BeforeClass
	public static void beforeClass() {

		// Load our application context
		applicationContext = SpringApplication.run(Application.class);

		// Load the UserServiceImpl service
		cadastroService = (CadastroService) applicationContext.getBean("cadastroServiceImpl");
	}

	@AfterClass
	public static void afterClass() {
		// userService.deleteAll();
	}

	@Test
	public void testAddUser() {
		// Create a user
		User user = new User("João da Silva", "joao@silva.org", "hunter2");

		// Insert it into the repository
		cadastroService.registerUser(user);

		// Check to see if its there
		User repositoryUser = cadastroService.findUserByEmailAddress("joao@silva.org");
		Assert.assertNotNull(repositoryUser);
		Assert.assertEquals("The user's name is not correct", "João da Silva", repositoryUser.getName());
		Assert.assertEquals("The user's password is not correct", "hunter2", repositoryUser.getPassword());

		// Remove the user
		// userService.removeUser(repositoryUser.getId());
	}

	@Test
	public void testAddUserWithPhones() {
		// Create a user
		User user = new User("João da Silva", "joao@silva.org", "hunter2");
		Phone phone1 = new Phone("021", "21212121212");
		Phone phone2 = new Phone("011", "777777");
		List<Phone> phones = new ArrayList<Phone>();
		phones.add(phone1);
		phones.add(phone2);
		user.setPhones(phones);

		// Insert it into the repository
		cadastroService.registerUser(user);

		// Check to see if its there
		User repositoryUser = cadastroService.findUserByEmailAddress("joao@silva.org");
		Assert.assertNotNull(repositoryUser);
		Assert.assertEquals("The user's name is not correct", "João da Silva", repositoryUser.getName());
		Assert.assertEquals("The user's password is not correct", "hunter2", repositoryUser.getPassword());
		Assert.assertNotNull("Phones is null", repositoryUser.getPhones());
		// Assert.assertEquals("The wrong number of phones", 2,
		// repositoryUser.getPhones().size());

		Phone repositoryAddress = repositoryUser.getPhones().get(0);
		Assert.assertEquals("DDD is wrong", "021", repositoryAddress.getDdd());
		Assert.assertEquals("Number is wrong", "21212121212", repositoryAddress.getNumber());

		// Remove the user
		// cadastroService.removeUser(repositoryUser.getId());
	}

}
