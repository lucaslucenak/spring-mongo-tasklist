package com.unifacisa.tasklist.repositories;

import com.unifacisa.tasklist.models.TaskModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<TaskModel, String> {
    List<TaskModel> findByUserId(String userId);
}
