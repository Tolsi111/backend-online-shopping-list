package graduate.work.onlineshoppinglist.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import graduate.work.onlineshoppinglist.exceptions.error_handling.error.ErrorCodes;
import graduate.work.onlineshoppinglist.exceptions.error_handling.response.CustomErrorResponse;
import graduate.work.onlineshoppinglist.model.Item;
import graduate.work.onlineshoppinglist.model.dto.ItemDTO;
import graduate.work.onlineshoppinglist.repository.ItemRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ItemService {

    private ItemRepository itemRepository;

    public Item saveItem(ItemDTO itemDTO) {
        Item newItem = new Item(0L, itemDTO.getName(), itemDTO.getCategory(), itemDTO.getPricePerUnit());
        try {
            return itemRepository.save(newItem);
        } catch (Exception e) {
            throw new CustomErrorResponse(ErrorCodes.INTERNAL_SERVER_ERROR_WHILE_CREATING_RESOURCE);
        }
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        Optional<Item> theItem = itemRepository.findById(id);
        if (theItem.isEmpty()) {
            throw new CustomErrorResponse(ErrorCodes.NOT_FOUND_ITEM);
        }
        return theItem.get();
    }

    public Item updateItemById(Long id,
                               ItemDTO newItem) {
        Item oldItem = this.getItemById(id);
        oldItem.setName(newItem.getName());
        oldItem.setCategory(newItem.getCategory());
        oldItem.setPricePerUnit(newItem.getPricePerUnit());
        return itemRepository.save(oldItem);
    }

    public Item deleteItemById(Long id) {
        Item theItem = this.getItemById(id);
        itemRepository.delete(theItem);
        return theItem;
    }

    public List<Item> findByName(String name) {
        return this.itemRepository.findTop5ByNameContaining(name);
    }
}
