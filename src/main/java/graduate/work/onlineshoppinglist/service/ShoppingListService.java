package graduate.work.onlineshoppinglist.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import graduate.work.onlineshoppinglist.exceptions.error_handling.error.ErrorCodes;
import graduate.work.onlineshoppinglist.exceptions.error_handling.response.CustomErrorResponse;
import graduate.work.onlineshoppinglist.model.ShoppingList;
import graduate.work.onlineshoppinglist.model.dto.ShoppingListDTO;
import graduate.work.onlineshoppinglist.repository.ShoppingListRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShoppingListService {

    private ShoppingListRepository shoppingListRepository;

    public ShoppingList saveShoppingList(ShoppingListDTO theList) {
        ShoppingList newList = new ShoppingList(0, theList.getTitle(), theList.getDescription(), 0, null);
        try {
            return shoppingListRepository.save(newList);
        } catch (Exception e) {
            throw new CustomErrorResponse(ErrorCodes.INTERNAL_SERVER_ERROR_WHILE_CREATING_RESOURCE);
        }
    }

    public List<ShoppingList> getShoppingLists() {
        return shoppingListRepository.findAll();
    }

    public ShoppingList getShoppingList(long listId) {
        Optional<ShoppingList> theList = shoppingListRepository.findById(listId);
        if (theList.isEmpty()) {
            throw new CustomErrorResponse(ErrorCodes.NOT_FOUND_SHOPPING_LIST);
        }
        return theList.get();
    }

    public ShoppingList updateShoppingListById(long listId,
                                               ShoppingListDTO newList) {
        ShoppingList oldList = this.getShoppingList(listId);
        oldList.setTitle(newList.getTitle());
        oldList.setDescription(newList.getDescription());
        return shoppingListRepository.save(oldList);
    }

    public ShoppingList deleteShoppingListById(long listId) {
        ShoppingList theList = this.getShoppingList(listId);
        shoppingListRepository.delete(theList);
        return theList;
    }
}
