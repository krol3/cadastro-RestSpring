package cadastro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Usuário e/ou senha inválidos")
public class LoginException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2004387309414917316L;
}
