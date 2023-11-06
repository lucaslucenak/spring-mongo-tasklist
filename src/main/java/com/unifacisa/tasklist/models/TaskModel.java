package com.unifacisa.tasklist.models;

import com.unifacisa.tasklist.dtos.TaskDto;
import com.unifacisa.tasklist.enums.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "task")
public class TaskModel {

    @Id
    private String id;

    private String title;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private StatusEnum status;

    private UserModel user;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public TaskModel(TaskDto taskDto) {
        BeanUtils.copyProperties(taskDto, this);
    }
}
