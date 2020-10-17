import javafx.scene.layout.Pane;

public class AdjectiveDescription extends WordDescription{
    AdjectiveDescription(String data){
        super(data);
        pathFile = ".\\WordDescription\\adjectiveDescription.txt";
        super.setPathFile(pathFile);
    }

    public Pane createDisplay(String type,double width){
        return super.createDisplay("Adjective",width);
    }
}
