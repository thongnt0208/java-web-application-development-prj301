/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thongnt.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import thongnt.utils.DBHelper;

/**
 *
 * @author trung
 */
public class OrdersDetailDAO implements Serializable {

    public boolean addToOrdersDetail(int id, String sku, int quantity, double price, double totalPrice) throws SQLException, NamingException {
        boolean result = false;

        Connection connection = null;
        PreparedStatement stm = null;

        try {
            //1. Make connection
            connection = DBHelper.makeConnection();
            //2. Write SQL String
            String sql = "insert into OrdersDetail\n"
                    + "	values (?, ?, ?, ?, ?)";
            //3. Create Statement
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setString(2, sku);
            stm.setInt(3, quantity);
            stm.setDouble(4, price);
            stm.setDouble(5, totalPrice);
            //4. Execute statement
            result = stm.execute();
            //5. Process Result
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
}
