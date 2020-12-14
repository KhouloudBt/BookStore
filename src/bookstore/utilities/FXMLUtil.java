/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.utilities;

import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author khoul
 */
public class FXMLUtil {   
    
    public static <T> T load(String loc) throws IOException {
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(FXMLUtil.class.getResource(loc));
       return loader.load();
   }
    
}
