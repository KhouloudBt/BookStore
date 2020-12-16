package bookstore.services;

import bookstore.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Emna
 */
public class LoginService {

    Connection cnx;

    public LoginService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public boolean adminverified(String username, String password) {
        boolean check = false;
        try {
            String request = "select * from admin where username='" + username + "'and pwd='" + password+"'";

            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(request);
            check = rs.next();
        } catch (Exception ex) {
            System.out.println("error while cheking admin" + ex.getMessage());
        }
        return check;
    }

    public boolean authenticate(String username, String password) {
        String pwd = "";
        try {
            String req = "select password from user where username ='" + username + "'";
            PreparedStatement st = cnx.prepareStatement(req);

            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                pwd = res.getString("password");
            }
            if (!(pwd.length() == 0) && (!(password.length() == 0))) {
                String hash =BCrypt.hashpw(password, BCrypt.gensalt(12));
                System.out.println(hash);
                if (hash.subSequence(0, 6).equals(pwd.subSequence(0, 6))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        //System.out.println(check);

        return false;
    }

    public String authority(String username) {
        String authority = "";

        try {
            String request = "select authority_name from authority a,user u where username= '" + username + "'  and a.id= u.id_authority";

            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery(request);

            if (rs.next())
            {
            authority = rs.getString(1);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            //CustomAlert.showErrorAlert("authority ", "Error while trying to get authority");
        }
        return authority;
    }

    public boolean login() {
        return false;
    }
}
