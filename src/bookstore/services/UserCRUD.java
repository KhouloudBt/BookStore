/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.services;

import bookstore.MyConnection;
import bookstore.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emna
 */
public class UserCRUD {
    Connection cnx;
    public UserCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }
    //list all users
    public List<User> readUsers(){
        List<User> myList = new ArrayList<User>();
        try {
            String request = "select * from user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
            User u = new User();
            u.setId(rs.getInt(1));
            u.setFirstName(rs.getString(2));
            u.setLastName(rs.getString(3));
            u.setUsername(rs.getString("username"));
            u.setPhone(rs.getString(4));
            myList.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    //update user
    public void modifyUser(User u,int id){
        try {
            String request="UPDATE user set firstname=?, lastname=?, username=?,phone=?, email=?,password=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, u.getFirstName());
            pst.setString(2, u.getLastName());
            pst.setString(3, u.getUsername());
            pst.setString(4, u.getPhone());
            pst.setString(5, u.getEmail());
            pst.setString(6, u.getPassword());

            pst.setInt(7, id);
            pst.executeUpdate();
            System.out.println("user has been updated!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
    //delete user
    public void deleteUser(User u){
        try {
            String request = "DELETE FROM user where id =?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setInt(1, u.getId());
            pst.executeUpdate();
            System.out.println("user has been deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
}
