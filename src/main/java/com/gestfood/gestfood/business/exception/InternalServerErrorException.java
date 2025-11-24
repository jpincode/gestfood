package com.gestfood.gestfood.business.exception;

public class InternalServerErrorException extends BusinessException {
    public InternalServerErrorException(String message) {
        super("Ocorreu um erro interno no servidor: " + message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super("Ocorreu um erro interno no servidor: " + message, cause);
    }

}
