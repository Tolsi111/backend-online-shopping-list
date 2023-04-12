package graduate.work.onlineshoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import graduate.work.onlineshoppinglist.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findTop5ByNameContaining(String name);
}
