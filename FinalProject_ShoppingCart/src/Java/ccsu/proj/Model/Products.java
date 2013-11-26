/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Samuel Cawley
 */

@ManagedBean
@Entity
@Table(name="Products")
public class Products implements Serializable {

    @Id
    @Column(name="ID")
    private int id = 0;
    
    @Column(name="PRODUCTNAME")
    private String productName = "";
    
    @Column(name="PRICE")
    private float price = 0;
    
    @Column(name="DESCRIPTION")
    private String description = "";
    
    @ManyToMany
    @JoinTable(name="PRODUCTS_CATEGORIES", joinColumns=@JoinColumn(name="PID", referencedColumnName="ID"), 
            inverseJoinColumns=@JoinColumn(name="cid", referencedColumnName="CID"))
    private Set<Categories> categories = new HashSet();
    
    @OneToMany(mappedBy="product")
    private Set<OrdersProducts> ordersProducts = new HashSet();
    
    @Transient
    private int quantity = 1;
    
    public Products(){}
    
    public int getID() {
        return id;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public float getPrice() {
        return price;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Set<Categories> getCategories() {
        return categories;
    }
    
    public void setCategories(Set<Categories> categories) {
        this.categories = categories;
    }
    
    public Set<OrdersProducts> getOrders() {
        return ordersProducts;
    }
    
    public void setOrders(Set<OrdersProducts> ordersProducts) {
        this.ordersProducts = ordersProducts;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
