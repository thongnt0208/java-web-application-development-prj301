/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thongnt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author trung
 */
public class ProductCartObject implements Serializable {

    private Map<String, Integer> items;

    //khong ton tai setItems vi chir bo tung mons --> chi co lay ra
    public Map<String, Integer> getItems() {
        return items;
    }

    public void addProductToCart(String sku) {
        //1. Checking items existed?
        if (this.items == null) {
            this.items = new HashMap<>();
        }//items existed
        int quantity = 1;
        //2. check existed book        
        if (this.items.containsKey(sku)) {
            quantity = this.items.get(sku) + 1;
        }

        //3. update items
        this.items.put(sku, quantity);
    }

    public void removeProductFromCart(String sku) {
        //1. Checking items existed?
        if (this.items == null) {
            return;
        }//items existed

        //2. check existed book
        if (this.items.containsKey(sku)) {
            this.items.remove(sku);
            //vi map sometime tu gan size = 0 nhung map van khong null
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
}
