package cp.webapp.web.repository;

import cp.webapp.web.model.CustomOrder;
import cp.webapp.web.model.Role;
import cp.webapp.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomOrderRepository extends JpaRepository<CustomOrder, Integer> {

    CustomOrder findCustomOrderByIdAndCustomerUsername(int id, String customerUsername);

    List<CustomOrder> findCustomOrdersByCustomerUsername(String customerUsername);

}