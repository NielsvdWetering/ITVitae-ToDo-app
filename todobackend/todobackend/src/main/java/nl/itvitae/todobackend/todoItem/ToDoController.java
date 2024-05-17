package nl.itvitae.todobackend.todoItem;

import lombok.RequiredArgsConstructor;
import nl.itvitae.todobackend.category.Category;
import nl.itvitae.todobackend.category.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
@CrossOrigin("http://localhost:5173/")
public class ToDoController {

    private final ToDoRepository toDoRepository;
    private final CategoryRepository categoryRepository;


    @PostMapping("/addItem")
    public ResponseEntity<ToDoDTO> addToDo(@RequestBody ToDoDTO toDoDTO, UriComponentsBuilder uriComponentsBuilder) {
        var toDoText = toDoDTO.toDoText();
        if (toDoText == null) {
            return ResponseEntity.badRequest().build();
        }
        ToDo toDo = new ToDo(toDoText);

        var categoryId = toDoDTO.categoryId();
        if (categoryId != null) {
            var possibleCategory = categoryRepository.findById(categoryId);
            if (possibleCategory.isPresent()) {
                Category category = possibleCategory.get();
                toDo.setCategory(category);
            }
        }

        long itemIndex = toDoRepository.count() + 1;
        toDo.setItemIndex(itemIndex);
        toDoRepository.save(toDo);
        var location = uriComponentsBuilder.path("/api/v1/category/{id}").buildAndExpand(toDo.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/all-items")
    public Iterable<ToDoGetDTO> findAll() {
        return toDoRepository.findAll().stream().map(ToDoGetDTO::from).toList();
    }

    @PatchMapping("/toggleDone/{id}/{status}")
    public ResponseEntity<ToDo> toggleDone(@PathVariable("id") UUID id,@PathVariable("status") boolean status) {
        Optional<ToDo> possibleToDo = toDoRepository.findById(id);
        if (possibleToDo.isEmpty()) return ResponseEntity.notFound().build();
        ToDo toDo = possibleToDo.get();
        toDo.setCompleted(status);
        toDoRepository.save(toDo);
        return ResponseEntity.ok(toDo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") UUID id) {
        Optional<ToDo> possibleToDo = toDoRepository.findById(id);
        if (possibleToDo.isEmpty()) return ResponseEntity.notFound().build();
        ToDo toDo = possibleToDo.get();
        long index = toDo.getItemIndex() + 1;
        toDoRepository.deleteById(id);
        for (long i = index; i <= toDoRepository.count() + 1; i++) {
            patchIndex(i);
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    public void patchIndex(long itemIndex) {
        ToDo toDo = toDoRepository.findByItemIndex(itemIndex);
        toDo.setItemIndex(toDo.getItemIndex() - 1);
        toDoRepository.save(toDo);
    }
}
