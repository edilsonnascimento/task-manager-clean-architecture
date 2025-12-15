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

    public boolean isInProgress() {
        return this.equals(IN_PROGRESS);
    }
}
