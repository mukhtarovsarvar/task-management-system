package com.example.service;

import com.example.dto.request.TaskCreateDTO;
import com.example.dto.request.TaskUpdateDTO;
import com.example.dto.response.TaskResponseDTO;
import com.example.entity.TaskEntity;
import com.example.enums.TaskStatus;
import com.example.repository.TaskRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * @author 'Mukhtarov Sarvarbek' on 9/17/2024
 * @project task-management-system
 * @contact @sarvargo
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskServiceTest {

    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Order(1)
    @DisplayName("Create task")
    @ParameterizedTest(name = "{0} - task created")
    @CsvSource(
            {
                    "Reading books, Books are useful for your brain",
                    "Riding horse,"
            }
    )
    void create_success(String title, String description) {
        TaskCreateDTO taskCreateDTO = new TaskCreateDTO(title, description, null);
        // validating
        Set<ConstraintViolation<TaskCreateDTO>> violations = validator.validate(taskCreateDTO);
        assertTrue(violations.isEmpty());

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(1L);
        taskEntity.setTitle(taskCreateDTO.getTitle());

        when(taskRepository.save(any(TaskEntity.class))).thenReturn(taskEntity);

        TaskResponseDTO taskResponseDTO = taskService.create(taskCreateDTO);
        assertNotNull(taskResponseDTO.getId());
        assertEquals(taskCreateDTO.getTitle(), taskResponseDTO.getTitle());
    }

    @Order(2)
    @DisplayName("Create task validation failed because title is null")
    @ParameterizedTest(name = "Tittle is {0} failed to validation")
    @CsvSource(
            {
                    ", Books are useful for your brain",
                    ", Some task"
            }
    )
    void create_validation_error(String title, String description) {
        TaskCreateDTO taskCreateDTO = new TaskCreateDTO(title, description, null);
        // validating
        Set<ConstraintViolation<TaskCreateDTO>> violations = validator.validate(taskCreateDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    @Order(3)
    @DisplayName("Update task")
    void update_success() {
        Long taskId = 1L;

        TaskEntity existingTask = new TaskEntity();
        existingTask.setId(taskId);
        existingTask.setTitle("Walking");
        existingTask.setDescription("Walking every morning ....");
        existingTask.setDueDate(LocalDate.now());
        existingTask.setStatus(TaskStatus.OPEN);

        TaskUpdateDTO taskUpdateDTO = new TaskUpdateDTO("Updated Title", "Updated Description", LocalDate.now().plusDays(1), TaskStatus.IN_PROGRESS);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));

        when(taskRepository.save(any(TaskEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<TaskResponseDTO> taskResponseDTOOpt = taskService.update(taskId, taskUpdateDTO);

        assertTrue(taskResponseDTOOpt.isPresent());

        TaskResponseDTO taskResponseDTO = taskResponseDTOOpt.get();

        assertNotNull(taskResponseDTO.getId());

        assertEquals(taskUpdateDTO.getTitle(), taskResponseDTO.getTitle());
        assertEquals(taskUpdateDTO.getDescription(), taskResponseDTO.getDescription());
        assertEquals(taskUpdateDTO.getDueDate(), taskResponseDTO.getDueDate());
        assertEquals(taskUpdateDTO.getStatus(), taskResponseDTO.getStatus());

        verify(taskRepository, times(1)).save(existingTask);
    }

    @Test
    @Order(4)
    @DisplayName("Delete task")
    void delete_success() {
        Long taskId = 1L;

        when(taskRepository.existsById(taskId)).thenReturn(true);

        taskService.delete(taskId);

        verify(taskRepository, times(1)).deleteTaskById(taskId);
    }

    @Test
    @Order(5)
    @DisplayName("Find all tasks")
    void getAll_success() {
        TaskEntity task1 = new TaskEntity();
        task1.setId(1L);
        task1.setTitle("Task 1");

        TaskEntity task2 = new TaskEntity();
        task2.setId(2L);
        task2.setTitle("Task 2");

        List<TaskEntity> taskList = Arrays.asList(task1, task2);

        when(taskRepository.findAll()).thenReturn(taskList);

        List<TaskResponseDTO> responseList = taskService.findAll();

        assertEquals(2, responseList.size());
        assertEquals(task1.getId(), responseList.get(0).getId());
        assertEquals(task1.getTitle(), responseList.get(0).getTitle());
        assertEquals(task2.getId(), responseList.get(1).getId());
        assertEquals(task2.getTitle(), responseList.get(1).getTitle());

        verify(taskRepository, times(1)).findAll();
    }

}
