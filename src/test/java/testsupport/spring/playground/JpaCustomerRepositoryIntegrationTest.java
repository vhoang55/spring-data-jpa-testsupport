package testsupport.spring.playground;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = CustomerRepository.class)
public class JpaCustomerRepositoryIntegrationTest {

    @Autowired
    CustomerRepository repository;

    @Test
    public void insertsNewCustomerCorrectly() {

        Customer customer = new Customer("John", "Smith");
        customer = repository.save(customer);

        assertNotNull(customer.getId());
    }

    @Test
    public void updatesCustomerCorrectly() {

        Customer dave = repository.findByEmailAddress(new EmailAddress("dave@dmband.com"));
        assertNotNull(dave);

        dave.setLastname("Miller");
        dave = repository.save(dave);

        Customer reference = repository.findByEmailAddress(dave.getEmailAddress());
        assertEquals(reference.getLastname(), dave.getLastname());
    }
}
