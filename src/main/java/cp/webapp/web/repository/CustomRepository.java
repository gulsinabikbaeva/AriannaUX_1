package cp.webapp.web.repository;

import cp.webapp.web.model.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CustomRepository {

    EntityManager em;

    public class IdTitle {
        private int id;
        private String title;

        public IdTitle(int id, String title) {
            this.id = id;
            this.title = title;
        }

        int getId(){
            return id;
        }
        String getTitle(){
            return title;
        }
    }

    public CustomRepository(EntityManager entityManager) {
        this.em = entityManager;
    }

    public List<IdTitle> findItem(String pattern) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<IdTitle> cq = cb.createQuery(IdTitle.class);

        Root<Item> root = cq.from(Item.class);
        cq.multiselect(root.get("id"), root.get("title")).distinct(true);
        Predicate authorNamePredicate = cb.like(root.get("title"), pattern);
        cq.where(authorNamePredicate);

        TypedQuery<IdTitle> query = em.createQuery(cq);
        return query.getResultList();
    }
}