/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import hospital.utils.Constants;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * 登陆界面
 * @author mile
 */
public class LoginController implements Initializable ,setApp{
    
    private Hospital application;
    
    @FXML
    TextField username;
    
    @FXML
    PasswordField password;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        System.exit(0);
    }
    
    @FXML
    private void gotoMainPageAction(ActionEvent event) {
        try {
            String[] strs = new String[1];
            strs[0] = Constants.TOP_MENU;
            application.gotoMain(strs);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setApp(Hospital application) {
        this.application = application;
    }
    
}
