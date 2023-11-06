package com.unifacisa.tasklist.services;

import com.unifacisa.tasklist.dtos.TaskDto;
import com.unifacisa.tasklist.exceptions.IncompatibleIdsException;
import com.unifacisa.tasklist.exceptions.ResourceNotFoundException;
import com.unifacisa.tasklist.models.TaskModel;
import com.unifacisa.tasklist.models.UserModel;
import com.unifacisa.tasklist.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public TaskModel findTaskById(String taskId) {
        Optional<TaskModel> taskModelOptional = taskRepository.findById(taskId);

        if (taskModelOptional.isPresent()) {
            return taskModelOptional.get();
        } else {
            throw new ResourceNotFoundException("Resource: Task. Not found with id: " + taskId);
        }
    }

    @Transactional
    public List<TaskModel> findAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional
    public TaskModel saveTask(TaskDto taskDto) {
        TaskModel taskModel = new TaskModel(taskDto);
        UserModel userModel = userService.findUserById(taskDto.getUserId());
        taskModel.setUser(userModel);

        return taskRepository.save(taskModel);
    }

    @Transactional
    public TaskModel updateTask(String taskId, TaskModel updatedTaskModel) {
        if (!taskId.equals(updatedTaskModel.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + taskId + ", Body Id: " + updatedTaskModel.getId());
        }

        TaskModel existingTaskModel = this.findTaskById(taskId);

        BeanUtils.copyProperties(updatedTaskModel, existingTaskModel, "createdAt, updatedAt");
        return taskRepository.save(existingTaskModel);
    }

    @Transactional
    public void deleteTaskById(String taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
        } else {
            throw new ResourceNotFoundException("Resource: Task. Not found with id: " + taskId);
        }
    }

}
