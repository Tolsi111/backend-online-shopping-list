package graduate.work.onlineshoppinglist.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import graduate.work.onlineshoppinglist.exceptions.error_handling.error.ErrorCodes;
import graduate.work.onlineshoppinglist.exceptions.error_handling.response.CustomErrorResponse;
import graduate.work.onlineshoppinglist.model.ShoppingItem;
import graduate.work.onlineshoppinglist.model.dto.ShoppingItemDTO;
import graduate.work.onlineshoppinglist.repository.ShoppingItemRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShoppingItemService {

    private ShoppingItemRepository shoppingItemRepository;

//    public ShoppingItem saveShoppingItem(ShoppingItemDTO theItem) {
//        ShoppingItem newItem = new ShoppingItem(0, theItem.getName(), theItem.getCategory(), theItem.getPrice(), theItem.getAmount(), null);
//        try {
//            return shoppingItemRepository.save(newItem);
//        } catch (Exception e) {
//            throw new CustomErrorResponse(ErrorCodes.INTERNAL_SERVER_ERROR_WHILE_CREATING_RESOURCE);
//        }
//    }

    public List<ShoppingItem> getShoppingItems() {
        return shoppingItemRepository.findAll();
    }

    public ShoppingItem getShoppingItem(long itemId) {
        Optional<ShoppingItem> theItem = shoppingItemRepository.findById(itemId);
        if (theItem.isEmpty()) {
            throw new CustomErrorResponse(ErrorCodes.NOT_FOUND_SHOPPING_ITEM);
        }
        return theItem.get();
    }

//    public ShoppingItem updateShoppingItem(long itemId,
//                                           ShoppingItemDTO newItem) {
//        ShoppingItem oldItem = this.getShoppingItem(itemId);
//        oldItem.setName(newItem.getName());
//        oldItem.setCategory(newItem.getCategory());
//        oldItem.setPrice(newItem.getPrice());
//        oldItem.setAmount(newItem.getAmount());
//        return shoppingItemRepository.save(oldItem);
//    }

    public ShoppingItem deleteShoppingItem(long itemId) {
        ShoppingItem theItem = this.getShoppingItem(itemId);
        shoppingItemRepository.delete(theItem);
        return theItem;
    }
}
