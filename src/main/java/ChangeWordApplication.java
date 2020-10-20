import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChangeWordApplication extends AbstractApplication{
    static Word wordNeedingChange = null;
    private static ChangeWordApplication inst = null;
    private ChangeWordApplication(){

    }

    public static ChangeWordApplication Instance(){
        if(inst == null){
            inst = new ChangeWordApplication();
        }
        return inst;
    }

    int countDefi = 1;
    Pane definitionPane = AddWordApplication.Instance().definitionPane();
    public static void changeWordinDic(String wordTarget, String[] definition,String phonetics, AddWordApplication.TYPE_WORD[] type, String[] example) throws Exception{
        ArrayList<Word> dic = Dictionary.Instance().getDictionary();
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

            if(type[i] == AddWordApplication.TYPE_WORD.NOUN){
                data[0] += "~"+definition[i]+example[i];
            }
            else if(type[i] == AddWordApplication.TYPE_WORD.VERB){
                data[1] += "~"+definition[i]+example[i];
            }
            else if(type[i] == AddWordApplication.TYPE_WORD.ADJECTIVE){
                data[2] += "~"+definition[i]+example[i];
            }
            else if(type[i] == AddWordApplication.TYPE_WORD.ADVERB){
                data[3] += "~"+definition[i]+example[i];
            }
            else if(type[i] == AddWordApplication.TYPE_WORD.PREPOSITION){
                data[4] += "~"+definition[i]+example[i];
            }


        }

        wordNeedingChange.setPhonetic(phonetics);
        wordNeedingChange.getDescriptions().removeAll(wordNeedingChange.getDescriptions());
        for(int i = 0;i < 5; i++){
            if(!data[i].equals("")){
                if(i == 0){
                    NounDescription des = new NounDescription(data[i]);
                    wordNeedingChange.addDescription(des);
                }
                else if(i == 1){
                    VerbDescription des = new VerbDescription(data[i]);
                    wordNeedingChange.addDescription(des);
                }
                else if(i == 2){
                    AdjectiveDescription des = new AdjectiveDescription(data[i]);
                    wordNeedingChange.addDescription(des);
                }
                else if(i == 3){
                    AdverbDescription des = new AdverbDescription(data[i]);
                    wordNeedingChange.addDescription(des);
                }
                else if(i == 4){
                    PrepositionDescription des = new PrepositionDescription(data[i]);
                    wordNeedingChange.addDescription(des);
                }
            }
        }
        if(!wordTarget.equals(wordNeedingChange.getButton().getText())){
            wordNeedingChange.getButton().setText(wordTarget);
        }
        wordNeedingChange.setWordTarget(wordTarget);
        wordNeedingChange.setDisplay(null);
        wordNeedingChange.setDisplay(WordManagement.Instance().createWordDisplay(wordNeedingChange,555));
        dic.sort(Word.getWordTargetCom());

        //render app1 again
        MainApplication.renderWordButton(null,true);
    }
    private Pane definitionPane(String definition, String examples, AddWordApplication.TYPE_WORD typeWord){

        VBox vbox13 = new VBox();
        vbox13.setSpacing(10);
        HBox hbox131 = new HBox();
        Text defiText = new Text("Definition " + countDefi + ":");
        TextField enterDefinition = new TextField();
        enterDefinition.setText(definition);
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

        if(typeWord == AddWordApplication.TYPE_WORD.NOUN){
            type.setValue(type.getItems().get(0));
        }
        else if(typeWord == AddWordApplication.TYPE_WORD.VERB){
            type.setValue(type.getItems().get(1));
        }
        else if(typeWord == AddWordApplication.TYPE_WORD.ADJECTIVE){
            type.setValue(type.getItems().get(2));
        }
        else if(typeWord == AddWordApplication.TYPE_WORD.ADVERB){
            type.setValue(type.getItems().get(3));
        }
        else if(typeWord == AddWordApplication.TYPE_WORD.PREPOSITION){
            type.setValue(type.getItems().get(4));
        }

        Text exampleText = new Text("Add example for definition(One example one line):");
        TextArea exampleArea = new TextArea();
        exampleArea.setMinHeight(200);
        exampleArea.setMinWidth(600);
        exampleArea.setText(examples);
        vbox13.getChildren().addAll(hbox131, type, exampleText, exampleArea);
        countDefi++;
        return vbox13;
    }
    public Stage getApplication(){
        int[] indexDefi = {1,1,1,1,1};
        wordNeedingChange = MainApplication.oldClikedWord;
            HBox root = new HBox();
            //VBox1
            VBox vbox1 = new VBox();
            Text title = new Text("Change Word!");
            HBox hbox12 = new HBox();
            Text title2 = new Text("WordTarget:");
            TextField enterWordTarget = new TextField();
            Text title3 = new Text("Phonetic transcription:");
            TextField enterPhonetic = new TextField();
            enterPhonetic.setText(wordNeedingChange.getPhonetic());
            enterWordTarget.setText(wordNeedingChange.getWordTarget());
            hbox12.getChildren().addAll(title2, enterWordTarget,title3,enterPhonetic);
            hbox12.setSpacing(10);
            vbox1.getChildren().addAll(title,hbox12);
            for(int i = 0;i < wordNeedingChange.getDescriptions().size();i++){
                WordDescription description = wordNeedingChange.getDescriptions().get(i);
                String[] definition = description.getData().split("~");
                if(description instanceof NounDescription){
                    for(int j = 1;j < definition.length; j++){
                        String[] tokens = definition[j].split("`");
                        String examples = "";
                        for(int k = 1;k < tokens.length;k++){
                            examples+=tokens[k]+"\n";
                        }

                        if(tokens[0].charAt(0) >= '0' && tokens[0].charAt(0) <= '9'){
                            if(tokens[0].charAt(1) == '.'){
                                tokens[0] = tokens[0].substring(2);
                            }
                            else if(tokens[0].charAt(2) == '.'){
                                tokens[0] = tokens[0].substring(3);
                            }
                        }
                        vbox1.getChildren().add(definitionPane(tokens[0],examples, AddWordApplication.TYPE_WORD.NOUN));
                    }
                }
                else if(description instanceof VerbDescription){
                    for(int j = 1;j < definition.length; j++){
                        String[] tokens = definition[j].split("`");
                        String examples = "";
                        for(int k = 1;k < tokens.length;k++){
                            examples+=tokens[k]+"\n";
                        }

                        if(tokens[0].charAt(0) >= '0' && tokens[0].charAt(0) <= '9'){
                            if(tokens[0].charAt(1) == '.'){
                                tokens[0] = tokens[0].substring(2);
                            }
                            else if(tokens[0].charAt(2) == '.'){
                                tokens[0] = tokens[0].substring(3);
                            }
                        }
                        vbox1.getChildren().add(definitionPane(tokens[0],examples, AddWordApplication.TYPE_WORD.VERB));
                    }
                }
                else if(description instanceof AdjectiveDescription){
                    for(int j = 1;j < definition.length; j++){
                        String[] tokens = definition[j].split("`");
                        String examples = "";
                        for(int k = 1;k < tokens.length;k++){
                            examples+=tokens[k]+"\n";
                        }

                        if(tokens[0].charAt(0) >= '0' && tokens[0].charAt(0) <= '9'){
                            if(tokens[0].charAt(1) == '.'){
                                tokens[0] = tokens[0].substring(2);
                            }
                            else if(tokens[0].charAt(2) == '.'){
                                tokens[0] = tokens[0].substring(3);
                            }
                        }
                        vbox1.getChildren().add(definitionPane(tokens[0],examples, AddWordApplication.TYPE_WORD.ADJECTIVE));
                    }
                }
                else if(description instanceof AdverbDescription){
                    for(int j = 1;j < definition.length; j++){
                        String[] tokens = definition[j].split("`");
                        String examples = "";
                        for(int k = 1;k < tokens.length;k++){
                            examples+=tokens[k]+"\n";
                        }

                        if(tokens[0].charAt(0) >= '0' && tokens[0].charAt(0) <= '9'){
                            if(tokens[0].charAt(1) == '.'){
                                tokens[0] = tokens[0].substring(2);
                            }
                            else if(tokens[0].charAt(2) == '.'){
                                tokens[0] = tokens[0].substring(3);
                            }
                        }
                        vbox1.getChildren().add(definitionPane(tokens[0],examples, AddWordApplication.TYPE_WORD.ADVERB));
                    }
                }
                else if(description instanceof PrepositionDescription){
                    for(int j = 1;j < definition.length; j++){
                        String[] tokens = definition[j].split("`");
                        String examples = "";
                        for(int k = 1;k < tokens.length;k++){
                            examples+=tokens[k]+"\n";
                        }

                        if(tokens[0].charAt(0) >= '0' && tokens[0].charAt(0) <= '9'){
                            if(tokens[0].charAt(1) == '.'){
                                tokens[0] = tokens[0].substring(2);
                            }
                            else if(tokens[0].charAt(2) == '.'){
                                tokens[0] = tokens[0].substring(3);
                            }
                        }
                        vbox1.getChildren().add(definitionPane(tokens[0],examples, AddWordApplication.TYPE_WORD.PREPOSITION));
                    }
                }
            }


            //VBox2
            VBox vbox2 = new VBox();
            Button addDefiButton = new Button("Add Definition.");
            addDefiButton.setOnAction(e->{
                vbox1.getChildren().add(definitionPane("","",null));
            });
            Button removeButton = new Button("Remove Definition.");
            removeButton.setOnAction(e->{
                if(vbox1.getChildren().size() > 3){
                    vbox1.getChildren().remove(vbox1.getChildren().get(vbox1.getChildren().size()-1));
                    countDefi--;
                }

            });
            Button addWordButton1 = new Button("Change Word.");
            addWordButton1.setOnAction(e->{
                int size = vbox1.getChildren().size()-2;
                countDefi = 1;

                String[] defi = new String[size];
                String[] examples = new String[size];
                AddWordApplication.TYPE_WORD[] type_words = new AddWordApplication.TYPE_WORD[size];

                for(int i = 2;i < size+2; i++){
                    VBox current = (VBox)vbox1.getChildren().get(i);
                    TextField definition = (TextField)((HBox)current.getChildren().get(0)).getChildren().get(1);
                    ComboBox type = (ComboBox) current.getChildren().get(1);
                    TextArea exampleArea = (TextArea) current.getChildren().get(3);

                    if(enterWordTarget.getText().equals("")){
                        Alert information = new Alert(Alert.AlertType.ERROR);
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
                        /*defi[i-2] = i-1+"."+definition.getText();*/
                    }



                    if(type.getValue() == "Noun"){
                        defi[i-2] = indexDefi[0]+"."+definition.getText();
                        indexDefi[0]++;
                        type_words[i-2] = AddWordApplication.TYPE_WORD.NOUN;
                    }
                    else if(type.getValue() == "Verb"){
                        defi[i-2] = indexDefi[1]+"."+definition.getText();
                        indexDefi[1]++;
                        type_words[i-2] = AddWordApplication.TYPE_WORD.VERB;
                    }
                    else if(type.getValue() == "Adjective"){
                        defi[i-2] = indexDefi[2]+"."+definition.getText();
                        indexDefi[2]++;
                        type_words[i-2] = AddWordApplication.TYPE_WORD.ADJECTIVE;
                    }
                    else if(type.getValue() == "Adverb"){
                        defi[i-2] = indexDefi[3]+"."+definition.getText();
                        indexDefi[3]++;
                        type_words[i-2] = AddWordApplication.TYPE_WORD.ADVERB;
                    }
                    else if(type.getValue() == "Preposition"){
                        defi[i-2] = indexDefi[4]+"."+definition.getText();
                        indexDefi[4]++;
                        type_words[i-2] = AddWordApplication.TYPE_WORD.PREPOSITION;
                    }
                    else{
                        Alert information = new Alert(Alert.AlertType.ERROR);
                        information.setContentText("Type isn't selected!!!!");
                        information.show();
                        return;
                    }

                    examples[i-2] = exampleArea.getText();
                }

                try {
                    changeWordinDic(enterWordTarget.getText(),defi,enterPhonetic.getText(),type_words,examples);
                    Alert information = new Alert(Alert.AlertType.INFORMATION);
                    information.setContentText("Success add word to dictionary!");
                    information.show();
                    for(int i = 0; i < 5; i++){
                        indexDefi[i] = 1;
                    }
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
            stage.setTitle("Change Word");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(MainApplication.Instance().app);
            app = stage;

        return app;
    }
}

