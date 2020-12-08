/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.services;

import edu.bookstore.connection.MyConnection;
import edu.bookstore.models.Comment;
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
public class CommentCRUD {
    Connection cnx;
    public CommentCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    //create\insert
    public void addComment(){
        try {
            String request = "INSERT INTO comment(username,comment) VALUES"+
                    " ('Ben Mabrouk','first essai comment')";
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println("comment has been added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
    //create\insert
    public void addComment(Comment c){
        try {
            String request = "INSERT INTO comment(username,comment) VALUES"+
                    " (?,?)";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, c.getUsername());
            pst.setString(2, c.getComment());
            pst.executeUpdate();
            System.out.println("comment has been added!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
    //read\retrieve
    public List<Comment> readComments(){
        List<Comment> myList = new ArrayList<Comment>();
        try {
            String request = "select * from comment";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
            Comment c = new Comment();
            c.setId(rs.getInt(1));
            c.setUsername(rs.getString("username"));
            c.setComment(rs.getString(3));
            myList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    //delete
    public void deleteComment(Comment c){
        try {
            String request = "DELETE FROM comment where id =?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setInt(1, c.getId());
           
            pst.executeUpdate();
            System.out.println("comment has been deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
    //update
    public void modifyComment(Comment c,int id){
        try {
            String request="UPDATE comment set username=?,comment=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setString(1, c.getUsername());
            pst.setString(2, c.getComment());
            pst.setInt(3, id);
            pst.executeUpdate();
            System.out.println("comment has been updated!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
}
