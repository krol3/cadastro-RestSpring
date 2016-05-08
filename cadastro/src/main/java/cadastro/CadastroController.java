package cadastro;

import java.util.Map;

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

import cadastro.service.CadastroService;

@RestController
class CadastroController {

	@RequestMapping("/greet")
	String sayHello(@RequestParam("name") String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("The 'name' parameter must not be null or empty");
		}
		return String.format("Hello %s!", name);
	}

	@Autowired
	private CadastroService personService;

	@RequestMapping(value = "/cadastro/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	User get(@PathVariable("id") String id) {
		return personService.findById(new Long(id));
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	Map<String, Object> saveCadastro(@RequestBody User json) {
		User userCreated = personService.registerUser(json);

		if (userCreated == null) {
			throw new CadastroException();
		}

		Map<String, Object> m = Maps.newHashMap();
		m.put("success", true);
		m.put("created", userCreated);
		return m;
	}
}
