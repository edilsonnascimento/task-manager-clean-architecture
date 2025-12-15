package br.ednascimento.taskmanager.domain.entity;

import br.ednascimento.taskmanager.domain.exception.InvalidTaskException;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;

    public Task(String title, String description) {
        validateTitle(title);
        this.title = title;
        this.description = description;
        this.status = TaskStatus.PENDING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void markInProgress() {
        if (this.status != TaskStatus.PENDING)
            throw new InvalidTaskException("Only PENDING tasks can be started");
        this.status = TaskStatus.IN_PROGRESS;
    }

    public void markDone() {
        if (this.status != TaskStatus.IN_PROGRESS)
            throw new InvalidTaskException("Only IN_PROGRESS tasks can be completed");
        this.status = TaskStatus.DONE;
    }

    private void validateTitle(String title) {
        if (Objects.isNull(title))
            throw new IllegalArgumentException("title must not be null");
        if (title.isBlank())
            throw new IllegalArgumentException("title must not be blank");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
