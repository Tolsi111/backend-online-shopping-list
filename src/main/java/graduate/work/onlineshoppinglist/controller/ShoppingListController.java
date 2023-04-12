package graduate.work.onlineshoppinglist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graduate.work.onlineshoppinglist.exceptions.error_handling.response.Response;
import graduate.work.onlineshoppinglist.model.ShoppingList;
import graduate.work.onlineshoppinglist.model.dto.ShoppingListDTO;
import graduate.work.onlineshoppinglist.model.responses.DataResponse;
import graduate.work.onlineshoppinglist.service.ShoppingListService;

@RestController
@RequestMapping("/shopping-lists")
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;

    @PostMapping
    public Response<ShoppingList> addShoppingList(@RequestBody ShoppingListDTO theList) {
        return new Response<>(new DataResponse<>(shoppingListService.saveShoppingList(theList)));
    }

    @GetMapping
    public Response<ShoppingList> getShoppingLists() {
        return new Response<>(new DataResponse<>((shoppingListService.getShoppingLists())));
    }

    @GetMapping("/{listId}")
    public Response<ShoppingList> getShoppingList(@PathVariable long listId) {
        return new Response<>(new DataResponse<>(shoppingListService.getShoppingList(listId)));
    }
    //    @PutMapping("/{itemId}")
    //    public Response<ShoppingItem> updateShoppingItem(@PathVariable long itemId,
    //                                                     @RequestBody ShoppingItemDTO newItem) {
    //        return new Response<>(new DataResponse<>(shoppingListService.updateShoppingItem(itemId, newItem)));
    //    }

    @DeleteMapping("/{listId}")
    public Response<ShoppingList> deleteShoppingList(@PathVariable long listId) {
        return new Response<>(new DataResponse<>(shoppingListService.deleteShoppingList(listId)));
    }
}
