package com.gestfood.gestfood.business.exception;

public class NoContentException extends BusinessException {
    public NoContentException(String resourceName) {
        super(String.format("Nenhum conteúdo de %s disponível", resourceName));
    }

    public NoContentException(String resourceName, Throwable cause) {
        super(String.format("Nenhum conteúdo de %s disponível", resourceName), cause);
    }

}
