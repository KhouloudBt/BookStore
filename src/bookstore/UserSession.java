/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import bookstore.utilities.CustomAlert;

/**
 *
 * @author khoul
 */
public class UserSession {

    private static UserSession instance;
    CustomAlert alert = new CustomAlert();
    private String userName;

    private UserSession(String userName) {
        this.userName = userName;

    }

    public static UserSession getInstance() {
        return instance;
    }

    public static UserSession getInstance(String userName) {
        if (instance == null) {
            instance = new UserSession(userName);
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

//    public Set<String> getPrivileges() {
//        return privileges;
//    }
    public void cleanUserSession() {
        userName = "";// or null

    }

    @Override
    public String toString() {
        return "UserSession{"
                + "userName='" + userName + '\''
                + '}';
    }
}
