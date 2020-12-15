/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.services;

import edu.bookstore.connection.CustomAlert;
import edu.bookstore.connection.MyConnection;
import edu.bookstore.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Emna
 */
public class RegisterService {
       Connection cnx;

    public RegisterService() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public void insertUser(User u){
        try {
            String request = "INSERT INTO user(id,firstname,lastname,phone) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setInt(1, u.getId());
            pst.setString(2, u.getFirstName());
            pst.setString(3,u.getLastName());
            pst.setString(4,u.getPhone());
            pst.executeUpdate();
            System.out.println("user added succesfully");
            
            String req = "INSERT INTO login (email,username,user_password,id_user)VALUES (?,?,?,?)";
            PreparedStatement p = cnx.prepareStatement(req);
            p.setString(1, u.getEmail());
            p.setString(2, u.getUsername());
            p.setString(3, u.getPassword());
            p.setInt(4, u.getId());
            System.out.println("hounu");
            try{
            p.executeUpdate();
            System.out.println("succeeess");

            } catch(Exception ex )
            {
                System.out.println("eeeeeeeeeerrrrrrrrrrrrrrorrrrr"+ex.getMessage());
        }
            System.out.println("user added succesfully into login");

            
        } catch (SQLException ex) {
        CustomAlert. showErrorAlert("Register Error", ex.getMessage());   }
   
    
}
    
    
}