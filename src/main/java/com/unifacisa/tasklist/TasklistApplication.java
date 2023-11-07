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
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = TaskRepository.class)
public class TasklistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasklistApplication.class, args);
	}

}
