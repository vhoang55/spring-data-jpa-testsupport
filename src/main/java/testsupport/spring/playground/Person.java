// tag::sample[]
package testsupport.spring.playground;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    protected Person() {}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Person[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(this.getClass().equals(obj.getClass()))) {
            return false;
        }
        Person that = (Person) obj;
        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getFirstName(), that.getFirstName()) &&
                Objects.equals(this.getLastName(), that.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, lastName);
    }



}

