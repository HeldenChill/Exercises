import javafx.scene.layout.Pane;

public class NounDescription extends WordDescription {
    NounDescription(String data){
        super(data);
        pathFile = ".\\WordDescription\\nounDescription.txt";
        super.setPathFile(pathFile);
    }
    public Pane createDisplay(String type,double width){
        return super.createDisplay("Noun",width);
    }
}
