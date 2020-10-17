import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public abstract class WordDescription {
    protected String data = "";
    protected ArrayList<String[]> definitions;
    protected String pathFile;
    protected Pane display = null;

    public WordDescription(String data){
        definitions = new ArrayList<String[]>();
        this.data = data;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile){
        this.pathFile = pathFile;
    }

    public Pane getDisplay(){
        return display;
    }

    public boolean exportToFile(Word word){
        DictionaryManagement.Instance().writeWordToFile(word,this.data,pathFile);
        return true;
    }

    public Pane createDisplay(String type,double width){
        if(display != null){
            return display;
        }

        Text typeWord = new Text("    "+type);
        typeWord.setStyle("-fx-font-size:15;" +
                "-fx-font-weight:900;" +
                "-fx-fill:#155ccf;");
        VBox root = new VBox();
        root.getChildren().add(typeWord);
        root.setMargin(typeWord,new Insets(10,0,0,0));
        ArrayList<String[]> definitions = preprocesserDes();

        for(int i = 0; i < definitions.size(); i++){
            VBox defiBox = new VBox();
            Text defiText = new Text(definitions.get(i)[0]);
            defiBox.setSpacing(5);
            defiText.setStyle("-fx-font-size:14;" +
                    "-fx-font-weight:800;");
            defiText.setWrappingWidth(width);
            defiBox.getChildren().add(defiText);

            for(int j = 1;j < definitions.get(i).length ; j++){
                Text example = new Text("- "+definitions.get(i)[j]);
                example.setWrappingWidth(width);
                example.setStyle("-fx-font-size:12;" +
                        "-fx-font-weight:700;"+
                        "-fx-fill:#8f8f14;");
                defiBox.getChildren().add(example);
            }
            defiBox.setPadding(new Insets(5,0,0,10));
            root.getChildren().add(defiBox);
        }
        display = root;
        return display;
    }

    protected ArrayList<String[]> preprocesserDes(){
        String[] tokens = data.split("~");

        for(int i = 0;i < tokens.length; i++){
            String[] example = tokens[i].split("`");
            definitions.add(example);
        }
        return definitions;
    }


}
