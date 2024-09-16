package com.example.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author Admin on 9/16/2024
 * @project task-managment-system
 * @package com.example.dto.response
 * @contact @sarvargo
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseResponse<T> {

    String message;

    boolean success;
    int code;

    T data;

    public BaseResponse(String message, boolean success, int code) {
        this.message = message;
        this.success = success;
        this.code = code;
    }

    public static BaseResponse<Void> ok() {
        return new BaseResponse<>("Success", true, 200);
    }

    public static BaseResponse<Void> bad(String message) {
        return new BaseResponse<>(message, false, 400);
    }
}
