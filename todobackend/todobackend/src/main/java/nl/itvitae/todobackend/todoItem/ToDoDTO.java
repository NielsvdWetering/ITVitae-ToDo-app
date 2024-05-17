package nl.itvitae.todobackend.todoItem;

import java.util.UUID;

public record ToDoDTO(String toDoText, UUID categoryId) {

    static ToDoDTO from(ToDo toDo) {
        return new ToDoDTO(toDo.getToDoText(), toDo.getCategory().getId());
    }
}
