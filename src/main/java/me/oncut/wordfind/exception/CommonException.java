package me.oncut.wordfind.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class CommonException extends RuntimeException {

    protected CommonException(final String message) {
        super(message);
    }

    protected CommonException(String message, Throwable cause) {
        super(message, cause);
    }
}