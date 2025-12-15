package br.ednascimento.taskmanager.infrastructure.persistence;

import br.ednascimento.taskmanager.domain.entity.TaskStatus;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskJdbcRepositoryImpl implements TaskJdbcRepository {

    private final JdbcClient jdbcClient;

    public TaskJdbcRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Long> create(TaskEntity taskEntity) {

        var sql = """
                  INSERT INTO task (title, description, status)
                  OUTPUT INSERTED.id
                  VALUES (:title, :description, :status)
                  """;
        return jdbcClient.sql(sql)
                .param("title", taskEntity.getTitle())
                .param("description", taskEntity.getDescription())
                .param("status", taskEntity.getStatus().name())
                .query(Long.class)
                .optional();
    }

    @Override
    public Optional<TaskEntity> findById(Long id) {

        var sql = "SELECT * FROM task WHERE id = :id";
        return jdbcClient.sql(sql)
                .param("id", id)
                .query(this::mapRow)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<List<TaskEntity>> findAll() {

        var sql = "SELECT * FROM task";
        var result = jdbcClient.sql(sql)
                .query(this::mapRow)
                .list();
        return Optional.of(result);
    }

    @Override
    public void update(TaskEntity taskEntity) {

        var sql = """
                  UPDATE task
                  SET title = :title,
                      description = :description
                  WHERE id = :id
                  """;
        jdbcClient.sql(sql)
                .param("title", taskEntity.getTitle())
                .param("description", taskEntity.getDescription())
                .param("id", taskEntity.getId())
                .update();
    }

    @Override
    public void updateStatus(TaskEntity taskEntity) {

        var sql = "UPDATE task SET status = :status WHERE id = :id ";
        jdbcClient.sql(sql)
                .param("status", taskEntity.getStatus().name())
                .param("id", taskEntity.getId())
                .update();
    }

    @Override
    public void delete(Long id) {
        var sql = "DELETE FROM task WHERE id = :id";
        jdbcClient.sql(sql)
                .param("id", id)
                .update();
    }

    private TaskEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TaskEntity.builder()
                .id(rs.getLong("id"))
                .title(rs.getString("title"))
                .description(rs.getString("description"))
                .status(TaskStatus.valueOf(rs.getString("status")))
                .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                .build();
    }
}
