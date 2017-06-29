/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.body;

import hospital.Hospital;
import hospital.setApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 * 咨询管理主体页
 *
 * @author mile
 */
public class ConsultingManagementBodyController implements Initializable,setApp {

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
    
}
