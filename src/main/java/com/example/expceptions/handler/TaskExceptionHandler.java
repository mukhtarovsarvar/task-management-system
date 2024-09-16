package com.example.expceptions.handler;

import com.example.dto.response.BaseResponse;
import com.example.expceptions.TaskNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Admin on 9/16/2024
 * @project task-managment-system
 * @package com.example.expceptions.handler
 * @contact @sarvargo
 */
@ControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler({TaskNotFoundException.class})
    public ResponseEntity<BaseResponse<Void>> badRequestExp(RuntimeException e) {
        return ResponseEntity
                .badRequest()
                .body(
                        BaseResponse.bad(e.getMessage())
                );
    }
}
