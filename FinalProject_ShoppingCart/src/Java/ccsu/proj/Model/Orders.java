/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@NamedQueries({
    @NamedQuery(name = "Orders.sortDateAsc", query = "SELECT o FROM Orders o ORDER BY o.date ASC"),
    @NamedQuery(name = "Orders.sortDateDesc", query = "SELECT o FROM Orders o ORDER BY o.date DESC")})
public class Orders implements Serializable {
    @Id
    @Column(name = "ORDERNUM")
    @GeneratedValue
    private Integer ordernum;
    
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @ManyToOne
    @JoinColumn(name = "ID", insertable = false, updatable = false)
    private Account account;
    
    @OneToMany(mappedBy="orderNum")
    private List<OrdersProducts> ordersProducts;

    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account){
        this.account = account;
    }
    public Orders(){
        
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
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public List<OrdersProducts> getOrdersProducts() {
        return ordersProducts;
    }
    
    public void setProducts(List<OrdersProducts> ordersProducts) {
        this.ordersProducts = ordersProducts;
    }
    
}
