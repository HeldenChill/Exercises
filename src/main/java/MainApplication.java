import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainApplication extends AbstractApplication{
    private static MainApplication inst = null;
    private MainApplication(){
    }

    public static MainApplication Instance(){
        if(inst == null){
            inst = new MainApplication();
        }
        return inst;
    }
    public static double buttonWordWidth;
    public static double exampleWordWidth;
    public static Pane buttonWordDisplay;
    public static Pane exampleWordDisplay;
    public static Pane exampleWordTitleDisplay;

    public static Word oldClikedWord = null;
    public Stage getApplication() throws Exception{
        if(app == null){
            Stage stage = new Stage();
            HBox root = new HBox();
            VBox vbox1 = new VBox();
            VBox vbox2 = new VBox();
            VBox vbox23 = new VBox();

            //design vbox1
            Text title1 = new Text("Advanced English Dictionary!");
            title1.setStyle("-fx-fill:white;"+
                    "-fx-font-size:14px;"+
                    "-fx-font-weight:900;");
            VBox vbox11 = new VBox();
            vbox1.setStyle("-fx-background-color:#d7dadb;");
            vbox11.setStyle("-fx-background-color:#033567;");
            vbox11.setPadding(new Insets(11,0,5,0));
            vbox11.setSpacing(10);

            HBox hbox112 = new HBox();
            hbox112.setSpacing(10);
            Button addWordButton = SupportApplication.createButtonStyle("Add Word", SupportApplication.ID_STYLE.BUTTONSTYLE2);
            addWordButton.setOnAction(e->{
                Stage addWord = SupportApplication.startApplication(SupportApplication.ID_APPLICATION.ADD_WORD);
                addWord.setWidth(900);
                addWord.setHeight(700);
                addWord.show();
            });
            Button changeWordButton = SupportApplication.createButtonStyle("Change Word", SupportApplication.ID_STYLE.BUTTONSTYLE2);
            changeWordButton.setOnAction(e->{
                if(oldClikedWord == null){
                    return;
                }
                Stage changeWord = SupportApplication.startApplication(SupportApplication.ID_APPLICATION.CHANGE_WORD);
                changeWord.setWidth(900);
                changeWord.setHeight(700);
                changeWord.show();
            });
            Button deleteButton = SupportApplication.createButtonStyle("Delete Word", SupportApplication.ID_STYLE.BUTTONSTYLE2);
            deleteButton.setOnAction(e->{
                if(oldClikedWord != null){
                    WordManagement.buttonToWord.remove(oldClikedWord.getWordTarget());
                    Dictionary.Instance().getDictionary().remove(oldClikedWord);
                    oldClikedWord = null;
                    renderWordButton(null,true);
                    exampleWordDisplay.getChildren().removeAll(exampleWordDisplay.getChildren());
                }

            });
            hbox112.getChildren().addAll(addWordButton,changeWordButton,deleteButton);
            hbox112.setMargin(addWordButton,new Insets(0,0,0,10));
            vbox11.getChildren().addAll(title1,hbox112);

            HBox hbox12 = new HBox();
            TextField findWordTextField = new TextField();


            hbox12.getChildren().add(findWordTextField);
            VBox vbox13 = new VBox();

            findWordTextField.setMinWidth(265);

            //System.out.println(title1.getLayoutX()+","+title1.getLayoutY());

            vbox1.getChildren().addAll(vbox11,hbox12,vbox13);

            vbox1.setMargin(title1,new Insets(0,50,10,50));
            vbox1.setMargin(hbox12,new Insets(10,0,0,20));
            root.getChildren().addAll(vbox1,vbox2);
            vbox1.setSpacing(10);
            vbox13.setSpacing(5);

            //
            //design vbox2
            Text title2 = new Text("Definition");
            title2.setStyle("-fx-fill:white;"+
                    "-fx-font-size:15px;"+
                    "-fx-font-weight:900;"+
                    "-fx-color:#e6ac1c");
            VBox vbox21 = new VBox();
            HBox hbox22 = new HBox();

            ScrollPane vbox23SP = new ScrollPane();
            vbox23SP.setContent(vbox23);
            vbox23SP.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            vbox23SP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            vbox21.setStyle("-fx-background-color:#0a4580;");
            vbox21.setPadding(new Insets(58,500,8,10));

            //vbox2.setMargin(title2,new Insets(10,140,10,0));
            vbox21.getChildren().add(title2);
            vbox2.getChildren().addAll(vbox21,hbox22,vbox23SP);


            Scene scene = new Scene(root);

            stage.setMinHeight(820);
            stage.setMinWidth(900);
            stage.setMaxWidth(900);
            stage.setMaxHeight(820);

            stage.setScene(scene);
            stage.setTitle("This is my first Application!");
            exampleWordDisplay = vbox23;
            buttonWordDisplay = vbox13;
            exampleWordTitleDisplay = vbox21;

            findWordTextField.textProperty().addListener(((observableValue, oldData,newData) ->{
                int[] bound = DictionaryManagement.Instance().dictionarySeacher(newData);
                renderWordButton(bound,false);
            } ));
            app = stage;
        }
        return app;
    }



    @Override
    public void runAfterShow() throws Exception{
        exampleWordWidth = exampleWordTitleDisplay.getWidth()-26;
        buttonWordWidth = buttonWordDisplay.getWidth()+10;
        ArrayList<Word> dic = Dictionary.Instance().getDictionary();
        for(int i = 0; i < dic.size(); i++){
            WordManagement.Instance().createWordDisplay(dic.get(i),exampleWordWidth);
            Button button = WordManagement.Instance().createButton(exampleWordDisplay,dic.get(i),buttonWordWidth);
            dic.get(i).setButton(button);
            MainApplication.buttonWordDisplay.getChildren().add(button);
        }
    }

    public static void renderWordButton(int[] bound,boolean renderFull){
        ArrayList<Word> dic = Dictionary.Instance().getDictionary();
        buttonWordDisplay.getChildren().removeAll(buttonWordDisplay.getChildren());
        if(renderFull == true){
            for(int i = 0; i < dic.size();i++){
                buttonWordDisplay.getChildren().add(dic.get(i).getButton());
            }
            return;
        }
        for(int i = bound[0]; i <= bound[1]; i++){
            buttonWordDisplay.getChildren().add(dic.get(i).getButton());
        }
    }
}
