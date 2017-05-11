package testsupport.spring.playground;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = ProductRepository.class)
public class ProductRepositoryIntegrationTest {

    @Autowired
    ProductRepository repository;

    @Test
    public void findsProductsByAttributes() {
        List<Product> products = repository.findByAttributeAndValue("connector", "plug");

        assertThat(products)
                .extracting(Product::getAttributes)
                .containsOnlyOnce(ImmutableMap.of("connector", "plug"));
    }

    @Test
    public void lookupProductsByDescription() {
        Pageable pageable = new PageRequest(0, 1, Sort.Direction.DESC, "name");
        Page<Product> page = repository.findByDescriptionContaining("Apple", pageable);
        assertThat(page.getTotalElements(), is(2L));
        assertThat(page.isFirst(), is(true));
        assertThat(page.isLast(), is(false));
        assertThat(page.hasNext(), is(true));
    }
}
