package cadastro;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeLinkDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeLinkSave;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;

@Service
@RepositoryEventHandler(User.class)
public class UserEventHandler {

	private static final Logger LOG = LoggerFactory.getLogger(UserEventHandler.class);
	@Autowired
	UserRepository repository;

	@HandleBeforeCreate
	public void handleBeforeCreate(User user) {
		
		user.setCreated(new Date());
		UUID idOne = UUID.randomUUID();
		user.setToken(idOne.toString());
		user.setLast_login(new Date());

		LOG.info("handleBeforeCreate: " + user);
	}

	@HandleBeforeSave
	public void handleBeforeSave(User user) {
		
		LOG.info("handleBeforeSave: " + user);
	}

	@HandleBeforeLinkSave
	public void handleBeforeLinkSave(User c) {
		LOG.info("Before link save " + c);
	}

	@HandleBeforeDelete
	public void handleBeforeDelete(User c) {
		LOG.info("Before delete " + c);
	}

	@HandleBeforeLinkDelete
	public void handleBeforeLinkDelete(User c) {
		LOG.info("Before link delete " + c);
	}

}
