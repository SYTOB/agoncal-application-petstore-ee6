package org.agoncal.application.petstore.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
@EqualsAndHashCode
public class CartItem {

    // ======================================
    // =             Attributes             =
    // ======================================

    @NotNull
    private Item item;
    @NotNull
    @Min(1)
    private Integer quantity;

    // ======================================
    // =            Constructors            =
    // ======================================

    public CartItem(Item item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    // ======================================
    // =              Public Methods        =
    // ======================================

    public Float getSubTotal() {
        return item.getUnitCost() * quantity;
    }

    // ======================================
    // =         Getters & setters          =
    // ======================================

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // ======================================
    // =   Methods toString   =
    // ======================================


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CartItem");
        sb.append("{item='").append(item).append('\'');
        sb.append(", quantity='").append(quantity).append('\'');
        sb.append('}');
        return sb.toString();
    }
}