package com.nikolasiker.lib_api.api;

public class ApiResponse<T> {
    public static class Success<T> extends ApiResponse<T> {
        private T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

    public static class Error<T> extends ApiResponse<T> {
        private String errorMessage = "Error occurred!";

        public Error() {
        }

        public Error(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

    public static class Loading<T> extends ApiResponse<T> {
    }

    public enum ResponseStatus {
        STATUS_OK("OK");

        public final String status;

        ResponseStatus(String status) {
            this.status = status;
        }
    }
}
