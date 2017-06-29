/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 * 主界面：登陆成功后进入该界面，
 *
 * @author mile
 */
public class MainController implements Initializable,setApp {

    private Hospital application;
    @FXML
    VBox main_border;
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
    
    /**
     * 根据传进来的模块重新绘制主界面：
     * 拿到main_border清空子元素，然后根据数据挨个把模块添加进来并重新绘制
     */
    void loadLayout(String[] strs) {
        main_border.getChildren().clear();
        for (String str : strs) {
            URL layout = this.application.getClass().getResource(str);
            if (layout != null) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    InputStream in = Hospital.class.getResourceAsStream(str);
                    loader.setBuilderFactory(new JavaFXBuilderFactory());
                    loader.setLocation(Hospital.class.getResource(str));
                    Node page;
                    try {
                        page = (Node) loader.load(in);
                    } finally {
                        in.close();
                    }
                    main_border.getChildren().add(page);
                    setApp setapp = (setApp)loader.getController();
                    setapp.setApp(this.application);
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
