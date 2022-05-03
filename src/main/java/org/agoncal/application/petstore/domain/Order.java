package org.agoncal.application.petstore.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@Entity
@Table(name = "t_order")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = Order.FIND_ALL, query = "SELECT o FROM Order o")
})

@EqualsAndHashCode
public class Order {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;
    @Column(name = "order_date", updatable = false)
    @Temporal(TemporalType.DATE)
    @Getter private Date orderDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_fk", nullable = false)
    @Getter @Setter private Customer customer;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "t_order_order_line",
            joinColumns = {@JoinColumn(name = "order_fk")},
            inverseJoinColumns = {@JoinColumn(name = "order_line_fk")})
    @Getter @Setter private List<OrderLine> orderLines;
    @Embedded
    @Getter @Setter private Address deliveryAddress;
    @Embedded
    @Getter @Setter private CreditCard creditCard = new CreditCard();

    // ======================================
    // =             Constants              =
    // ======================================

    public static final String FIND_ALL = "Order.findAll";

    // ======================================
    // =            Constructors            =
    // ======================================

    public Order() {
    }

    public Order(Customer customer, CreditCard creditCard, Address deliveryAddress) {
        this.customer = customer;
        this.creditCard = creditCard;
        this.deliveryAddress = deliveryAddress;
    }

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @PrePersist
    private void setDefaultData() {
        orderDate = new Date();
    }

    // ======================================
    // =              Public Methods        =
    // ======================================

    public Float getTotal() {
        if (orderLines == null || orderLines.isEmpty())
            return 0f;

        Float total = 0f;

        // Sum up the quantities
        for (OrderLine orderLine : orderLines) {
            total += (orderLine.getSubTotal());
        }

        return total;
    }


    // ======================================
    // =   Methods toString   =
    // ======================================


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Order");
        sb.append("{id=").append(id);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", customer=").append(customer);
        sb.append(", orderLines=").append(orderLines);
        sb.append(", deliveryAddress=").append(deliveryAddress);
        sb.append(", creditCard=").append(creditCard);
        sb.append('}');
        return sb.toString();
    }
}
