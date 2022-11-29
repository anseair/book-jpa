package telran.java2022.book.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthorDto {
	String name;
    LocalDate birthDate;
}
