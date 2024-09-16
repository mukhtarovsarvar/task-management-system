package com.example.service;

import com.example.dto.request.TaskCreateDTO;
import com.example.dto.request.TaskUpdateDTO;
import com.example.dto.response.BaseResponse;
import com.example.dto.response.TaskResponseDTO;
import com.example.entity.TaskEntity;
import com.example.expceptions.TaskNotFoundException;
import com.example.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Admin on 9/16/2024
 * @project task-managment-system
 * @package com.example.service
 * @contact @sarvargo
 */
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskResponseDTO create(TaskCreateDTO payload) {
        TaskEntity task = toEntity(payload);
        taskRepository.save(task);
        return toResponse(task);
    }

    public Optional<TaskResponseDTO> update(Long id, TaskUpdateDTO payload) {
        return taskRepository
                .findById(id)
                .map(task -> {
                    toEntity(task, payload);
                    taskRepository.save(task);
                    return toResponse(task);
                });
    }

    public void delete(Long id) {
        taskRepository.deleteTaskById(id);
    }

    public List<TaskResponseDTO> findAll() {
        return taskRepository.findAll().stream().map(this::toResponse).toList();
    }

    private TaskResponseDTO toResponse(TaskEntity task) {
        return new TaskResponseDTO(
                task.getId(), task.getTitle(), task.getDescription(),
                task.getDueDate(), task.getCreatedDate()
        );
    }

    private TaskEntity toEntity(TaskCreateDTO payload) {
        return new TaskEntity(
                payload.getTitle(),
                payload.getDescription(),
                payload.getDueDate()
        );
    }

    private TaskEntity toEntity(TaskEntity task, TaskUpdateDTO payload) {
        task.setTitle(payload.getTitle());
        task.setDescription(payload.getDescription());
        task.setDueDate(payload.getDueDate());
        task.setStatus(payload.getStatus());
        return task;
    }
}
