package cadastro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "E-mail já existente")
public class CadastroException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3404961818956729980L;

}
