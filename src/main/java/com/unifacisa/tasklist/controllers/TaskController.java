package com.unifacisa.tasklist.controllers;

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
    public ResponseEntity<Page<TaskModel>> getAllTasks(Pageable pageable) {
        List<TaskModel> taskReturnDtos = new ArrayList<>();
        for (TaskModel i : taskService.findAllTasksPaginated(pageable)) {
            taskReturnDtos.add(i);
        }
        Page<TaskModel> taskReturnDtoPage = new PageImpl<>(taskReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), taskReturnDtos.size());
        return ResponseEntity.ok().body(taskReturnDtoPage);
    }

    @PostMapping
    public ResponseEntity<TaskModel> saveTask(@RequestBody @Valid TaskModel taskPostDto) {
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
