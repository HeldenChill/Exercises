import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;



public class DictionaryApplication extends Application {
    public void startApplication(String[] args){
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Text text = new Text("Advanced English Dictionary!");
        Text text2 = new Text("---VBOX2 is here\n:\n:\n:");
        HBox root = new HBox();

        VBox vbox1 = new VBox();
        VBox vbox2 = new VBox();

        HBox find = new HBox();
        TextField findWordTextField = new TextField();
        Button findButton = new Button("Find");
        //findButton.setStyle("-fx-background-color: #f4f162");
        find.getChildren().addAll(findWordTextField,findButton);
        VBox recommendWord = new VBox();
        //vbox1.getChildren().addAll(text,find,recommend);

        findWordTextField.setMinWidth(200);

        vbox1.getChildren().addAll(text,find,recommendWord);
        vbox2.getChildren().addAll(text2);

        vbox1.setMargin(text,new Insets(10,70,10,70));
        vbox1.setMargin(find,new Insets(10,40,0,40));
        root.getChildren().addAll(vbox1,vbox2);
        root.setMargin(vbox2,new Insets(10,10,10,10));
        vbox1.setSpacing(10);
        recommendWord.setSpacing(5);

        Scene scene = new Scene(root);

        root.setMinSize(500,720);
        root.setMaxSize(600,720);

        stage.setMinHeight(820);
        stage.setMinWidth(750);
        stage.setMaxWidth(750);
        stage.setMaxHeight(820);

        ArrayList<Word> dictionary = Dictionary.Instance().getDictionary();
        findWordTextField.textProperty().addListener(((observableValue, oldData,newData) ->{
            System.out.println(newData);
            int[] bound = DictionaryCommandline.Instance().dictionarySearcher(newData);
            recommendWord.getChildren().removeAll(recommendWord.getChildren());
            Button wordButton;
            for(int i = bound[0]; i<= bound[1] ;i++){
                wordButton = new Button(dictionary.get(i).getWordTarget());
                wordButton.setMinSize(140,30);
                recommendWord.getChildren().add(wordButton);
                recommendWord.setMargin(wordButton,new Insets(0,0,0,70));
            }
        } ));

        stage.setScene(scene);
        stage.setTitle("This is my first Application!");
        stage.show();
    }
}
