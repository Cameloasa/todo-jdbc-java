package dev.cameloasa.web;

import dev.cameloasa.controller.TodoItemTaskController;
import dev.cameloasa.daoimpl.TodoItemTaskDaoImpl;
import dev.cameloasa.service.TodoItemTaskService;
import io.javalin.Javalin;
import java.time.LocalDate;

public class TodoItemTaskWebController {

  private static final TodoItemTaskController controller =
      new TodoItemTaskController(new TodoItemTaskService(new TodoItemTaskDaoImpl()));

  public static void registerRoutes(Javalin app) {

    // GET all tasks
    app.get(
        "/tasks",
        ctx -> {
          ctx.json(controller.getAllTasks());
        });

    // GET task by id
    app.get(
        "/tasks/{id}",
        ctx -> {
          int id = Integer.parseInt(ctx.pathParam("id"));
          ctx.json(controller.getTaskById(id));
        });

    // POST create task
    app.post(
        "/tasks",
        ctx -> {
          String title = ctx.formParam("title");
          String description = ctx.formParam("description");
          String deadlineStr = ctx.formParam("deadline");
          String todoItemIdStr = ctx.formParam("todoItemId");

          LocalDate deadline = LocalDate.parse(deadlineStr);
          Integer todoItemId = Integer.parseInt(todoItemIdStr);

          ctx.status(201).json(controller.createTask(title, description, deadline, todoItemId));
        });

    // PUT update task
    app.put(
        "/tasks/{id}",
        ctx -> {
          int id = Integer.parseInt(ctx.pathParam("id"));

          String newTitle = ctx.formParam("title");
          String newDescription = ctx.formParam("description");
          String newDeadlineStr = ctx.formParam("deadline");
          String newDoneStr = ctx.formParam("done");
          String newTodoItemIdStr = ctx.formParam("todoItemId");

          LocalDate newDeadline = newDeadlineStr == null ? null : LocalDate.parse(newDeadlineStr);
          Boolean newDone = newDoneStr == null ? null : Boolean.parseBoolean(newDoneStr);
          Integer newTodoItemId =
              newTodoItemIdStr == null ? null : Integer.parseInt(newTodoItemIdStr);

          ctx.json(
              controller.updateTask(
                  id, newTitle, newDescription, newDeadline, newDone, newTodoItemId));
        });

    // DELETE task
    app.delete(
        "/tasks/{id}",
        ctx -> {
          int id = Integer.parseInt(ctx.pathParam("id"));
          ctx.json(controller.deleteTask(id));
        });

    // GET tasks by todoItemId
    app.get(
        "/tasks/todoitem/{todoItemId}",
        ctx -> {
          int todoItemId = Integer.parseInt(ctx.pathParam("todoItemId"));
          ctx.json(controller.getTasksByTodoItemId(todoItemId));
        });

    // GET tasks by done status
    app.get(
        "/tasks/done/{status}",
        ctx -> {
          boolean status = Boolean.parseBoolean(ctx.pathParam("status"));
          ctx.json(controller.getTasksByDone(status));
        });

    // GET tasks by title
    app.get(
        "/tasks/title/{title}",
        ctx -> {
          ctx.json(controller.getTasksByTitle(ctx.pathParam("title")));
        });

    // GET overdue tasks
    app.get(
        "/tasks/overdue",
        ctx -> {
          ctx.json(controller.getOverdueTasks());
        });
  }
}
