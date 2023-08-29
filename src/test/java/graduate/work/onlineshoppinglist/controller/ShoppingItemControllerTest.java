package graduate.work.onlineshoppinglist.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import graduate.work.onlineshoppinglist.model.ShoppingItem;
import graduate.work.onlineshoppinglist.model.dto.ShoppingItemDTO;
import graduate.work.onlineshoppinglist.service.ShoppingItemService;
import graduate.work.onlineshoppinglist.service.ShoppingListService;
import graduate.work.onlineshoppinglist.exceptions.error_handling.response.Response;


@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ShoppingItemControllerTest {

    @Mock
    private ShoppingItemService shoppingItemService;

    @InjectMocks
    private ShoppingItemController shoppingItemController;

    @Test
    void test_addShoppingItem() {
        ShoppingItem mockShoppingItem = new ShoppingItem();
        ShoppingItemDTO mockShoppingItemDTO = new ShoppingItemDTO();
        when(shoppingItemService.saveShoppingItem(any(ShoppingItemDTO.class))).thenReturn(mockShoppingItem);
        Response<ShoppingItem> response = shoppingItemController.addShoppingItem(mockShoppingItemDTO);
        verify(shoppingItemService, times(1)).saveShoppingItem(any(ShoppingItemDTO.class));
        assertNotNull(response);
    }

    @Test
    void test_getShoppingItemsByRecipeId() {
        List<ShoppingItem> mockShoppingItems = new ArrayList<>();
        when(shoppingItemService.getShoppingItemsByRecipeId(anyLong())).thenReturn(mockShoppingItems);
        Response<ShoppingItem> response = shoppingItemController.getShoppingItemsByRecipeId(1L);
        verify(shoppingItemService, times(1)).getShoppingItemsByRecipeId(anyLong());
        assertNotNull(response);
    }

    @Test
    void test_updateShoppingItem() {
        ShoppingItem mockShoppingItem = new ShoppingItem();
        ShoppingItemDTO mockShoppingItemDTO = new ShoppingItemDTO();
        when(shoppingItemService.updateShoppingItemAmount(anyLong(), anyInt())).thenReturn(mockShoppingItem);
        Response<ShoppingItem> response = shoppingItemController.updateShoppingItem(1L,mockShoppingItemDTO);
        verify(shoppingItemService, times(1)).updateShoppingItemAmount(anyLong(), anyInt());
        assertNotNull(response);
    }

    @Test
    void test_deleteShoppingItem() {
        ShoppingItem mockShoppingItem = new ShoppingItem();
        when(shoppingItemService.deleteShoppingItem(anyLong())).thenReturn(mockShoppingItem);
        Response<ShoppingItem> response = shoppingItemController.deleteShoppingItem(1L);
        verify(shoppingItemService, times(1)).deleteShoppingItem(anyLong());
        assertNotNull(response);
    }
}
