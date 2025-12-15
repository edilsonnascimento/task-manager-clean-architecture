package br.ednascimento.taskmanager.domain.entity;

public enum TaskStatus {

    PENDING,
    IN_PROGRESS,
    DONE;

    public boolean isPending() {
        return this.equals(PENDING);
    }

    public boolean isDone() {
        return this.equals(DONE);
    }
}
