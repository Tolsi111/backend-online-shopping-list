package graduate.work.onlineshoppinglist.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
