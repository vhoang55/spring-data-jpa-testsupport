package testsupport.spring.playground;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = {OrderRepository.class, CustomerRepository.class, ProductRepository.class})
public class OrderRepositoryIntegrationTest {

    @Autowired
    OrderRepository repository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void createOrder() {

        Customer dave = customerRepository.findByEmailAddress(new EmailAddress("dave@dmband.com"));
        Product iPad = productRepository.findOne(1L);

        Order order = new Order(dave, dave.getAddresses().iterator().next());
        order.add(new LineItem(iPad));

        order = repository.save(order);
        assertThat(order.getId(), is(notNullValue()));
    }

    @Test
    public void readOrder() {
        Customer dave = customerRepository.findByEmailAddress(new EmailAddress("dave@dmband.com"));
        List<Order> orders = repository.findByCustomer(dave);
        assertThat(orders, is(Collections.emptyList()));
    }
}

