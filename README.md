# JavaFxBaseDemo
JavaFx界面基础类，一些实现不同模块随意组合的基础类

刚学JavaFx的时候会遇见如何实现界面跳转，
还有很多界面的模块都有重用，怎么实现不同模块在界面的随意组合。下面直接上代码来解决问题。

下面demo中其实只有两个界面，
一个是登陆界面LoginController.java，
一个是主界面MainController.java。(其他模块随意组合拼成不同的主界面)

Hospital.java 是程序的入口他继承了Application并且实现了main方法
继承Application就会实现其中的start方法就是新建打开界面



```
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
```

主界面有不同的模块，且各种不同组合的模块组合。所以MainController.java中我们就实现了loadLayout方法根据传参显示不同模块组合的界面

```
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
```



所有控制类都要实现该接口，即可使用hospital的重绘主界面方法，和application的方法
```
package hospital;

/**
 *
 * @author mile
 * 每个控制类都要实现这个接口，让他有application的引用，从而控制其他地方
 */
public interface setApp {
    public void setApp(Hospital application);
}
```

例如下面的菜单模块的控制类

```
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

```
