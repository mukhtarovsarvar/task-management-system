package com.example.dto.request;

import com.example.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * @author Admin on 9/16/2024
 * @project task-managment-system
 * @package com.example.dto.request
 * @contact @sarvargo
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskUpdateDTO extends TaskCreateDTO {

    @NotNull(message = "Status can't be empty!")
    TaskStatus status;

    public TaskUpdateDTO( String title, String description, LocalDate dueDate, TaskStatus status) {
        super(title, description, dueDate);
        this.status = status;
    }
}
