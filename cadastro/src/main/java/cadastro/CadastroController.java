package cadastro;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import cadastro.exceptions.CadastroException;
import cadastro.exceptions.LoginException;
import cadastro.service.CadastroService;

@RestController
class CadastroController {

	private static final Logger LOG = LoggerFactory.getLogger(CadastroController.class);

	@Autowired
	private CadastroService cadastroService;

	@RequestMapping("/greet")
	String sayHello(@RequestParam("name") String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("The 'name' parameter must not be null or empty");
		}
		return String.format("Hello %s!", name);
	}

	@RequestMapping(value = "/cadastro/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	User get(@PathVariable("id") String id) {
		return cadastroService.findById(new Long(id));
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	Map<String, Object> saveCadastro(@RequestBody User json) {
		User userCreated = cadastroService.registerUser(json);

		if (userCreated == null) {
			throw new CadastroException();
		}

		Map<String, Object> m = Maps.newHashMap();
		m.put("success", true);
		m.put("created", userCreated);
		return m;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	Map<String, Object> doLogin(@RequestParam("email") String email, @RequestParam("senha") String senha) {
		User userFound = cadastroService.findUserByEmailAddress(email);

		if (userFound == null) {
			LOG.error("Usuario nao existe");
			throw new LoginException();
		}

		if (userFound != null) {
			if (!senha.equals(userFound.getPassword())) {
				LOG.error("Usuario existe, senha errada");
				throw new LoginException();
			}
		}

		// update last login
		userFound = cadastroService.saveLastLoginUser(userFound);

		Map<String, Object> m = Maps.newHashMap();
		m.put("success", true);
		m.put("created", userFound);
		return m;
	}

	@RequestMapping(value = "/perfil/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	Map<String, Object> doPerfil(@PathVariable("id") int id, HttpServletRequest request) {

		String token = request.getHeader("token");
		LOG.info("token: " + token);

		LOG.info("id: " + id);

		User userFound = cadastroService.findUser(new Long(id));

		if (userFound == null) {
			LOG.error("Usuario nao existe");
			throw new LoginException();
		}

		if (userFound != null) {
			if (!token.equals(userFound.getToken())) {
				LOG.error("Usuario existe, TOKEN errado");
				throw new LoginException();
			}
		}

		// FIXME verificar ultimo login, dentro dos 30 minutos

		Map<String, Object> m = Maps.newHashMap();
		m.put("success", true);
		m.put("created", userFound);
		return m;
	}
}
