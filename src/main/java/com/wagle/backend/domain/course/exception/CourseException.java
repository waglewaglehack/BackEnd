package com.wagle.backend.domain.course.exception;

import lombok.Getter;

@Getter
public class CourseException extends RuntimeException{

    private final CourseErrorCode courseErrorCode;

    private final String message;

    public CourseException(CourseErrorCode errorCode, String message) {
        this.courseErrorCode = errorCode;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "[%s] %s".formatted(courseErrorCode, message);
    }
}
