package edu.mum.cs544.UserService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto<T> {
    private String message;
    private boolean hasError = false;
    private T response = null;
    private String errorMessage;
}
