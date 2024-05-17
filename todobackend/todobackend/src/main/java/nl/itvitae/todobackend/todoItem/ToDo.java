package nl.itvitae.todobackend.todoItem;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.itvitae.todobackend.category.Category;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private boolean completed;
    private String toDoText;
    private long itemIndex;
    @ManyToOne
    @JsonBackReference
    private Category category;

    public ToDo (String toDoText, Category category) {
        this.toDoText = toDoText;
        this.category = category;
        completed = false;
    }
    public ToDo (String toDoText) {
        this.toDoText = toDoText;
        completed = false;
    }

}
