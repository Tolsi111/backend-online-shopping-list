package graduate.work.onlineshoppinglist.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import graduate.work.onlineshoppinglist.exceptions.error_handling.error.ErrorCodes;
import graduate.work.onlineshoppinglist.exceptions.error_handling.response.CustomErrorResponse;
import graduate.work.onlineshoppinglist.model.Recipe;
import graduate.work.onlineshoppinglist.model.dto.RecipeDTO;
import graduate.work.onlineshoppinglist.repository.RecipeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecipeService {

    private RecipeRepository recipeRepository;

    public Recipe saveRecipe(RecipeDTO theRecipe) {
        Recipe newRecipe = new Recipe(0, theRecipe.getTitle(), theRecipe.getDescription(), theRecipe.getServings(), theRecipe.getTime(),
                                      null, null);
        try {
            return recipeRepository.save(newRecipe);
        } catch (Exception e) {
            throw new CustomErrorResponse(ErrorCodes.INTERNAL_SERVER_ERROR_WHILE_CREATING_RESOURCE);
        }
    }

    public List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipe(long recipeId) {
        Optional<Recipe> theRecipe = recipeRepository.findById(recipeId);
        if (theRecipe.isEmpty()) {
            throw new CustomErrorResponse(ErrorCodes.NOT_FOUND_RECIPE);
        }
        return theRecipe.get();
    }

    public Recipe getRecipeRecommendation() {
        return new Recipe(-1, "Piept de pui la gratar", "Cea mai anabolica mancare. Perfect pentru sportivi", 3, 30, null, null);
    }

    public Recipe updateRecipe(long recipeId,
                               RecipeDTO newRecipe) {
        Recipe oldRecipe = this.getRecipe(recipeId);
        oldRecipe.setTitle(newRecipe.getTitle());
        oldRecipe.setDescription(newRecipe.getDescription());
        oldRecipe.setServings(newRecipe.getServings());
        return recipeRepository.save(oldRecipe);
    }

    public Recipe deleteRecipe(long recipeId) {
        Recipe theRecipe = this.getRecipe(recipeId);
        recipeRepository.delete(theRecipe);
        return theRecipe;
    }
}
