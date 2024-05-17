package nl.itvitae.todobackend.category;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.itvitae.todobackend.todoItem.ToDo;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String color;
    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private Set<ToDo> toDos;

    public Category(String name, String color) {
        this.name = name;
        this.color = color;
    }
}
