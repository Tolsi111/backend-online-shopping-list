package graduate.work.onlineshoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import graduate.work.onlineshoppinglist.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
