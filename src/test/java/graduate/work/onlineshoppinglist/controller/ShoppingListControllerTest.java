package graduate.work.onlineshoppinglist.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import graduate.work.onlineshoppinglist.exceptions.error_handling.response.Response;
import graduate.work.onlineshoppinglist.model.ShoppingList;
import graduate.work.onlineshoppinglist.model.dto.ShoppingListDTO;
import graduate.work.onlineshoppinglist.service.ShoppingListService;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class ShoppingListControllerTest {

    @Mock
    private ShoppingListService shoppingListService;

    @InjectMocks
    private ShoppingListController shoppingListController;

    @Test
    void test_addShoppingList() {
        // Prepare
        ShoppingListDTO mockShoppingListDto = new ShoppingListDTO();
        ShoppingList mockShoppingList = new ShoppingList();
        when(shoppingListService.saveShoppingList((ShoppingListDTO) any())).thenReturn(mockShoppingList);
        // Act
        Response<ShoppingList> response = shoppingListController.addShoppingList(mockShoppingListDto);
        // Assert
        verify(shoppingListService, times(1)).saveShoppingList(Mockito.any(ShoppingListDTO.class));
        assertNotNull(response);
    }

    @Test
    void test_getShoppingLists() {
        // Prepare
//        ShoppingListDTO mockShoppingListDto = new ShoppingListDTO();
        List<ShoppingList> mockShoppingLists = new ArrayList<>();
        when(shoppingListService.getShoppingLists()).thenReturn(mockShoppingLists);
        // Act
        Response<ShoppingList> response = shoppingListController.getShoppingLists();
        // Assert
        verify(shoppingListService, times(1)).getShoppingLists();
        assertNotNull(response);
    }

    @Test
    void test_getShoppingList() {
        // Prepare
        //        ShoppingListDTO mockShoppingListDto = new ShoppingListDTO();
        ShoppingList mockShoppingList = new ShoppingList();
        when(shoppingListService.getShoppingList(anyLong())).thenReturn(mockShoppingList);
        // Act
        Response<ShoppingList> response = shoppingListController.getShoppingList(1L);
        // Assert
        verify(shoppingListService, times(1)).getShoppingList(anyLong());
        assertNotNull(response);
    }

    @Test
    void test_updateShoppingList() {
        // Prepare
        ShoppingListDTO mockShoppingListDto = new ShoppingListDTO();
        ShoppingList mockShoppingList = new ShoppingList();
        when(shoppingListService.updateShoppingListById(anyLong(),any(ShoppingListDTO.class))).thenReturn(mockShoppingList);
        // Act
        Response<ShoppingList> response = shoppingListController.updateShoppingList(1L, mockShoppingListDto);
        // Assert
        verify(shoppingListService, times(1)).updateShoppingListById(anyLong(), any(ShoppingListDTO.class));
        assertNotNull(response);
    }

    @Test
    void test_deleteShoppingList() {
        // Prepare
        //        ShoppingListDTO mockShoppingListDto = new ShoppingListDTO();
        ShoppingList mockShoppingList = new ShoppingList();
        when(shoppingListService.deleteShoppingListById(anyLong())).thenReturn(mockShoppingList);
        // Act
        Response<ShoppingList> response = shoppingListController.deleteShoppingList(1L);
        // Assert
        verify(shoppingListService, times(1)).deleteShoppingListById(anyLong());
        assertNotNull(response);
    }
}
