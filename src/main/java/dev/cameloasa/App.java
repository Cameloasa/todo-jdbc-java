package dev.cameloasa;

import dev.cameloasa.dao.PersonDao;
import dev.cameloasa.dao.TodoItemDao;
import dev.cameloasa.daoimpl.PersonDaoImpl;
import dev.cameloasa.daoimpl.TodoItemDaoImpl;
import dev.cameloasa.model.Person;
import dev.cameloasa.model.TodoItem;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class App {

    public static void main(String[] args) {

        PersonDao personDao = new PersonDaoImpl();
        TodoItemDao todoItemDao = new TodoItemDaoImpl();

        // Create Person
        Person john = new Person("John", "Doe");
        john = personDao.create(john);
        System.out.println("Created person: " + john);

        // Find Person by ID
        Optional<Person> foundPerson = personDao.findById(john.getPersonId());
        foundPerson.ifPresent(p -> System.out.println("Found person: " + p));

        // Find all Persons
        List<Person> allPeople = personDao.findAll();
        System.out.println("All people: " + allPeople);

        // Find by first name
        List<Person> johns = personDao.findByFirstName("John");
        System.out.println("People named John: " + johns);

        // Update Person
        john.setFirstName("Jane");
        john.setLastName("Smith");
        boolean updated = personDao.update(john);
        System.out.println("Person updated: " + updated);

        // Delete Person
        boolean deleted = personDao.delete(john.getPersonId());
        System.out.println("Person deleted: " + deleted);


        // Create TodoItem
        TodoItem todo = new TodoItem(
                "Buy milk",
                "Go to the grocery store",
                LocalDate.now(),
                false,
                1
        );

        todo = todoItemDao.create(todo);
        System.out.println("Created todo item: " + todo);

        // Find TodoItem by ID
        Optional<TodoItem> foundTodo = todoItemDao.findById(todo.getTodoId());
        foundTodo.ifPresent(t -> System.out.println("Found todo item: " + t));

        // Find all TodoItems
        List<TodoItem> allTodos = todoItemDao.findAll();
        System.out.println("All todo items: " + allTodos);

        // Find by done status
        List<TodoItem> notDone = todoItemDao.findByDoneStatus(false);
        System.out.println("Todo items not done: " + notDone);

        // Find by assignee
        List<TodoItem> assignedTo1 = todoItemDao.findByAssigneeId(1);
        System.out.println("Todo items assigned to 1: " + assignedTo1);

        // Update TodoItem
        todo.setDone(true);
        todo.setAssigneeId(2);
        boolean todoUpdated = todoItemDao.update(todo);
        System.out.println("Todo item updated: " + todoUpdated);

        // Delete TodoItem
        boolean todoDeleted = todoItemDao.deleteById(todo.getTodoId());
        System.out.println("Todo item deleted: " + todoDeleted);
    }
}
