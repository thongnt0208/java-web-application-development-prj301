/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thongnt.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import thongnt.utils.DBHelper;

/**
 *
 * @author trung
 */
public class OrdersDAO implements Serializable {

    /**
     * Add an object to Orders table
     *
     * @param totalQuantity - total of quantity 
     * @return the row count for SQL Data Manipulation
     * Language (DML) statements or 0 for SQL statements that return nothing
     * @throws java.sql.SQLException
     * @throws javax.naming.NamingException
     */
    public int addToOrders(int totalQuantity) throws SQLException, NamingException {
        int result = -1;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String date = formatter.format(today);

        Connection connection = null;
        PreparedStatement stm = null;

        try {
            //1. Make connection
            connection = DBHelper.makeConnection();
            //2. Write SQL String
            String sqlInsert = "insert into Orders\n"
                    + "values(?, ?)";
            //3. Create Statement
            stm = connection.prepareStatement(sqlInsert);
            stm.setString(1, date);
            stm.setInt(2, totalQuantity);
            //4. Execute statement
            result = stm.executeUpdate();
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

    /**
     * Get the latest ID value
     * 
     * @return id of the latest object which is Primary key of Orders table. Return -1
     * if the operation has been fail.
     * @throws SQLException
     * @throws NamingException 
     */
    public int getLatestId() throws SQLException, NamingException {
        int result = -1;
        Connection connection = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            //1. Make connection
            connection = DBHelper.makeConnection();
            //2. Write SQL String
            String sqlSelectId = "select top 1 id \n"
                    + "from Orders \n"
                    + "order by id desc";
            //3. Create Statement
            stm = connection.createStatement();
            //4. Execute statement
            rs = stm.executeQuery(sqlSelectId);
            //5. Process Result
            if (rs.next()) {
                result = rs.getInt("id");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
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
