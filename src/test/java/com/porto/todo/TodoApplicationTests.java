package com.porto.todo;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class TodoApplicationTests {

	@Setter
    @Getter
    static class Pessoa {
		String name;
		LocalDate birthDate;

		public Pessoa(String name, LocalDate birthDate) {
			this.name = name;
			this.birthDate = birthDate;
		}

        public int getAge() {
			int yearBirthDay = birthDate.getYear();
			int yearNow = LocalDate.now().getYear();
            return yearNow - yearBirthDay;
        }
	}

	@Test
	void shouldBeValidateAge() {
		Pessoa person = new Pessoa("Fulano", LocalDate.of(2020, 1, 1));
		Assertions.assertEquals(4, person.getAge());
		Assertions.assertTrue(person.getAge() > 2);
	}

}
