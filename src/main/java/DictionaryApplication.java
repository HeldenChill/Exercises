import javafx.application.Application;
import javafx.stage.Stage;


public class DictionaryApplication extends Application {


    public void startApplication(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage = MainApplication.Instance().getApplication();
        stage.show();
        System.out.println(MainApplication.buttonWordDisplay.getWidth());
        MainApplication.Instance().runAfterShow();
    }
}
