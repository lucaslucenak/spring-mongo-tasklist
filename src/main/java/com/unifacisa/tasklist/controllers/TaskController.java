package com.unifacisa.tasklist.controllers;

import com.unifacisa.tasklist.dtos.TaskDto;
import com.unifacisa.tasklist.models.TaskModel;
import com.unifacisa.tasklist.services.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping(value = "/{taskId}")
    public ResponseEntity<TaskModel> getTaskById(@PathVariable String taskId) {
        return ResponseEntity.ok().body(taskService.findTaskById(taskId));
    }

    @GetMapping
    public ResponseEntity<List<TaskModel>> getAllTasks() {
        return ResponseEntity.ok().body(taskService.findAllTasks());
    }

    @PostMapping
    public ResponseEntity<TaskModel> saveTask(@RequestBody @Valid TaskDto taskPostDto) {
        return ResponseEntity.ok().body(taskService.saveTask(taskPostDto));
    }

    @PutMapping(value = "/{taskId}")
    public ResponseEntity<TaskModel> updateTask(@PathVariable String taskId, @RequestBody @Valid TaskModel taskPostDto) {
        return ResponseEntity.ok().body(taskService.updateTask(taskId, taskPostDto));
    }

    @DeleteMapping(value = "/{taskId}")
    public ResponseEntity<TaskModel> deleteTaskById(@PathVariable String taskId) {
        taskService.deleteTaskById(taskId);
        return ResponseEntity.noContent().build();
    }
}
