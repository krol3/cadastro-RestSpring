package cadastro;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

@RepositoryEventHandler(User.class)
public class UserEventHandler {

	@HandleBeforeCreate
	public void handleUserCreate(User user) {
		user.setCreated(new Date());
		UUID idOne = UUID.randomUUID();
		user.setToken(idOne.toString());
		user.setLast_login(new Date());
	}

}
