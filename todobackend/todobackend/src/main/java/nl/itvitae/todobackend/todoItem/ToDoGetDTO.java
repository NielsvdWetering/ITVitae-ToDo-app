package nl.itvitae.todobackend.todoItem;

import java.util.UUID;

public record ToDoGetDTO(UUID id, boolean completed, String toDoText, long itemIndex, UUID categoryId) {
    static ToDoGetDTO from(ToDo toDo) {
        UUID categoryId;
        if (toDo.getCategory() == null) {
            categoryId = null;
        } else {
            categoryId = toDo.getCategory().getId();
        }
        return new ToDoGetDTO(toDo.getId(), toDo.isCompleted(), toDo.getToDoText(), toDo.getItemIndex(), categoryId);
    }
}
