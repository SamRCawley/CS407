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
import javax.persistence.Table;

/**
 *
 * @author Samuel Cawley
 */
@ManagedBean
@Entity
@Table(name="Accounts")
public class Account implements Serializable {
    @Id
    @Column(name="ID")
    private int id = 0;
    
    @Column(name="USERNAME")
    private String username = null;
    
    @Column(name="EMAIL")
    private String email = null;
    
    @Column(name="PW_HASH")
    private String pw_hash = null;
    
    @Column(name="PERMISSION_LEVEL")
    private short permission_level = 0;
    
    public Account() {
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPW_Hash() {
        return pw_hash;
    }
    
    public void setPW_Hash(String pw_hash) {
        this.pw_hash = pw_hash;
    }
    
    public int getPermission_Level() {
        return permission_level;
    }
    
    public void setPermission_Level(short permission_level) {
        this.permission_level = permission_level;
    } 
}
