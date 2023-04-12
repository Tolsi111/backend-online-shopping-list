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
import graduate.work.onlineshoppinglist.model.Item;
import graduate.work.onlineshoppinglist.model.ShoppingItem;
import graduate.work.onlineshoppinglist.model.dto.ItemDTO;
import graduate.work.onlineshoppinglist.model.responses.DataResponse;
import graduate.work.onlineshoppinglist.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

        @PostMapping
        public Response<Item> addItem(@RequestBody ItemDTO theItem) {
            return new Response<>(new DataResponse<>(itemService.saveItem(theItem)));
        }

    @GetMapping
    public Response<Item> getItems() {
        return new Response<>(new DataResponse<>((itemService.getItems())));
    }

    @GetMapping("/{itemId}")
    public Response<Item> getItem(@PathVariable Long itemId) {
        return new Response<>(new DataResponse<>(itemService.getItemById(itemId)));
    }

        @PutMapping("/{itemId}")
        public Response<Item> updateItem(@PathVariable long itemId,
                                                         @RequestBody ItemDTO newItem) {
            return new Response<>(new DataResponse<>(itemService.updateItemById(itemId, newItem)));
        }

    @DeleteMapping("/{itemId}")
    public Response<Item> deleteItem(@PathVariable long itemId) {
        return new Response<>(new DataResponse<>(itemService.deleteItemById(itemId)));
    }
}
