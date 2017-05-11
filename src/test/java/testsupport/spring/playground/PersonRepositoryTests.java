
package testsupport.spring.playground;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = PersonRepository.class)
public class PersonRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository personeRepository;

    @Test
    public void testFindByLastName() {
        Person person = new Person("first", "last");
        entityManager.persist(person);

        List<Person> findByLastName = personeRepository.findByLastName(person.getLastName());

        assertThat(findByLastName)
                .extracting(Person::getLastName)
                .containsOnly(person.getLastName());
    }
}