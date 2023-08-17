package graduate.work.onlineshoppinglist.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import graduate.work.onlineshoppinglist.model.Item;
import graduate.work.onlineshoppinglist.model.Recipe;
import graduate.work.onlineshoppinglist.model.ShoppingItem;
import graduate.work.onlineshoppinglist.model.ShoppingList;
import graduate.work.onlineshoppinglist.model.dto.RecipeDTO;
import graduate.work.onlineshoppinglist.model.dto.ShoppingItemDTO;
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
}
