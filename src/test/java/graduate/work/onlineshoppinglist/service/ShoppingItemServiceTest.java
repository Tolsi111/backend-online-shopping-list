package graduate.work.onlineshoppinglist.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import graduate.work.onlineshoppinglist.exceptions.error_handling.error.ErrorCodes;
import graduate.work.onlineshoppinglist.exceptions.error_handling.response.CustomErrorResponse;
import graduate.work.onlineshoppinglist.model.Item;
import graduate.work.onlineshoppinglist.model.Recipe;
import graduate.work.onlineshoppinglist.model.ShoppingItem;
import graduate.work.onlineshoppinglist.model.ShoppingList;
import graduate.work.onlineshoppinglist.model.dto.RecipeDTO;
import graduate.work.onlineshoppinglist.model.dto.ShoppingItemDTO;
import graduate.work.onlineshoppinglist.model.dto.ShoppingListDTO;
import graduate.work.onlineshoppinglist.repository.ShoppingItemRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ShoppingItemServiceTest {
    @Mock
    private ShoppingItemRepository shoppingItemRepository;
    @Mock
    private ItemService itemService;
    @Mock
    private ShoppingListService shoppingListService;
    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private ShoppingItemService shoppingItemService;

    @Test
    void saveShoppingItem_shoppingList_test() {
        ShoppingItemDTO shoppingItemDTO = new ShoppingItemDTO(1,0L,null,0L);

        when(itemService.getItemById(anyLong())).thenReturn(new Item());
        when(shoppingListService.getShoppingList(anyLong())).thenReturn(new ShoppingList());

        shoppingItemService.saveShoppingItem(shoppingItemDTO);

        verify(shoppingListService).getShoppingList(anyLong());
        verify(shoppingListService).saveShoppingList((ShoppingList) any());
        verify(shoppingItemRepository).save(any());
    }

    @Test
    void saveShoppingItem_recipe_test() {
        ShoppingItemDTO shoppingItemDTO = new ShoppingItemDTO(1,null,0L,0L);

        when(itemService.getItemById(anyLong())).thenReturn(new Item());
        when(recipeService.getRecipe(anyLong())).thenReturn(new Recipe());

        shoppingItemService.saveShoppingItem(shoppingItemDTO);

        verify(recipeService).getRecipe(anyLong());
        verify(shoppingItemRepository).save(any());
    }

    @Test
    void test_unsuccessful_saveShoppingItem() {
        ShoppingItemDTO shoppingItemDTO = new ShoppingItemDTO(1,null,null,0L);
        CustomErrorResponse err = assertThrows(CustomErrorResponse.class, () -> shoppingItemService.saveShoppingItem(shoppingItemDTO));
        assertEquals("400-6", err.getCode());
        assertEquals("Bad request - Neither recipe nor shopping list was provided for the shopping item", err.getDescription());
    }

    @Test
    void test_unsuccessful_saveShoppingItem2() {
        ShoppingItemDTO shoppingItemDTO = new ShoppingItemDTO(1,null,1L,0L);
        when(shoppingItemRepository.save(any(ShoppingItem.class))).thenThrow(new CustomErrorResponse(ErrorCodes.INTERNAL_SERVER_ERROR_WHILE_CREATING_RESOURCE));
        CustomErrorResponse err = assertThrows(CustomErrorResponse.class, () -> shoppingItemService.saveShoppingItem(shoppingItemDTO));
        assertEquals("500-2", err.getCode());
        assertEquals("Internal server error - There was an error while creating resource", err.getDescription());
    }

    @Test
    void test_unsuccesful_getShoppingItem() {
        when(shoppingItemRepository.findById(anyLong())).thenReturn(Optional.empty());
        CustomErrorResponse err = assertThrows(CustomErrorResponse.class, () -> shoppingItemService.getShoppingItem(1L));
        // Assert
        assertEquals("404-2", err.getCode());
        assertEquals("Bad request - Shopping item not found", err.getDescription());
    }

    @Test
    void test_updateShoppingItemAmount() {
        ShoppingList mShoppingList = new ShoppingList();
        mShoppingList.setPrice(100);
        Item item = new Item();
        item.setPricePerUnit(1);
        ShoppingItem mShoppingItem =  new ShoppingItem(1L, 2, null, null, null);
        mShoppingItem.setShoppingList(mShoppingList);
        mShoppingItem.setItem(item);

        when(shoppingItemRepository.findById(anyLong())).thenReturn(Optional.of(mShoppingItem));
        when(shoppingListService.saveShoppingList(any(ShoppingList.class))).thenReturn(mShoppingList);
        when(shoppingItemRepository.save(any(ShoppingItem.class))).thenReturn(mShoppingItem);

        ShoppingItem result = shoppingItemService.updateShoppingItemAmount(1L,1);
        assertNotNull(result);
        assertEquals(99, mShoppingList.getPrice());
    }

    @Test
    void test_deleteShoppingItem() {
        ShoppingList mShoppingList = new ShoppingList();
        mShoppingList.setPrice(100);
        Item item = new Item();
        item.setPricePerUnit(1);
        ShoppingItem mShoppingItem =  new ShoppingItem(1L, 2, null, null, null);
        mShoppingItem.setShoppingList(mShoppingList);
        mShoppingItem.setItem(item);

        when(shoppingItemRepository.findById(anyLong())).thenReturn(Optional.of(mShoppingItem));
        when(shoppingListService.saveShoppingList(any(ShoppingList.class))).thenReturn(mShoppingList);

        ShoppingItem result = shoppingItemService.deleteShoppingItem(1L);
        assertNotNull(result);
    }
}
