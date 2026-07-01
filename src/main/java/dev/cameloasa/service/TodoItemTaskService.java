package dev.cameloasa.service;

import dev.cameloasa.dao.TodoItemTaskDao;
import dev.cameloasa.model.TodoItemTask;
import java.time.LocalDate;
import java.util.List;

public class TodoItemTaskService {

  private final TodoItemTaskDao taskDao;

  public TodoItemTaskService(TodoItemTaskDao taskDao) {
    this.taskDao = taskDao;
  }

  public TodoItemTask createTask(
      String title, String description, LocalDate deadline, Integer todoItemId) {

    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("Task title cannot be empty");
    }

    if (deadline == null) {
      throw new IllegalArgumentException("Task deadline cannot be null");
    }

    if (todoItemId == null) {
      throw new IllegalArgumentException("Task must belong to a TodoItem");
    }

    TodoItemTask task = new TodoItemTask(title, description, deadline, false, todoItemId);

    return taskDao.create(task);
  }

  public TodoItemTask findTaskById(int id) {
    return taskDao
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Task not found: " + id));
  }

  public List<TodoItemTask> findAllTasks() {
    return taskDao.findAll();
  }

  public List<TodoItemTask> findTasksByTodoItemId(int todoItemId) {
    return taskDao.findByTodoItemId(todoItemId);
  }

  public List<TodoItemTask> findTasksByDone(boolean done) {
    return taskDao.findByDoneStatus(done);
  }

  public List<TodoItemTask> findTasksByTitle(String title) {
    return taskDao.findByTitle(title);
  }

  public List<TodoItemTask> findOverdueTasks() {
    return taskDao.findOverdueTasks();
  }

  public boolean updateTask(
      int id,
      String newTitle,
      String newDescription,
      LocalDate newDeadline,
      Boolean newDone,
      Integer newTodoItemId) {
    TodoItemTask task = findTaskById(id);

    if (newTitle != null && !newTitle.isBlank()) {
      task.setTitle(newTitle);
    }

    if (newDescription != null && !newDescription.isBlank()) {
      task.setDescription(newDescription);
    }

    if (newDeadline != null) {
      task.setDeadline(newDeadline);
    }

    if (newDone != null) {
      task.setDone(newDone);
    }

    if (newTodoItemId != null) {
      task.setTodoItemId(newTodoItemId);
    }

    return taskDao.update(task);
  }

  public boolean deleteTask(int id) {
    return taskDao.deleteById(id);
  }
}
