import javafx.scene.layout.Pane;

public class PrepositionDescription extends WordDescription {
    PrepositionDescription(String data){
        super(data);
        pathFile = ".\\WordDescription\\prepositionDescription.txt";
        super.setPathFile(pathFile);
    }

    public Pane createDisplay(String type,double width){
        return super.createDisplay("Preposition",width);
    }
}
