package com.example.dto.response;

import com.example.enums.TaskStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Admin on 9/16/2024
 * @project task-managment-system
 * @package com.example.dto.response
 * @contact @sarvargo
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
    Long id;
    String title;
    String description;
    LocalDate dueDate;
    TaskStatus status;
    LocalDateTime createdDate;
}
