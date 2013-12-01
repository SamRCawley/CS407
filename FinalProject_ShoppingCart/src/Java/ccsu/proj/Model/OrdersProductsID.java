/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Model;

import java.io.Serializable;


/**
 *
 * @author Samuel Cawley
 */


public class OrdersProductsID implements Serializable{
    private int orderNum;
    private int product;
    
    public OrdersProductsID() {}
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof OrdersProductsID){
            OrdersProductsID OPID = (OrdersProductsID) obj;
 
            if(!(OPID.getOrderNum()== orderNum)){
                return false;
            }
 
            if(!(OPID.getProduct() == product)){
                return false;
            }
 
            return true;
        }
 
        return false;
    }
    
    @Override
   public int hashCode()
   {
      String hashString = orderNum+ "" + product;
      return (int)hashString.hashCode();
   }
    
    public int getOrderNum()
    {
        return orderNum;
    }
    
    public int getProduct()
    {
        return product;
    }
    
    public void setOrderNum(int orderNum)
    {
        this.orderNum = orderNum;
    }
    
    public void setProduct(int product)
    {
        this.product = product;
    }
}
