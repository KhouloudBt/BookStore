/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.entities;

/**
 *
 * @author Emna
 */
public class Comment {
    private int id;
    private String username;
    private String comment;

    public Comment() {
    }

    public Comment(int id, String username, String comment) {
        this.id = id;
        this.username = username;
        this.comment = comment;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", username=" + username + ", comment=" + comment + '}';
    }
    
    
}
