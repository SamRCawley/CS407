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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Samuel Cawley
 */

@ManagedBean
@Entity
@Table(name="CATEGORIES")
public class Categories implements Serializable {

    @Id
    @Column(name="CID")
    private int cid = 0;
    
    @Column(name="CATEGORYNAME")
    private String categoryName = null;
    
    @ManyToMany(mappedBy="categories")
    private Set<Products> products = new HashSet();
    
    public Categories(){}
    
    public int getCID()
    {
        return cid;
    }
    
    public void setCID(int cid)
    {
        this.cid = cid;
    }
    
    public String getCategoryName()
    {
        return categoryName;
    }
    
    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

     public Set<Products> getProducts()
    {
        return products;
    }
    
    public void setProducts(Set<Products> products)
    {
        this.products = products;
    }
    
}
