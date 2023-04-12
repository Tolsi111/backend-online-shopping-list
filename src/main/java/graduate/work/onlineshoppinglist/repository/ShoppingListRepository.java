package graduate.work.onlineshoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import graduate.work.onlineshoppinglist.model.ShoppingList;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
}
