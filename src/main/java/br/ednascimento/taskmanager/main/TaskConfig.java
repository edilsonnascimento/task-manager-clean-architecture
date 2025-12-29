package br.ednascimento.taskmanager.main;

import br.ednascimento.taskmanager.application.gateways.TaskGateway;
import br.ednascimento.taskmanager.application.usecases.*;
import br.ednascimento.taskmanager.infrastructure.gateways.TaskEntityMapper;
import br.ednascimento.taskmanager.infrastructure.gateways.TaskRepositoryGateway;
import br.ednascimento.taskmanager.infrastructure.persistence.TaskJdbcRepository;
import br.ednascimento.taskmanager.infrastructure.web.adapter.DeleteTaskHttpAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfig {

    @Bean
    TaskRepositoryGateway createTaskRepositoryGateway(TaskJdbcRepository taskJdbcRepository,
                                                      TaskEntityMapper taskEntityMapper) {
        return new TaskRepositoryGateway(taskJdbcRepository, taskEntityMapper);
    }

    @Bean
    CreateTaskInteractor createTaskInteractor(TaskGateway gateway) {
        return new CreateTaskInteractor(gateway);
    }

    @Bean
    ListTasksInteractor listTasksInteractor(TaskGateway gateway) {
        return new ListTasksInteractor(gateway);
    }

    @Bean
    FindTaskByIdInteractor findTaskByIdInteractor(TaskGateway gateway) {
        return new FindTaskByIdInteractor(gateway);
    }

    @Bean
    UpdateTaskInteractor updateTaskInteractor(TaskGateway gateway) {
        return new UpdateTaskInteractor(gateway);
    }

    @Bean
    UpdateInProgressTaskInteractor updateInProgressTaskInteractor(TaskGateway gateway) {
        return new UpdateInProgressTaskInteractor(gateway);
    }

    @Bean
    UpdateDoneTaskInteractor updateDoneTaskInteractor(TaskGateway gateway) {
        return new UpdateDoneTaskInteractor(gateway);
    }

    @Bean
    DeleteTaskInteractor deleteTaskInteractor(TaskGateway gateway) {
        return new DeleteTaskInteractor(gateway);
    }
}
