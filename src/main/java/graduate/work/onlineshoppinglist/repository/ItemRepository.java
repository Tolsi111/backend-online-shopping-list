package graduate.work.onlineshoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import graduate.work.onlineshoppinglist.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
