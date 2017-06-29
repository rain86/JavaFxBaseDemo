/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import hospital.utils.Constants;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * 程序入口
 * @author mile
 */
public class Hospital extends Application {
    
    private Stage stage;
    private MainController main;
    

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        LoginController login = (LoginController) replaceSceneContent(Constants.LOGIN_PAGE);
        this.stage.show();
        login.setApp(this);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(Hospital.class, (java.lang.String[])null);
    }
    
    
    /**
     * 替换界面并返回相应的控制类
     */
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Hospital.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Hospital.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 1000, 500);
        System.out.print("replaceSceneContent");
        stage.setScene(scene);
        stage.setTitle("Hospital");
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    /**
     * 登陆成功后进入主页面
     */
    public void gotoMain(String[] strs) {
        try {
            main = (MainController) replaceSceneContent(Constants.MAIN_PAGE);//替换界面
            main.setApp(this);//给controller传入application
            main.loadLayout(strs);//给主界面中加载需要模块 strs 是模块集合
        } catch (Exception ex) {
            Logger.getLogger(Hospital.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 重绘主界面
     */
    public void reMain(String[] strs){
        main.loadLayout(strs);
    }
    
}
