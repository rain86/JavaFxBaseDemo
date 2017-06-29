/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.menu;

import hospital.Hospital;
import hospital.setApp;
import hospital.utils.Constants;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 * 头部的总菜单
 * @author mile
 */
public class TopMenuController implements Initializable ,setApp{

    private Hospital application;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void setApp(Hospital application) {
        this.application = application;
    }
    
    @FXML
    private void gotoDelete(ActionEvent event) {
        try {
            String[] strs = new String[1];
            strs[0] = Constants.TOP_MENU;
            application.gotoMain(strs);
        } catch (Exception ex) {
            Logger.getLogger(QueryMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void gotoConsultingManagement(ActionEvent event) {
        try {
            String[] strs = new String[3];
            strs[0] = Constants.TOP_MENU;
            strs[1] = Constants.QUERY_MENU;
            strs[2] = Constants.CONSULTING_MANAGEMENT_BODY;
            application.reMain(strs);
        } catch (Exception ex) {
            Logger.getLogger(QueryMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void gotoexit(ActionEvent event) {
        System.exit(0);
    }
    
}
