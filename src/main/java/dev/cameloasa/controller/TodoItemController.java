package dev.cameloasa.controller;

import dev.cameloasa.model.TodoItem;
import dev.cameloasa.service.TodoItemService;
import java.time.LocalDate;
import java.util.List;

public class TodoItemController {

  private final TodoItemService todoItemService;

  public TodoItemController(TodoItemService todoItemService) {
    this.todoItemService = todoItemService;
  }

  public TodoItem createItem(
      String title, String description, LocalDate deadline, Integer assigneeId) {
    return todoItemService.createItem(title, description, deadline, assigneeId);
  }

  public TodoItem getItemById(int id) {
    return todoItemService.findItemById(id);
  }

  public List<TodoItem> getAllItems() {
    return todoItemService.findAllItems();
  }

  public List<TodoItem> getItemsByDone(boolean done) {
    return todoItemService.findByDone(done);
  }

  public List<TodoItem> getItemsByAssigneeId(int assigneeId) {
    return todoItemService.findByAssigneeId(assigneeId);
  }

  public List<TodoItem> getUnassignedItems() {
    return todoItemService.findUnassigned();
  }

  public List<TodoItem> getItemsByDeadline(LocalDate deadline) {
    return todoItemService.findByDeadline(deadline);
  }

  public List<TodoItem> getOverdueItems() {
    return todoItemService.findOverdue();
  }

  public List<TodoItem> getItemsByDeadlineRange(LocalDate from, LocalDate to) {
    return todoItemService.findByDeadlineRange(from, to);
  }

  public List<TodoItem> getItemsByTitle(String title) {
    return todoItemService.findByTitle(title);
  }

  public boolean updateItem(
      int id,
      String newTitle,
      String newDescription,
      LocalDate newDeadline,
      Boolean newDone,
      Integer newAssigneeId) {
    return todoItemService.updateItem(
        id, newTitle, newDescription, newDeadline, newDone, newAssigneeId);
  }

  public boolean deleteItem(int id) {
    return todoItemService.deleteItem(id);
  }
}
