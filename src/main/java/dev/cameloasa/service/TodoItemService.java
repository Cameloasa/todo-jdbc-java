package dev.cameloasa.service;

import dev.cameloasa.dao.TodoItemDao;
import dev.cameloasa.model.TodoItem;
import java.time.LocalDate;
import java.util.List;

public class TodoItemService {

  private final TodoItemDao todoItemDao;

  public TodoItemService(TodoItemDao todoItemDao) {
    this.todoItemDao = todoItemDao;
  }

  public TodoItem createItem(
      String title, String description, LocalDate deadline, Integer assigneeId) {

    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be empty");
    }

    if (deadline == null) {
      throw new IllegalArgumentException("Deadline cannot be null");
    }

    TodoItem item = new TodoItem(title, description, deadline, false, assigneeId);

    return todoItemDao.create(item);
  }

  public TodoItem findItemById(int id) {
    return todoItemDao
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("TodoItem not found: " + id));
  }

  public List<TodoItem> findAllItems() {
    return todoItemDao.findAll();
  }

  public boolean updateItem(
      int id,
      String newTitle,
      String newDescription,
      LocalDate newDeadline,
      Boolean newDone,
      Integer newAssigneeId) {

    TodoItem item = findItemById(id);

    if (newTitle != null && !newTitle.isBlank()) {
      item.setTitle(newTitle);
    }

    if (newDescription != null && !newDescription.isBlank()) {
      item.setDescription(newDescription);
    }

    if (newDeadline != null) {
      item.setDeadline(newDeadline);
    }

    if (newDone != null) {
      item.setDone(newDone);
    }

    if (newAssigneeId != null) {
      item.setAssigneeId(newAssigneeId);
    }

    return todoItemDao.update(item);
  }

  public boolean deleteItem(int id) {
    return todoItemDao.deleteById(id);
  }

  public List<TodoItem> findByDone(boolean done) {
    return todoItemDao.findByDoneStatus(done);
  }

  public List<TodoItem> findByAssigneeId(int assigneeId) {
    return todoItemDao.findByAssigneeId(assigneeId);
  }

  public List<TodoItem> findUnassigned() {
    return todoItemDao.findUnassigned();
  }

  public List<TodoItem> findByDeadline(LocalDate deadline) {
    return todoItemDao.findByDeadline(deadline);
  }

  public List<TodoItem> findOverdue() {
    return todoItemDao.findOverdue();
  }

  public List<TodoItem> findByDeadlineRange(LocalDate from, LocalDate to) {
    return todoItemDao.findByDeadlineRange(from, to);
  }

  public List<TodoItem> findByTitle(String title) {
    return todoItemDao.findByTitle(title);
  }
}
