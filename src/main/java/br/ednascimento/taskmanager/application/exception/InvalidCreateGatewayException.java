package br.ednascimento.taskmanager.application.exception;

import br.ednascimento.taskmanager.domain.exception.DomainException;

public class InvalidCreateGatewayException extends DomainException {

    public InvalidCreateGatewayException(String message) {
        super(message);
    }
}