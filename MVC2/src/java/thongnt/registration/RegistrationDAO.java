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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import thongnt.utils.DBHelper;

/**
 *
 * @author trung
 */
public class RegistrationDAO implements Serializable {

    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;

        try {
            //1. Make Connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL String
                String sql = "Select username, lastname, isAdmin  "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?";
                //3. Create Statement Object
                stm = connection.prepareStatement(sql);
                stm.setString(1, username); //thay bien username vao ? dau tien
                stm.setString(2, password); //thay bien password vao ? thu hai
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    result = new RegistrationDTO(username, null, fullname, role);
                }
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

    private List<RegistrationDTO> accounts; //phai xu li moi co gia tri vao account

    public List<RegistrationDTO> getAccounts() { //getAcc + Crt Space
        return accounts;
    }

    public void searchLastName(String searchValue)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Make Connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL String
                String sql = "select username, password, lastname, isAdmin "
                        + "from Registration "
                        + "where lastname like ?";
                //3. Create Statement Object
                stm = connection.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(
                            username, password, fullName, role);

                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }//end account is initialize
                    //account has existed
                    this.accounts.add(dto);
                }//end traverse ResultSet
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
    }//end searchLastName function

    public boolean deleteAccount(String username)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Make Connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL String
                String sql = "Delete from Registration "
                        + "Where username = ? ";

                //3. Create Statement Object
                stm = connection.prepareStatement(sql);
                stm.setString(1, username); //thay bien username vao ?

                //4. Execute Query 
                int effectedRows = stm.executeUpdate();

                //5. Process result
                if (effectedRows > 0) {
                    result = true;
                }
            }//end check connection != null
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

    public boolean updateAccount(String username, String password, boolean isAdmin)
            throws SQLException, NamingException {
        boolean result = false;

        Connection connection = null;
        PreparedStatement stm = null;

        try {
            //Make Connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //Write sql String
                String sql = "update Registration\n"
                        + "set password = ?, isAdmin = ?\n"
                        + "where username = ?";

                //Create Statement
                stm = connection.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);

                //Execute stm
                int effectedRows = stm.executeUpdate();

                //Process result
                if (effectedRows > 0) {
                    result = true;
                }
            }//end check connection != null
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
    
    public boolean createAccount(RegistrationDTO dto) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Make Connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                //2. Create SQL String
                String sql = "insert into Registration("
                        + "username, password, lastname, isAdmin)"
                        + "Values(?,?,?,?)";

                //3. Create Statement Object
                stm = connection.prepareStatement(sql);
                stm.setString(1, dto.getUsername()); //thay bien username vao ?
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());
                //4. Execute Query 
                int effectedRows = stm.executeUpdate();

                //5. Process result
                if (effectedRows > 0) {
                    result = true;
                }
            }//end check connection != null
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
