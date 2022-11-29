package telran.java2022.book.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundEsception extends RuntimeException{


	private static final long serialVersionUID = -4246731824719626558L;
	

}
