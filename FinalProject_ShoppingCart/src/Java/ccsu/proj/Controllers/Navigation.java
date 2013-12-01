/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Controllers;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Samuel Cawley
 */
@ManagedBean
@SessionScoped
public class Navigation implements Serializable {
    String view = null;
    String category = null;
    public void setView (String view)
    {
        this.view = view;
    }
    
    public String getView()
    {
        return view;
    }
    
     public void setCategory (String category)
    {
        this.category = category;
    }
    
    public String getCategory()
    {
        return category;
    }
}
