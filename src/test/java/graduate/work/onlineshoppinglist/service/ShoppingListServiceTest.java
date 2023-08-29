package graduate.work.onlineshoppinglist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import graduate.work.onlineshoppinglist.exceptions.error_handling.error.ErrorCodes;
import graduate.work.onlineshoppinglist.exceptions.error_handling.response.CustomErrorResponse;
import graduate.work.onlineshoppinglist.model.ShoppingList;
import graduate.work.onlineshoppinglist.model.dto.ShoppingListDTO;
import graduate.work.onlineshoppinglist.repository.ShoppingListRepository;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class ShoppingListServiceTest {
    @Mock
    private ShoppingListRepository shoppingListRepository;

    @InjectMocks
    private ShoppingListService shoppingListService;

    @Test
    void test_unsuccessful_saveShoppingListDto() {
        // Prepare
        ShoppingListDTO dto = new ShoppingListDTO();
        when(shoppingListRepository.save(any())).thenThrow(new CustomErrorResponse(ErrorCodes.INTERNAL_SERVER_ERROR_WHILE_CREATING_RESOURCE));
        // Act
        CustomErrorResponse err = assertThrows(CustomErrorResponse.class, () -> shoppingListService.saveShoppingList(dto));
        // Assert
        assertEquals("500-2", err.getCode());
        assertEquals("Internal server error - There was an error while creating resource", err.getDescription());
    }

    @Test
    void test_saveShoppingList() {
        // Prepare
        ShoppingList mockShoppingList = new ShoppingList();
//        ShoppingListDTO dto = new ShoppingListDTO();
        when(shoppingListRepository.save(any())).thenReturn(mockShoppingList);
        // Act
        ShoppingList result = shoppingListService.saveShoppingList(mockShoppingList);
        // Assert
        assertNotNull(result);
        verify(shoppingListRepository, times(1)).save(Mockito.any(ShoppingList.class));
    }

    @Test
    void test_unsuccessful_saveShoppingList() {
        // Prepare
        ShoppingList mockShoppingList = new ShoppingList();
        when(shoppingListRepository.save(any())).thenThrow(new CustomErrorResponse(ErrorCodes.INTERNAL_SERVER_ERROR_WHILE_CREATING_RESOURCE));
        // Act
        CustomErrorResponse err = assertThrows(CustomErrorResponse.class, () -> shoppingListService.saveShoppingList(mockShoppingList));
        // Assert
        assertEquals("500-2", err.getCode());
        assertEquals("Internal server error - There was an error while creating resource", err.getDescription());
    }

    @Test
    void test_unsuccessful_getShoppingList() {
        // Prepare
        when(shoppingListRepository.findById(anyLong())).thenReturn(Optional.empty());
        // Act
        CustomErrorResponse err = assertThrows(CustomErrorResponse.class, () -> shoppingListService.getShoppingList(1L));
        // Assert
        assertEquals("404-3", err.getCode());
        assertEquals("Bad request - Shopping list not found", err.getDescription());

    }

    @Test
    void test_updateShoppingList() {
        // Prepare
        ShoppingList mockShoppingList = new ShoppingList();
        ShoppingListDTO dto = new ShoppingListDTO();
        when(shoppingListRepository.save(any())).thenReturn(mockShoppingList);
        when(shoppingListRepository.findById(anyLong())).thenReturn(Optional.of(mockShoppingList));
        // Act
        ShoppingList result = shoppingListService.updateShoppingListById(1L,dto);
        // Assert
        assertNotNull(result);
        verify(shoppingListRepository, times(1)).save(Mockito.any(ShoppingList.class));
    }

    @Test
    void test_getShoppingLists() {
        List<ShoppingList> result = shoppingListService.getShoppingLists();
        assertNotNull(result);
        verify(shoppingListRepository, times(1)).findAll();
    }

    @Test
    void test_deleteShoppingListById() {
        ShoppingList mockShoppingList = new ShoppingList();
        when(shoppingListRepository.findById(anyLong())).thenReturn(Optional.of(mockShoppingList));
        ShoppingList result = shoppingListService.deleteShoppingListById(1L);
        assertNotNull(result);

        verify(shoppingListRepository, times(1)).delete(any(ShoppingList.class));
    }

}
