package com.unifacisa.tasklist.models;

import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class UserModel {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String password;

    @Indexed(unique = true)
    private String email;

    private Set<TaskModel> tasks;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
