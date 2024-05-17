package nl.itvitae.todobackend.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173/")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/find-all")
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category, UriComponentsBuilder uriComponentsBuilder) {
        if (category.getName() == null || category.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        categoryRepository.save(category);
        var location = uriComponentsBuilder.path("/api/v1/category/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(location).body(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") UUID id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Category category = optionalCategory.get();
        return ResponseEntity.ok().body(category);
    }
}
