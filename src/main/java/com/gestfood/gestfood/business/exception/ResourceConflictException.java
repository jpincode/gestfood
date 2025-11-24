package com.gestfood.gestfood.business.exception;

public class ResourceConflictException extends BusinessException {
    public ResourceConflictException(String resource, long id) {
        super(String.format("Já existente: %s com ID %d", resource, id));
    }

    public ResourceConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
