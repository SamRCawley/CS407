/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Model;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 *
 * @author Sam Cawley
 */
@Entity
@ManagedBean
@Table(name = "ORDERS_PRODUCTS")
@IdClass(OrdersProductsID.class)
public class OrdersProducts implements Serializable {
    
    @Id
    @ManyToOne
    @JoinColumn(name="ORDERNUM")
    private Orders orderNum;
    
    @Id
    @ManyToOne
    @JoinColumn(name="PID")
    private Products product;
       
    @Column(name = "QUANTITY")
    private int quantity;

    public OrdersProducts() {
    }

    public Orders getOrdernum()
    {
        return orderNum;
    }
    
    public void setOrdernum(Orders orderNum)
    {
        this.orderNum = orderNum;
    }
    
    public Products getproduct()
    {
        return product;
    }
    
    public void setProduct(Products product)
    {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
