package com.example.dto.request;

import com.example.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author Admin on 9/16/2024
 * @project task-managment-system
 * @package com.example.dto.request
 * @contact @sarvargo
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskUpdateDTO extends TaskCreateDTO {

    @NotNull(message = "Status can't be empty!")
    TaskStatus status;
}
