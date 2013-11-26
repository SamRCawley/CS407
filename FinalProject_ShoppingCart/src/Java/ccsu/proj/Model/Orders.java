/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author Sam Cawley
 */
@Entity
@ManagedBean
@Table(name = "ORDERS")
@SessionScoped
public class Orders implements Serializable {
    @Id
    @Column(name = "ORDERNUM")
    @GeneratedValue
    private Integer ordernum;
    
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @ManyToMany
    @JoinTable(name="ORDERS_PRODUCTS", joinColumns=@JoinColumn(name="ORDERNUM", referencedColumnName="ORDERNUM"), 
            inverseJoinColumns=@JoinColumn(name="PID", referencedColumnName="ID"))
    private Set<Products> products = new HashSet();

    public Orders() {
    }

    public Orders(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Orders(Integer ordernum, Date date) {
        this.ordernum = ordernum;
        this.date = date;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public Set<Products> getProducts() {
        return products;
    }
    
    public void setProducts(Set<Products> products) {
        this.products = products;
    }
    
}
