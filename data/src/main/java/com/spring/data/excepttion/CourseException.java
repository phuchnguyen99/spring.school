package com.spring.data.excepttion;

public class CourseException extends Exception
{
    public CourseException() {
        super();
    }

    public CourseException(String message) {
        super(message);
    }

    public CourseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseException(Throwable cause) {
        super(cause);
    }
}
