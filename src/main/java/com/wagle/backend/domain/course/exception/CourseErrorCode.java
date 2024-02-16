package com.wagle.backend.domain.course.exception;

public enum CourseErrorCode {

    FAIL_FIND_COURSE_COMMENT("코스에 존재하지 않는 코멘트입니다."),
    FAIL_FIND_COURSE("존재하지 않는 코스입니다.");

    public final String message;

    CourseErrorCode(String message) {
        this.message = message;
    }
}
