package com.gestfood.gestfood.business.exception;

public class InternalServerErrorException extends BusinessException {
    public InternalServerErrorException() {
        super("Ocorreu um erro interno no servidor");
    }

    public InternalServerErrorException(String message) {
        super(message);
    }
}
