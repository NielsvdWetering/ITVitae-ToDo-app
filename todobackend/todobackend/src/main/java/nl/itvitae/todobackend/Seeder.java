package nl.itvitae.todobackend;

import lombok.RequiredArgsConstructor;
import nl.itvitae.todobackend.category.Category;
import nl.itvitae.todobackend.category.CategoryRepository;
import nl.itvitae.todobackend.todoItem.ToDo;
import nl.itvitae.todobackend.todoItem.ToDoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
    private final ToDoRepository toDoRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            categoryRepository.saveAll(List.of(
                    new Category("Werk", "#c2554e"),
                    new Category("Privé", "#99d192"),
                    new Category("Hobby", "#fcba03"),
                    new Category("ITVitae", "#5f97ec")
            ));
        }
        if (toDoRepository.count() == 0) {
            String[] toDoItems = {"Make a ToDo app","ToDo items kunnen weergeven","Zorg dat items afgestreept kunnen worden","Bouw de mogelijkheid om ToDo's toe te kunnen voegen","Items kunnen verwijderen","Voeg categoriën toe"};

            for (String toDo : toDoItems) {
                ToDo toDoObj = new ToDo(toDo);
                toDoObj.setItemIndex(toDoRepository.count() + 1);
                toDoRepository.save(toDoObj);
            }
        }


    }
}
