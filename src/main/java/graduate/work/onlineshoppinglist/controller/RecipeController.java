package graduate.work.onlineshoppinglist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import graduate.work.onlineshoppinglist.exceptions.error_handling.response.Response;
import graduate.work.onlineshoppinglist.model.Recipe;
import graduate.work.onlineshoppinglist.model.dto.RecipeDTO;
import graduate.work.onlineshoppinglist.model.responses.DataResponse;
import graduate.work.onlineshoppinglist.service.RecipeService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public Response<Recipe> addRecipe(@RequestBody RecipeDTO theRecipe) {
        return new Response<>(new DataResponse<>(recipeService.saveRecipe(theRecipe)));
    }

    @GetMapping
    public Response<Recipe> getRecipes() {
        return new Response<>(new DataResponse<>((recipeService.getRecipes())));
    }

    @GetMapping("/{recipeId}")
    public Response<Recipe> getRecipe(@PathVariable long recipeId) {
        return new Response<>(new DataResponse<>(recipeService.getRecipe(recipeId)));
    }

    @GetMapping("/recommendation")
    public Response<Recipe> getRecipeRecommendation() {
        return new Response<>(new DataResponse<>(recipeService.getRecipeRecommendation()));
    }

//    @PutMapping("/{recipeId}")
//    public Response<Recipe> updateRecipe(@PathVariable long recipeId,
//                               @RequestBody RecipeDTO newRecipe) {
//        return new Response<>(new DataResponse<>(recipeService.updateRecipe(recipeId, newRecipe)));
//    }

    @DeleteMapping("/{recipeId}")
    public Response<Recipe> deleteRecipe(@PathVariable long recipeId) {
        return new Response<>(new DataResponse<>(recipeService.deleteRecipe(recipeId)));
    }
}
