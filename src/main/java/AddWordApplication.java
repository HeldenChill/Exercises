import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AddWordApplication extends AbstractApplication{

    private static AddWordApplication inst = null;
    private AddWordApplication(){
        super();
    }

    public static AddWordApplication Instance(){
        if(inst == null){
            inst = new AddWordApplication();
        }

        return inst;
    }
    public enum TYPE_WORD {
        NOUN,
        VERB,
        ADJECTIVE,
        ADVERB,
        PREPOSITION,
    }

    int countDefi = 1;
    public Pane definitionPane(){
        VBox vbox13 = new VBox();
        vbox13.setSpacing(10);
        HBox hbox131 = new HBox();
        Text defiText = new Text("Definition " + countDefi + ":");
        TextField enterDefinition = new TextField();
        enterDefinition.setMinWidth(600);
        hbox131.getChildren().addAll(defiText, enterDefinition);
        hbox131.setSpacing(10);

        ComboBox type = new ComboBox();
        type.setPromptText("Type");
        type.getItems().add("Noun");
        type.getItems().add("Verb");
        type.getItems().add("Adjective");
        type.getItems().add("Adverb");
        type.getItems().add("Preposition");

        Text exampleText = new Text("Add example for definition(One example one line):");
        TextArea exampleArea = new TextArea();
        exampleArea.setMinHeight(200);
        exampleArea.setMinWidth(600);
        vbox13.getChildren().addAll(hbox131, type, exampleText, exampleArea);
        countDefi++;
        return vbox13;
    }

    public static void addWordToDicApp(String wordTarget, String[] definition,String phonetic, TYPE_WORD[] type, String[] example) throws Exception{
        ArrayList<Word> dic = Dictionary.Instance().getDictionary();
        Word word = new Word(wordTarget,definition[0],phonetic);
        String[] data = new String[5];

        for(int i = 0; i < 5; i++){
            data[i] = "";
        }

        for(int i = 0;i < definition.length; i++){
            //preprocess
            String[] examples = example[i].split("\n");
            example[i] = "";
            for(int j = 0;j<examples.length;j++){
                example[i]+="`"+examples[j];
            }
            //--end

            if(type[i] == TYPE_WORD.NOUN){
                data[0] += "~"+definition[i]+example[i];
            }
            else if(type[i] == TYPE_WORD.VERB){
                data[1] += "~"+definition[i]+example[i];
            }
            else if(type[i] == TYPE_WORD.ADJECTIVE){
                data[2] += "~"+definition[i]+example[i];
            }
            else if(type[i] == TYPE_WORD.ADVERB){
                data[3] += "~"+definition[i]+example[i];
            }
            else if(type[i] == TYPE_WORD.PREPOSITION){
                data[4] += "~"+definition[i]+example[i];
            }


        }
        for(int i = 0;i < 5; i++){
            if(!data[i].equals("")){
                if(i == 0){
                    NounDescription des = new NounDescription(data[i]);
                    word.addDescription(des);
                }
                else if(i == 1){
                    VerbDescription des = new VerbDescription(data[i]);
                    word.addDescription(des);
                }
                else if(i == 2){
                    AdjectiveDescription des = new AdjectiveDescription(data[i]);
                    word.addDescription(des);
                }
                else if(i == 3){
                    AdverbDescription des = new AdverbDescription(data[i]);
                    word.addDescription(des);
                }
                else if(i == 4){
                    PrepositionDescription des = new PrepositionDescription(data[i]);
                    word.addDescription(des);
                }
            }
        }
        Button button = WordManagement.Instance().createButton(MainApplication.exampleWordDisplay,word,MainApplication.buttonWordWidth);
        word.setButton(button);
        WordManagement.Instance().createWordDisplay(word,MainApplication.exampleWordWidth);
        dic.add(word);
        dic.sort(Word.getWordTargetCom());
        //render app1 again
        MainApplication.renderWordButton(null,true);
    }
    public Stage getApplication(){
        countDefi = 1;
        if(app == null){

            HBox root = new HBox();
            //VBox1
            VBox vbox1 = new VBox();
            Text title = new Text("AddWord!");
            HBox hbox12 = new HBox();
            Text title2 = new Text("WordTarget:");
            TextField enterWordTarget = new TextField();
            Text title3 = new Text("Phonetic transcription:");
            TextField enterPhonetic = new TextField();
            hbox12.getChildren().addAll(title2, enterWordTarget,title3,enterPhonetic);
            hbox12.setSpacing(10);


            VBox vbox13 =(VBox)definitionPane();
            vbox1.getChildren().addAll(title, hbox12, vbox13);

            //VBox2
            VBox vbox2 = new VBox();
            Button addDefiButton = new Button("Add Definition.");
            addDefiButton.setOnAction(e->{
                vbox1.getChildren().add(definitionPane());
            });
            Button removeButton = new Button("Remove Definition.");
            removeButton.setOnAction(e->{
                if(vbox1.getChildren().size() > 3){
                    vbox1.getChildren().remove(vbox1.getChildren().get(vbox1.getChildren().size()-1));
                    countDefi--;
                }

            });
            Button addWordButton1 = new Button("Add Word.");
            addWordButton1.setOnAction(e->{
                int size = vbox1.getChildren().size()-2;

                String[] defi = new String[size];
                String[] examples = new String[size];
                TYPE_WORD[] type_words = new TYPE_WORD[size];

               for(int i = 2;i < size+2; i++){
                    VBox current = (VBox)vbox1.getChildren().get(i);
                    TextField definition = (TextField)((HBox)current.getChildren().get(0)).getChildren().get(1);
                    ComboBox type = (ComboBox) current.getChildren().get(1);
                    TextArea exampleArea = (TextArea) current.getChildren().get(3);

                    if(enterWordTarget.getText().equals("")){
                        Alert information = new Alert(AlertType.ERROR);
                        information.setContentText("Word target cannot be left blank!!!!");
                        information.show();
                        return;
                    }


                   if(definition.getText().equals("") && !exampleArea.getText().equals("")){
                       Alert information = new Alert(Alert.AlertType.ERROR);
                       information.setContentText("Definition cannot be left blank!!!!");
                       information.show();
                       return;
                   }
                   else if(definition.getText().equals("") && exampleArea.getText().equals("")){

                   }
                   else{
                       defi[i-2] = i-1+"."+definition.getText();
                   }

                    if(type.getValue() == "Noun"){
                        type_words[i-2] = TYPE_WORD.NOUN;
                    }
                    else if(type.getValue() == "Verb"){
                        type_words[i-2] = TYPE_WORD.VERB;
                    }
                    else if(type.getValue() == "Adjective"){
                        type_words[i-2] = TYPE_WORD.ADJECTIVE;
                    }
                    else if(type.getValue() == "Adverb"){
                        type_words[i-2] = TYPE_WORD.ADVERB;
                    }
                    else if(type.getValue() == "Preposition"){
                        type_words[i-2] = TYPE_WORD.PREPOSITION;
                    }
                    else{
                        Alert information = new Alert(AlertType.ERROR);
                        information.setContentText("Type isn't selected!!!!");
                        information.show();
                        return;
                    }

                    examples[i-2] = exampleArea.getText();
               }

                try {
                    addWordToDicApp(enterWordTarget.getText(),defi,enterPhonetic.getText(),type_words,examples);
                    Alert information = new Alert(AlertType.INFORMATION);
                    information.setContentText("Success add word to dictionary!");
                    information.show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            vbox2.getChildren().addAll(addDefiButton, removeButton, addWordButton1);
            ScrollPane vbox1SP = new ScrollPane();
            vbox1SP.setContent(vbox1);
            vbox1SP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            root.getChildren().addAll(vbox1SP, vbox2);
            vbox1.setPadding(new Insets(30,30,30,25));
            root.setSpacing(30);
            vbox1.setSpacing(10);
            vbox2.setSpacing(10);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setMinHeight(700);
            stage.setMaxHeight(700);
            stage.setMinWidth(900);
            stage.setMaxWidth(900);
            stage.setY(0);
            stage.setTitle("Add Word");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(MainApplication.Instance().app);
            app = stage;
        }
        return app;
    }
}
