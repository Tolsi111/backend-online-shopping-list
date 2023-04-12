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

import graduate.work.onlineshoppinglist.exceptions.error_handling.response.Response;
import graduate.work.onlineshoppinglist.model.ShoppingItem;
import graduate.work.onlineshoppinglist.model.dto.ShoppingItemDTO;
import graduate.work.onlineshoppinglist.model.responses.DataResponse;
import graduate.work.onlineshoppinglist.service.ShoppingItemService;

@RestController
@RequestMapping("/shopping-items")
public class ShoppingItemController {

    @Autowired
    private ShoppingItemService shoppingItemService;

    @PostMapping
    public Response<ShoppingItem> addShoppingItem(@RequestBody ShoppingItemDTO theItem) {
        return new Response<>(new DataResponse<>(shoppingItemService.saveShoppingItem(theItem)));
    }

    @GetMapping("/by-recipe/{recipeId}")
    public Response<ShoppingItem> getShoppingItemsByRecipeId(@PathVariable long recipeId) {
        return new Response<>(new DataResponse<>(shoppingItemService.getShoppingItemsByRecipeId(recipeId)));
    }

    @PutMapping("/{itemId}")
    public Response<ShoppingItem> updateShoppingItem(@PathVariable long itemId,
                                                     @RequestBody ShoppingItemDTO theItem) {
        return new Response<>(new DataResponse<>(shoppingItemService.updateShoppingItemAmount(itemId, theItem.getAmount())));
    }

    @DeleteMapping("/{itemId}")
    public Response<ShoppingItem> deleteShoppingItem(@PathVariable long itemId) {
        return new Response<>(new DataResponse<>(shoppingItemService.deleteShoppingItem(itemId)));
    }
}
