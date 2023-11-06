package com.unifacisa.tasklist;

import com.unifacisa.tasklist.enums.StatusEnum;
import com.unifacisa.tasklist.models.TaskModel;
import com.unifacisa.tasklist.models.UserModel;
import com.unifacisa.tasklist.repositories.TaskRepository;
import com.unifacisa.tasklist.repositories.UserRepository;
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
	CommandLineRunner runner (UserRepository userRepository, TaskRepository taskRepository) {
		return args -> {
			UserModel user = UserModel.builder()
					.username("lucaslucena")
					.password("123456")
					.email("lucas.lucenak@gmail.com")
					.build();
			userRepository.save(user);
			TaskModel task1 = TaskModel.builder()
					.title("Passear com o cachorro de sônia")
					.description("Achei na olx")
					.status(StatusEnum.TO_DO)
					.user(user)
					.build();
			taskRepository.save(task1);
		};


	}

}
