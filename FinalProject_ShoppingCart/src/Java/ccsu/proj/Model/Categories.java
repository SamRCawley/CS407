/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Samuel Cawley
 */
@ManagedBean
@Entity
@Table(name = "CATEGORIES")
@ViewScoped
@NamedQueries({
    @NamedQuery(name = "Categories.findAll", query = "SELECT c FROM Categories c"),
    @NamedQuery(name = "Categories.byID", query = "SELECT c FROM Categories c WHERE c.cid = :number"),
    @NamedQuery(name = "Categories.byName", query = "SELECT c FROM Categories c WHERE c.categoryName LIKE :categoryname")})
public class Categories implements Serializable {

    @JoinTable(name = "PRODUCTS_CATEGORIES", joinColumns = {
        @JoinColumn(name = "CID", referencedColumnName = "CID")}, inverseJoinColumns = {
        @JoinColumn(name = "PID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Products> productsList;
    @Id
    @Column(name = "CID")
    private int cid;
    ;
    
    @Column(name = "CATEGORYNAME")
    private String categoryName;
    @ManyToMany(mappedBy = "categories")
    private Set<Products> products = new HashSet();

    public Categories() {
    }

    public int getCID() {
        return cid;
    }

    public void setCID(int cid) {
        this.cid = cid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Products> getProducts() {
        return products;
    }

    public void setProducts(Set<Products> products) {
        this.products = products;
    }

    public List<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
    }
}
