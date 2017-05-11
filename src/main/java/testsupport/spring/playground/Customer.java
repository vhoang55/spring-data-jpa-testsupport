package testsupport.spring.playground;


import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.google.common.base.MoreObjects;
import org.springframework.util.Assert;


@Entity
public class Customer extends AbstractEntity {

    private String firstname, lastname;

    @Column(unique = true)
    private EmailAddress emailAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private Set<Address> addresses = new HashSet<Address>();


    public Customer(String firstname, String lastname) {
        Assert.hasText(firstname);
        Assert.hasText(lastname);
        this.firstname = firstname;
        this.lastname = lastname;
    }

    protected Customer() {}

    public void add(Address address) {
        Assert.notNull(address);
        this.addresses.add(address);
    }


    public String getFirstname() {
        return firstname;
    }


    public String getLastname() {
        return lastname;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Set<Address> getAddresses() {
        return Collections.unmodifiableSet(addresses);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(this.getClass().equals(obj.getClass()))) {
            return false;
        }
        Customer that = (Customer) obj;
        return  Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getFirstname(), that.getFirstname()) &&
                Objects.equals(this.getLastname(), that.getLastname()) &&
                Objects.equals(this.getEmailAddress(), that.getEmailAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.firstname, this.lastname, this.emailAddress);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("firstname", firstname)
                .add("lastname", lastname)
                .add("emailAddress", emailAddress)
                .toString();
    }
}
