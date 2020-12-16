
package bookstore.services;

import bookstore.MyConnection;
import bookstore.UserSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Emna
 */
public class LoginService {

    Connection cnx;

    public LoginService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public boolean authenticate(String a, String b) {
        boolean check = false;
        //if its admin
        try {
            String request = "select * from Admin ";

            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(request);

            while (rs.next()) {
                String name = rs.getString(2);
                String job = rs.getString(3);
                if ((a.equals(name)) && (b.equals(job))) {
                    check = true;
                    UserSession.getInstance(a);
                }
            }

            // select * from login where username=a and user_password=b
            // result set null==> check = false si non check = true
            String request1 = "select * from login ";

            Statement ps = cnx.createStatement();
            ResultSet r = ps.executeQuery(request1);

            while (r.next()) {
                String nameuser = r.getString(3);
                String pwduser = r.getString(4);
                if ((a.equals(nameuser)) && (b.equals(pwduser))) {
                    check = true;
                    UserSession.getInstance(a);
                }
            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
        System.out.println(check);

        return check;
    }

    public String authority(String username) {
        String authority = "";

        try {
            String request = "select authority_name from authority A,user u where username= " + username + " and a.user_id= u.id";

            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(request);

            authority = rs.getString(1);

        } catch (Exception ex) {
            
            //CustomAlert.showErrorAlert("authority ", "Error while trying to get authority");
        }
        return authority;
    }

    public boolean login() {
        return false;
    }
}