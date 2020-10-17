import javafx.scene.layout.Pane;

public class AdverbDescription extends WordDescription {
    AdverbDescription(String data){
        super(data);
        pathFile = ".\\WordDescription\\adverbDescription.txt";
        super.setData(pathFile);
    }

    public Pane createDisplay(String type,double width) {
        return super.createDisplay("Adverb",width);
    }

}
