package dev.cameloasa;

import dev.cameloasa.db.DatabaseInitializer;
import dev.cameloasa.web.PersonWebController;
import dev.cameloasa.web.TodoItemTaskWebController;
import dev.cameloasa.web.TodoItemWebController;
import io.javalin.Javalin;

public class WebApp {
  public static void main(String[] args) {

    DatabaseInitializer.initialize();

    Javalin app = Javalin.create().start(7000);

    PersonWebController.registerRoutes(app);
    TodoItemWebController.registerRoutes(app);
    TodoItemTaskWebController.registerRoutes(app);
  }
}
