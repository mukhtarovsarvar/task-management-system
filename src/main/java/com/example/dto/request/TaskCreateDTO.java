package com.example.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskCreateDTO {

    @NotBlank(message = "Title can't be empty!")
    String title;

    String description;

    LocalDate dueDate;
}
