import javafx.scene.layout.Pane;

public class VerbDescription extends WordDescription {
    VerbDescription(String data){
        super(data);
        pathFile = ".\\WordDescription\\verbDescription.txt";
        super.setPathFile(pathFile);
    }

    public Pane createDisplay(String type,double width){
        return super.createDisplay("Verb",width);
    }

}
