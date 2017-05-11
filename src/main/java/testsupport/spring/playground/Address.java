package testsupport.spring.playground;

import javax.persistence.Entity;

import com.google.common.base.MoreObjects;
import org.springframework.util.Assert;

import java.util.Objects;


@Entity
public class Address extends AbstractEntity {

    private String street, city, country;

    public Address(String street, String city, String country) {

        Assert.hasText(street, "Street must not be null or empty!");
        Assert.hasText(city, "City must not be null or empty!");
        Assert.hasText(country, "Country must not be null or empty!");

        this.street = street;
        this.city = city;
        this.country = country;
    }

    protected Address() {
    }

    public Address getCopy() {
        return new Address(this.street, this.city, this.country);
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(this.getClass().equals(obj.getClass()))) {
            return false;
        }
        Address that = (Address) obj;
        return  Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getStreet(), that.getStreet()) &&
                Objects.equals(this.getCity(), that.getCity()) &&
                Objects.equals(this.getCountry(), that.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.street, this.city, this.country);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("street", street)
                .add("city", city)
                .add("country", country)
                .toString();
    }

}
