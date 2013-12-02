/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Samuel Cawley
 */

@ManagedBean
@Entity
@Table(name="Products")
@ViewScoped
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.byID", query = "SELECT p FROM Products p WHERE p.id = :number"),
    @NamedQuery(name = "Products.byName", query = "SELECT p FROM Products p WHERE p.productName LIKE :productname")})
public class Products implements Serializable {

    @Id
    @GeneratedValue
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
    @OrderBy("categoryName")
    private List<Categories> categories = new ArrayList();
    
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
    
    public List<Categories> getCategories() {
        return categories;
    }
    
    public void setCategories(List<Categories> categories) {
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
