package testsupport.spring.playground;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.google.common.base.MoreObjects;
import org.springframework.util.Assert;

@Entity
public class LineItem extends AbstractEntity {

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private BigDecimal price;
    private int amount;

    public LineItem(Product product) {
        this(product, 1);
    }

    public LineItem(Product product, int amount) {

        Assert.notNull(product, "The given Product must not be null!");
        Assert.isTrue(amount > 0, "The amount of Products to be bought must be greater than 0!");

        this.product = product;
        this.amount = amount;
        this.price = product.getPrice();
    }

    public LineItem() {}


    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal getUnitPrice() {
        return price;
    }

    public BigDecimal getTotal() {
        return price.multiply(BigDecimal.valueOf(amount));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(this.getClass().equals(obj.getClass()))) {
            return false;
        }
        LineItem that = (LineItem) obj;
        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getProduct(), that.getProduct()) &&
                Objects.equals(this.getAmount(), that.getAmount()) &&
                Objects.equals(this.getUnitPrice(), that.getUnitPrice()) &&
                Objects.equals(this.getTotal(), that.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.product, this.price, this.amount);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass())
                .add("product", product)
                .add("price", price)
                .add("amount", amount)
                .toString();
    }
}
