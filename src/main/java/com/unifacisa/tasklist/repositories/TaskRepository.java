package com.unifacisa.tasklist.repositories;

import com.unifacisa.tasklist.models.TaskModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<TaskModel, String> {
}
