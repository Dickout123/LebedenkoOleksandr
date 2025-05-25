package com.example.recipesapp.controller; // Переконайтеся, що пакет відповідає шляху до файлу

import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Позначає цей клас як REST контролер
@RequestMapping("/api/recipes") // Базовий шлях для всіх ендпоінтів цього контролера
@CrossOrigin(origins = "http://localhost:3000") // Дозволяє запити з Front-end на порту 3000 (якщо Front-end на React/Vue)
public class RecipeController {

    @Autowired // Ін'єктує екземпляр RecipeRepository
    private RecipeRepository recipeRepository;

    // Ендпоінт для отримання всіх рецептів
    // GET /api/recipes
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll(); // Використовуємо метод JpaRepository
    }

    // Ендпоінт для отримання рецепту за ID
    // GET /api/recipes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id); // Отримання за ID
        return recipe.map(ResponseEntity::ok) // Якщо рецепт знайдено, повернути 200 OK з рецептом
                .orElse(ResponseEntity.notFound().build()); // Якщо не знайдено, повернути 404 Not Found
    }

    // Ендпоінт для створення нового рецепту
    // POST /api/recipes
    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        Recipe savedRecipe = recipeRepository.save(recipe); // Зберігаємо новий рецепт
        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED); // Повертаємо 201 Created з новим рецептом
    }

    // Ендпоінт для оновлення існуючого рецепту
    // PUT /api/recipes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipeDetails) {
        // 1. Спробувати знайти рецепт за вказаним ID
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (recipeOptional.isPresent()) { // Якщо рецепт з таким ID існує
            Recipe existingRecipe = recipeOptional.get();

            // 2. Оновити поля існуючого рецепту даними з запиту
            existingRecipe.setName(recipeDetails.getName());
            existingRecipe.setDescription(recipeDetails.getDescription());
            existingRecipe.setImageUrl(recipeDetails.getImageUrl());
            existingRecipe.setIngredients(recipeDetails.getIngredients());
            existingRecipe.setInstructions(recipeDetails.getInstructions());

            // 3. Зберегти оновлений рецепт у базу даних
            Recipe updatedRecipe = recipeRepository.save(existingRecipe);

            // 4. Повернути 200 OK з оновленим рецептом
            return ResponseEntity.ok(updatedRecipe);
        } else { // Якщо рецепт з таким ID не знайдено
            // Повернути 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    // Ендпоінт для видалення рецепту за ID
    // DELETE /api/recipes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        // 1. Перевірити, чи існує рецепт з таким ID
        if (recipeRepository.existsById(id)) {
            // 2. Видалити рецепт з бази даних
            recipeRepository.deleteById(id);
            // 3. Повернути 204 No Content (успішне видалення без вмісту)
            return ResponseEntity.noContent().build();
        } else {
            // 4. Якщо рецепт не знайдено, повернути 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}