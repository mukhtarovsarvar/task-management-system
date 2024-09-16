package com.example.expceptions;

/**
 * @author Admin on 9/16/2024
 * @project task-managment-system
 * @package com.example.expceptions
 * @contact @sarvargo
 */
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String s) {
        super(s);
    }
}
