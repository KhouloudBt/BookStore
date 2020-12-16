/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.services;

import bookstore.MyConnection;
import bookstore.entities.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author khoul
 */
public class ResourceCRUD {
        Connection cnx = MyConnection.getInstance().getCnx();

     
     //ADD_Book
     public void addResource(Resource resource){
        try {
            String request = "INSERT INTO resource VALUES"
                    +"('"+resource.getId()+"'"
                    +",'"+resource.getPath()+"')";
            
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println("Resource added !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());}
    }

     
    public List<Resource> listResources(){
        List<Resource> myList = new ArrayList<Resource>();
        try {
            String request = "select * from resource";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while(rs.next()){
            Resource resource = new Resource();
            resource.setId(rs.getInt(1));
            resource.setPath(rs.getString(2));
            myList.add(resource);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
        //delete
    public void deleteResource(Resource resource){
        try {
            String request = "DELETE FROM resource where id =?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setInt(1, resource.getId());
           
            pst.executeUpdate();
            System.out.println("Resource deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }

        public List<Resource> ListByBook(String id)
        {
           return this.listResources().stream()
                   .filter(r->r.getId_book()==id)
                   .collect(Collectors.toList());
        }
    
}
