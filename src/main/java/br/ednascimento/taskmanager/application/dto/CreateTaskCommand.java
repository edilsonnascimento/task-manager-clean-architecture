package br.ednascimento.taskmanager.application.dto;

import java.util.Objects;

public class CreateTaskCommand {

    private final String title;
    private final String description;

    public CreateTaskCommand(String title, String description) {
        validateTitle(title);
        this.title = title;
        this.description = description;
    }

    private void validateTitle(String title) {
        if (Objects.isNull(title))
            throw new IllegalArgumentException("title must not be null");
        if (title.isBlank())
            throw new IllegalArgumentException("title must not be blank");
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreateTaskCommand that = (CreateTaskCommand) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }
}
