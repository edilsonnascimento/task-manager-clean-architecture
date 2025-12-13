package br.ednascimento.taskmanager.domain.exception;

public class InvalidTaskException extends DomainException {

    public InvalidTaskException(String message) {
        super(message);
    }
}
