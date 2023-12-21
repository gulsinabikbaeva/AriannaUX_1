package cp.webapp.web.repository;

import cp.webapp.web.model.Item;
import cp.webapp.web.model.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findTop20ByCategoryNameIgnoreCaseAndType(String category, ItemType type);
    List<Item> findTop20ByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCategoryNameContainingIgnoreCase(String title, String description, String category);
//    List<Item> findAllByUserFavoritesOrderByDateDesc(String username);
}