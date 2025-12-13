package br.ednascimento.taskmanager.application.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateTaskCommandTest {

    @Test
    void GIVEN_ValidTitleAndDescription_WHEN_CreateCommand_THEN_CommandShouldBeCreatedCorrectly() {

        // GIVEN
        var title = "Create unit tests";
        var description = "Write tests for application DTO";
        var expected = new CreateTaskCommand(title, description);

        // WHEN
        var actual = new CreateTaskCommand(title, description);

        // THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void GIVEN_ValidTitleAndNullDescription_WHEN_CreateCommand_THEN_CommandShouldBeCreated() {

        // GIVEN
        var title = "Task without description";
        var expected = new CreateTaskCommand(title, null);

        // WHEN
        var actual = new CreateTaskCommand(title, null);

        // THEN
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void GIVEN_NullTitle_WHEN_CreateCommand_THEN_ShouldThrowException() {

        // GIVEN
        var description = "Invalid command";
        var expected = "title must not be null";

        // WHEN
        var exception = assertThrows(IllegalArgumentException.class, () -> new CreateTaskCommand(null, description));

        // THEN
        assertThat(exception.getMessage()).hasToString(expected);
    }

    @Test
    void GIVEN_BlankTitle_WHEN_CreateCommand_THEN_ShouldThrowException() {

        // GIVEN
        var description = "Invalid command";
        var expected = "title must not be blank";

        // WHEN
        var exception = assertThrows(IllegalArgumentException.class, () -> new CreateTaskCommand("", description));

        // THEN
        assertThat(exception.getMessage()).hasToString(expected);
    }

    @Test
    void GIVEN_TitleWithOnlySpaces_WHEN_CreateCommand_THEN_ShouldThrowException() {

        // GIVEN
        var description = "Invalid command";
        var expectedMessage = "title must not be blank";

        // WHEN
        var exception = assertThrows(IllegalArgumentException.class, () -> new CreateTaskCommand("   ", description));

        // THEN
        assertThat(exception.getMessage()).hasToString(expectedMessage);
    }

    @ParameterizedTest(name = "equals should return false for invalid comparison: {0}")
    @MethodSource("invalidEqualsArguments")
    void GIVEN_Command_WHEN_ComparedWithInvalidObject_THEN_ShouldNotBeEqual(Object commandOther) {

        // GIVEN
        var command = new CreateTaskCommand("Title", "Description");

        // WHEN
        boolean result = command.equals(commandOther);

        // THEN
        assertThat(result).isFalse();
    }

    private static Stream<Object> invalidEqualsArguments() {
        return Stream.of(
                null,
                "invalid",
                new Object(),
                new CreateTaskCommand("Other title", "Description"),
                new CreateTaskCommand("Title", "Other description")
        );
    }

    @ParameterizedTest(name = "equals should return true for valid comparison")
    @MethodSource("validEqualsArguments")
    void GIVEN_TwoCommandsWithSameValues_WHEN_Compare_THEN_ShouldBeEqual(
            CreateTaskCommand other) {

        // GIVEN
        var command = new CreateTaskCommand("Title", "Description");

        // WHEN / THEN
        assertThat(command).isEqualTo(other);
    }

    private static Stream<CreateTaskCommand> validEqualsArguments() {
        return Stream.of(
                new CreateTaskCommand("Title", "Description")
        );
    }

    @ParameterizedTest(name = "hashCode should be equal for equal objects")
    @MethodSource("hashCodeEqualArguments")
    void GIVEN_EqualCommands_WHEN_HashCode_THEN_ShouldBeEqual(
            CreateTaskCommand other) {

        // GIVEN
        var command = new CreateTaskCommand("Title", "Description");

        // WHEN / THEN
        assertThat(command.hashCode()).isEqualTo(other.hashCode());
    }

    private static Stream<CreateTaskCommand> hashCodeEqualArguments() {
        return Stream.of(
                new CreateTaskCommand("Title", "Description")
        );
    }

    @ParameterizedTest(name = "hashCode should be different for different objects")
    @MethodSource("hashCodeDifferentArguments")
    void GIVEN_DifferentCommands_WHEN_HashCode_THEN_ShouldBeDifferent(
            CreateTaskCommand other) {

        // GIVEN
        var command = new CreateTaskCommand("Title", "Description");

        // WHEN / THEN
        assertThat(command.hashCode()).isNotEqualTo(other.hashCode());
    }

    private static Stream<CreateTaskCommand> hashCodeDifferentArguments() {
        return Stream.of(
                new CreateTaskCommand("Other title", "Description"),
                new CreateTaskCommand("Title", "Other description")
        );
    }

    @Test
    void GIVEN_Command_WHEN_TitleCalled_THEN_ShouldReturnTitle() {

        // GIVEN
        var title = "Task title";
        var command = new CreateTaskCommand(title, "Task description");
        var expected = "Task title";

        // WHEN
        var actualTitle = command.title();

        // THEN
        assertThat(actualTitle).isEqualTo(expected);
    }

    @Test
    void GIVEN_Command_WHEN_DescriptionCalled_THEN_ShouldReturnDescription() {

        // GIVEN
        var title = "Task title";
        var description = "Task description";
        var command = new CreateTaskCommand(title, description);
        var expected = "Task description";

        // WHEN
        var actualDescription = command.description();

        // THEN
        assertThat(actualDescription).isEqualTo(expected);
    }
}
