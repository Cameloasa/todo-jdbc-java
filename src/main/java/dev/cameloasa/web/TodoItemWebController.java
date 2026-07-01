package dev.cameloasa.web;

import dev.cameloasa.controller.TodoItemController;
import dev.cameloasa.daoimpl.TodoItemDaoImpl;
import dev.cameloasa.service.TodoItemService;
import io.javalin.Javalin;
import java.time.LocalDate;

public class TodoItemWebController {

  private static final TodoItemController controller =
      new TodoItemController(new TodoItemService(new TodoItemDaoImpl()));

  public static void registerRoutes(Javalin app) {

    // GET all items
    app.get(
        "/items",
        ctx -> {
          ctx.json(controller.getAllItems());
        });

    // GET item by id
    app.get(
        "/items/{id}",
        ctx -> {
          int id = Integer.parseInt(ctx.pathParam("id"));
          ctx.json(controller.getItemById(id));
        });

    // POST create item
    app.post(
        "/items",
        ctx -> {
          String title = ctx.formParam("title");
          String description = ctx.formParam("description");
          String deadlineStr = ctx.formParam("deadline");
          String assigneeStr = ctx.formParam("assigneeId");

          LocalDate deadline = LocalDate.parse(deadlineStr);
          Integer assigneeId = assigneeStr == null ? null : Integer.parseInt(assigneeStr);

          ctx.json(controller.createItem(title, description, deadline, assigneeId));
        });

    // PUT update item
    app.put(
        "/items/{id}",
        ctx -> {
          int id = Integer.parseInt(ctx.pathParam("id"));

          String newTitle = ctx.formParam("title");
          String newDescription = ctx.formParam("description");
          String newDeadlineStr = ctx.formParam("deadline");
          String newDoneStr = ctx.formParam("done");
          String newAssigneeStr = ctx.formParam("assigneeId");

          LocalDate newDeadline = newDeadlineStr == null ? null : LocalDate.parse(newDeadlineStr);
          Boolean newDone = newDoneStr == null ? null : Boolean.parseBoolean(newDoneStr);
          Integer newAssigneeId = newAssigneeStr == null ? null : Integer.parseInt(newAssigneeStr);

          ctx.json(
              controller.updateItem(
                  id, newTitle, newDescription, newDeadline, newDone, newAssigneeId));
        });

    // DELETE item
    app.delete(
        "/items/{id}",
        ctx -> {
          int id = Integer.parseInt(ctx.pathParam("id"));
          ctx.json(controller.deleteItem(id));
        });

    // GET done / not done
    app.get(
        "/items/done/{status}",
        ctx -> {
          boolean status = Boolean.parseBoolean(ctx.pathParam("status"));
          ctx.json(controller.getItemsByDone(status));
        });

    // GET unassigned
    app.get(
        "/items/unassigned",
        ctx -> {
          ctx.json(controller.getUnassignedItems());
        });

    // GET overdue
    app.get(
        "/items/overdue",
        ctx -> {
          ctx.json(controller.getOverdueItems());
        });

    // GET by deadline
    app.get(
        "/items/deadline/{date}",
        ctx -> {
          LocalDate date = LocalDate.parse(ctx.pathParam("date"));
          ctx.json(controller.getItemsByDeadline(date));
        });

    // GET by title
    app.get(
        "/items/title/{title}",
        ctx -> {
          ctx.json(controller.getItemsByTitle(ctx.pathParam("title")));
        });
  }
}
