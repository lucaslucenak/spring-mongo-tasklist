package com.unifacisa.tasklist.models;

import com.unifacisa.tasklist.dtos.TaskDto;
import com.unifacisa.tasklist.enums.StatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Field title shouldn't be null")
    @NotEmpty(message = "Field title shouldn't be empty")
    @NotBlank(message = "Field title shouldn't be blank")
    private String title;

    private String description;

    @NotNull(message = "Field status shouldn't be null")
    @Enumerated(value = EnumType.STRING)
    private StatusEnum status;

    @NotNull(message = "Field userId shouldn't be null")
    @NotEmpty(message = "Field userId shouldn't be empty")
    @NotBlank(message = "Field userId shouldn't be blank")
    private String userId;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public TaskModel(TaskDto taskDto) {
        BeanUtils.copyProperties(taskDto, this);
    }
}
