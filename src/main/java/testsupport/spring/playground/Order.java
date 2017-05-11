package testsupport.spring.playground;


import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import org.springframework.util.Assert;

@Entity
@Table(name = "Orders")
public class Order extends AbstractEntity {

    @ManyToOne(optional = false)
    private Customer customer;

    @ManyToOne
    private Address billingAddress;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Address shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private Set<LineItem> lineItems = new HashSet<LineItem>();


    public Order(Customer customer, Address shippingAddress) {
        this(customer, shippingAddress, null);
    }

    public Order(Customer customer, Address shippingAddress, Address billingAddress) {
        Assert.notNull(customer);
        Assert.notNull(shippingAddress);
        this.customer = customer;
        this.shippingAddress = shippingAddress.getCopy();
        this.billingAddress = billingAddress == null ? null : billingAddress.getCopy();
    }

    protected Order() {}

    public void add(LineItem lineItem) {
        this.lineItems.add(lineItem);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Address getBillingAddress() {
        return billingAddress != null ? billingAddress : shippingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public Set<LineItem> getLineItems() {
        return Collections.unmodifiableSet(lineItems);
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (LineItem item : lineItems) {
            total = total.add(item.getTotal());
        }
        return total;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(this.getClass().equals(obj.getClass()))) {
            return false;
        }
        Order that = (Order) obj;
        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getCustomer(), that.getCustomer()) &&
                Objects.equals(this.getBillingAddress(), that.getBillingAddress()) &&
                Objects.equals(this.getShippingAddress(), that.getShippingAddress()) &&
                Objects.equals(this.getLineItems(), that.getLineItems()) &&
                Objects.equals(this.getTotal(), that.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(),
                this.getCustomer(),
                this.getBillingAddress(),
                this.getShippingAddress(),
                this.getLineItems(),
                this.getTotal());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("customer", customer)
                .add("getBillingAddress", billingAddress)
                .add("shippingAddress", shippingAddress)
                .add("lineItems", lineItems)
                .toString();
    }
}
