package com.porto.todo;

import com.porto.todo.user.UserModel;
import com.porto.todo.user.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class TodoApplicationTests {

	@Autowired
    private UserRepository userRepository;



	@Test
	void shouldBeCreateNewUser() {
        UserModel creatingUser = new UserModel("User Test", "usertest", "123456");

		Assertions.assertFalse(creatingUser.toString().isEmpty());
	}

}
