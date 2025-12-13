package br.ednascimento.taskmanager.application.exception;

import br.ednascimento.taskmanager.domain.exception.DomainException;

public class NotFoundException extends DomainException {

    public NotFoundException(String message) {
        super(message);
    }
}
