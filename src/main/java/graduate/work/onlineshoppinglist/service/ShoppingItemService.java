package graduate.work.onlineshoppinglist.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import graduate.work.onlineshoppinglist.exceptions.error_handling.error.ErrorCodes;
import graduate.work.onlineshoppinglist.exceptions.error_handling.response.CustomErrorResponse;
import graduate.work.onlineshoppinglist.model.Item;
import graduate.work.onlineshoppinglist.model.Recipe;
import graduate.work.onlineshoppinglist.model.ShoppingItem;
import graduate.work.onlineshoppinglist.model.ShoppingList;
import graduate.work.onlineshoppinglist.model.dto.ShoppingItemDTO;
import graduate.work.onlineshoppinglist.repository.ShoppingItemRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShoppingItemService {

    private ShoppingItemRepository shoppingItemRepository;
    private ItemService itemService;
    private ShoppingListService shoppingListService;
    private RecipeService recipeService;

    public ShoppingItem saveShoppingItem(ShoppingItemDTO shoppingItemDTO) {
        Item theItem = this.itemService.getItemById(shoppingItemDTO.getItemId());
        ShoppingList shoppingList = new ShoppingList();
        Recipe recipe = new Recipe();
        if (shoppingItemDTO.getShoppingListId() == null && shoppingItemDTO.getRecipeId() == null) {
            throw new CustomErrorResponse(ErrorCodes.BAD_REQUEST_NO_LIST_PROVIDED);
        } else if (shoppingItemDTO.getShoppingListId() != null) {
            shoppingList = shoppingListService.getShoppingList(shoppingItemDTO.getShoppingListId());
            recipe = null;
        } else if (shoppingItemDTO.getRecipeId() != null) {
            recipe = recipeService.getRecipe(shoppingItemDTO.getRecipeId());
            shoppingList = null;
        }
        ShoppingItem newItem = new ShoppingItem(0, shoppingItemDTO.getAmount(), shoppingList, recipe, theItem);
        try {
            return shoppingItemRepository.save(newItem);
        } catch (Exception e) {
            throw new CustomErrorResponse(ErrorCodes.INTERNAL_SERVER_ERROR_WHILE_CREATING_RESOURCE);
        }
    }

    public List<ShoppingItem> getShoppingItemsByRecipeId(long itemId) {
        Recipe recipe = this.recipeService.getRecipe(itemId);
        return recipe.getIngredients();
    }

    public ShoppingItem updateShoppingItemAmount(long itemId,
                                                 int amount) {
        ShoppingItem oldItem = this.getShoppingItem(itemId);
        oldItem.setAmount(amount);
        return this.shoppingItemRepository.save(oldItem);
    }

    public ShoppingItem getShoppingItem(long itemId) {
        Optional<ShoppingItem> theItem = shoppingItemRepository.findById(itemId);
        if (theItem.isEmpty()) {
            throw new CustomErrorResponse(ErrorCodes.NOT_FOUND_SHOPPING_ITEM);
        }
        return theItem.get();
    }

    public ShoppingItem deleteShoppingItem(long itemId) {
        ShoppingItem theItem = this.getShoppingItem(itemId);
        shoppingItemRepository.delete(theItem);
        return theItem;
    }
}
