package bookstore.services;

import bookstore.MyConnection;
import bookstore.entities.User;
import bookstore.utilities.CustomAlert;
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
            String request = "INSERT INTO user(firstname,lastname,username,phone,password,email) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, u.getFirstName());
            pst.setString(2,u.getLastName());
            pst.setString(3,u.getUsername());
            pst.setString(4,u.getPhone());
            pst.setString(5,u.getPassword());
            pst.setString(6,u.getEmail());
            pst.executeUpdate();
            System.out.println("user added succesfully");
        } catch (SQLException ex) {
        CustomAlert. showErrorAlert("Register Error", ex.getMessage());}
   
    
}
   
    
}