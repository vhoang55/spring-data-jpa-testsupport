package testsupport.spring.playground;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);
}
