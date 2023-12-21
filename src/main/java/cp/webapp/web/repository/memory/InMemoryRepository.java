package cp.webapp.web.repository.memory;

import cp.webapp.web.model.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
@Deprecated
public class InMemoryRepository {
    private List<Item> items = new ArrayList<>();
    private Random randomGenerator = new Random();

    public List<Item> findAll() {
        return items;
    }

    public Optional<Item> getItem(int id) {
        return items.stream().filter(bp->bp.getId() == id).findFirst();
    }

    public Item create(Item item){
        item.setId(randomGenerator.nextInt(100000));
        items.add(item);
        return item;
    }

    public Item update(int id,  Item item){
        Optional<Item> dbItem = getItem(item.getId());
        if (!dbItem.isPresent()) return null;
        items.remove(dbItem.get());
        item.setId(id);
        items.add(item);
        return item;
    }

    public Boolean delete(int id) {
        Optional<Item> post = getItem(id);
        if (!post.isPresent()) return null;
        return items.remove(post.get());
    }

}
