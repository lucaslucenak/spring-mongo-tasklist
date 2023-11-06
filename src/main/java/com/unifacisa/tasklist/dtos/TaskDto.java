package com.unifacisa.tasklist.dtos;

import com.unifacisa.tasklist.enums.StatusEnum;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private String id;

    private String title;

    private String description;

    private StatusEnum status;

    private String userId;

}
