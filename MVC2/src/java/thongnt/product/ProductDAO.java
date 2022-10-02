/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thongnt.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.naming.NamingException;
import thongnt.utils.DBHelper;

/**
 *
 * @author trung
 */
public class ProductDAO implements Serializable {

    public HashMap<String, ProductDTO> viewAllProduct()
            throws SQLException, NamingException {
        HashMap<String, ProductDTO> result = new HashMap<>();
        Connection connection = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            //1. Make connection
            connection = DBHelper.makeConnection();

            //2. Write sql Statement
            String sql = "select [sku], [name], [description], [price] \n"
                    + "from Product";

            //3. Create Statement
            stm = connection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                String sku = rs.getString("sku");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                ProductDTO dto = new ProductDTO(sku, name, description, price);
                result.put(sku, dto);
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

    public ProductDTO getProductBySku(String sku)
            throws SQLException, NamingException {
        ProductDTO result = null;
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Make connection
            connection = DBHelper.makeConnection();
            //2. Write SQL String
            String sql = "select [sku], [name], [description], [price]\n"
                    + "from Product\n"
                    + "where sku = ?";
            //3. Create Statement
            stm = connection.prepareStatement(sql);
            stm.setString(1, sku);
            //4. Execute sql
            rs = stm.executeQuery();
            //5. Process result
            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                ProductDTO tmp = new ProductDTO(sku, name, description, price);
                result = tmp;
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

    public boolean createProduct(ProductDTO dto)
            throws SQLException, NamingException {
        boolean result = false;
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            //1. Make connection
            connection = DBHelper.makeConnection();
            //2. Write SQL String
            String sql = "insert into Product\n"
                    + "values (?, ?, ?, ?)";
            //3. Create Statement
            stm = connection.prepareStatement(sql);
            stm.setString(1, dto.getSku());
            stm.setString(2, dto.getName());
            stm.setString(3, dto.getDescription());
            stm.setDouble(4, dto.getPrice());

            //4. Execute sql
            int tmp = stm.executeUpdate();

            //5. Process result
            if (tmp != 0) {
                result = true;
            }
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
