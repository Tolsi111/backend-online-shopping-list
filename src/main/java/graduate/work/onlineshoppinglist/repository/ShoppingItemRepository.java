package graduate.work.onlineshoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import graduate.work.onlineshoppinglist.model.ShoppingItem;

public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> {
}
