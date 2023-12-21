package cp.webapp.web.repository;

import cp.webapp.web.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, String> {

    List<SubCategory> findAllByParentCategoryName(String categoryName);

}