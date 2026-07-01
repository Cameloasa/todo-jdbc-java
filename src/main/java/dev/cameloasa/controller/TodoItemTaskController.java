package dev.cameloasa.controller;

import dev.cameloasa.model.TodoItemTask;
import dev.cameloasa.service.TodoItemTaskService;
import java.time.LocalDate;
import java.util.List;

public class TodoItemTaskController {

  private final TodoItemTaskService taskService;

  public TodoItemTaskController(TodoItemTaskService taskService) {
    this.taskService = taskService;
  }

  public TodoItemTask createTask(
      String title, String description, LocalDate deadline, Integer todoItemId) {
    return taskService.createTask(title, description, deadline, todoItemId);
  }

  public TodoItemTask getTaskById(int id) {
    return taskService.findTaskById(id);
  }

  public List<TodoItemTask> getAllTasks() {
    return taskService.findAllTasks();
  }

  public List<TodoItemTask> getTasksByTodoItemId(int todoItemId) {
    return taskService.findTasksByTodoItemId(todoItemId);
  }

  public List<TodoItemTask> getTasksByDone(boolean done) {
    return taskService.findTasksByDone(done);
  }

  public List<TodoItemTask> getTasksByTitle(String title) {
    return taskService.findTasksByTitle(title);
  }

  public List<TodoItemTask> getOverdueTasks() {
    return taskService.findOverdueTasks();
  }

  public boolean updateTask(
      int id,
      String newTitle,
      String newDescription,
      LocalDate newDeadline,
      Boolean newDone,
      Integer newTodoItemId) {
    return taskService.updateTask(
        id, newTitle, newDescription, newDeadline, newDone, newTodoItemId);
  }

  public boolean deleteTask(int id) {
    return taskService.deleteTask(id);
  }
}
