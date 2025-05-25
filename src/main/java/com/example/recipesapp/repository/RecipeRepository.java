package com.example.recipesapp.repository; // Переконайтеся, що пакет відповідає шляху до файлу

import com.example.recipesapp.model.Recipe; // Імпорт класу Recipe
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // Для анотації @Repository

@Repository // Позначає цей інтерфейс як Spring Data Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    // Spring Data JPA автоматично надає реалізації для базових CRUD операцій
    // (findAll, findById, save, deleteById тощо).
    // Вам не потрібно писати тут жодного коду, якщо вам потрібні тільки стандартні операції.
}