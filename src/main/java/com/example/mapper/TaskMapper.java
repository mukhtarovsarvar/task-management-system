package com.example.mapper;

import com.example.dto.request.TaskCreateDTO;
import com.example.dto.request.TaskUpdateDTO;
import com.example.dto.response.TaskResponseDTO;
import com.example.entity.TaskEntity;

/**
 * @author Admin on 9/16/2024
 * @project task-managment-system
 * @package com.example.mapper
 * @contact @sarvargo
 */
public class TaskMapper {
    public static   TaskResponseDTO toResponse(TaskEntity task) {
        return new TaskResponseDTO(
                task.getId(), task.getTitle(),
                task.getDescription(),
                task.getDueDate(), task.getStatus(),
                task.getCreatedDate()
        );
    }

    public static TaskEntity toEntity(TaskCreateDTO payload) {
        return new TaskEntity(
                payload.getTitle(),
                payload.getDescription(),
                payload.getDueDate()
        );
    }

    public static void toEntity(TaskEntity task, TaskUpdateDTO payload) {
        task.setTitle(payload.getTitle());
        task.setDescription(payload.getDescription());
        task.setDueDate(payload.getDueDate());
        task.setStatus(payload.getStatus());
    }
}
