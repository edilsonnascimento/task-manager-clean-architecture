# 🚀 Task Manager API — Final Delivery README

API RESTful para gerenciamento de tarefas (**To-Do List**), desenvolvida em **Java 21** com **Spring Boot 4**, seguindo os princípios da **Clean Architecture**, utilizando **JDBC puro**, **SQL Server**, **Flyway** e **Docker**.

---

## 📌 Visão Geral

Este projeto implementa uma API que permite ao usuário:

- Criar tarefas
- Listar tarefas
- Atualizar tarefas
- Alterar status (PENDING, IN_PROGRESS, DONE)
- Excluir tarefas

A aplicação foi desenvolvida **exclusivamente com requisitos obrigatórios do desafio técnico**, sem uso de JPA/Hibernate.

---

## 🧰 Tecnologias Utilizadas

- Java 21
- Spring Boot 4.0.0
- Spring Web
- Spring JDBC
- SQL Server
- Docker / Docker Compose
- Flyway
- Gradle (Kotlin DSL)
- Git (GitFlow)

---

## 🧱 Arquitetura — Clean Architecture

```
src/main/java/br/ednascimento/taskmanager
├── application
│   ├── exception
│   ├── gateways
│   └── usecases
├── domain
│   ├── entity
│   └── exception
├── infrastructure
│   ├── gateways
│   ├── persistence
│   └── web
└── TaskManagerCleanArchitectureApplication.java
```

---

## 🐳 Subindo o Banco de Dados (Obrigatório)

```bash
docker-compose up -d
docker exec -it manager-task-db bash
```

```bash
/opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P 'SqlServer@2025' -C
```

```sql
CREATE DATABASE [manager-task-db];
GO
USE [manager-task-db];
GO
```

---

## ⚙️ Executando a Aplicação

```bash
./gradlew bootRun
```

Aplicação disponível em `http://localhost:8090`

---

## 🔌 Exemplos de Requisições (cURL)

```bash
curl http://localhost:8090/v1/tasks | jq
```

```bash
curl -X POST http://localhost:8090/v1/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Minha primeira task","description":"Descrição da task"}'
```

```bash
curl -X PUT http://localhost:8090/v1/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Minha primeira task - ALTERADA"}'
```

```bash
curl -X PATCH http://localhost:8090/v1/tasks/1/done
curl -X PATCH http://localhost:8090/v1/tasks/1/in-progress
curl -X DELETE http://localhost:8090/v1/tasks/1
```

---
