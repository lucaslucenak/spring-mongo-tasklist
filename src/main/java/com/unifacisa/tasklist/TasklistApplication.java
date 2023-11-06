package com.unifacisa.tasklist;

import com.unifacisa.tasklist.enums.StatusEnum;
import com.unifacisa.tasklist.models.TaskModel;
import com.unifacisa.tasklist.models.UserModel;
import com.unifacisa.tasklist.repositories.TaskRepository;
import com.unifacisa.tasklist.repositories.UserRepository;
import com.unifacisa.tasklist.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TasklistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasklistApplication.class, args);
	}

	@Bean
	CommandLineRunner runner (UserService userService, TaskRepository taskRepository) {
		return args -> {
			UserModel user = UserModel.builder()
					.username("lucaslucena")
					.password("123456")
					.email("lucas.lucenak@gmail.com")
					.build();
			if (!userService.existsByEmail("lucas.lucenak@gmail.com")) {
				userService.saveUser(user);
			}
			TaskModel task1 = TaskModel.builder()
					.title("Passear com o cachorro de sônia")
					.description("Achei na olx")
					.status(StatusEnum.TO_DO)
					.userId(user.getId())
					.build();
			taskRepository.save(task1);
		};


	}

}
