package dev.wisespirit.warehouse.utils;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private int statusCode;

    public ApiResponse(boolean success, String message, T data, int statusCode) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
    }

    public ApiResponse(boolean success, int value) {
        this.success = success;
        this.statusCode = value;
    }

    // Getter and Setter methods

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    // Static method to generate success response
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "Operation was successful", data, HttpStatus.OK.value());
    }
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(true, HttpStatus.OK.value());
    }

    // Static method to generate error response
    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<>(false, message, data, HttpStatus.BAD_REQUEST.value());
    }


}

