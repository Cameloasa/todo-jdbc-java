package dev.cameloasa.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

  public static void initialize() {
    try (Connection conn = ConnectionManager.getConnection();
        Statement stmt = conn.createStatement()) {

      stmt.execute(
          """
                CREATE TABLE IF NOT EXISTS person (
                    person_id INTEGER PRIMARY KEY,
                    first_name TEXT,
                    last_name TEXT
                );
            """);

      stmt.execute(
          """
                CREATE TABLE IF NOT EXISTS todo_item (
                    todo_id INTEGER PRIMARY KEY,
                    title TEXT,
                    description TEXT,
                    deadline TEXT,
                    done INTEGER DEFAULT 0,
                    assignee_id INTEGER,
                    FOREIGN KEY (assignee_id)
                        REFERENCES person(person_id)
                );
            """);

      stmt.execute(
          """
                CREATE INDEX IF NOT EXISTS idx_todo_item_assignee
                    ON todo_item (assignee_id);
            """);

      stmt.execute(
          """
                CREATE TABLE IF NOT EXISTS todo_item_task (
                    task_id INTEGER PRIMARY KEY,
                    title TEXT,
                    description TEXT,
                    done INTEGER DEFAULT 0,
                    todo_item_id INTEGER,
                    FOREIGN KEY (todo_item_id)
                        REFERENCES todo_item(todo_id)
                );
            """);

      stmt.execute(
          """
                CREATE INDEX IF NOT EXISTS idx_task_todo_item
                    ON todo_item_task (todo_item_id);
            """);

      System.out.println("SQLite schema initialized successfully.");

    } catch (SQLException e) {
      throw new RuntimeException("Failed to initialize database schema", e);
    }
  }
}
