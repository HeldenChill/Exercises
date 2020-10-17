import javafx.scene.control.Button;
import javafx.stage.Stage;


public class SupportApplication {
    public enum ID_STYLE {
        BUTTONSTYLE1,
        BUTTONSTYLE2,
        BUTTONSTYLE3,
    }

    public enum ID_APPLICATION {
        ADD_WORD,
        CHANGE_WORD,
        DELETE_WORD,
    }



    public static Button createButtonStyle(String nameButton, ID_STYLE style) throws Exception {
        Button button = new Button(nameButton);
        button.setStyle(getStyle(style).current);
        button.setOnMouseEntered(e -> {
            button.setStyle(getStyle(style).hover);
        });

        button.setOnMouseExited(e -> {
            button.setStyle(getStyle(style).current);
        });

        return button;
    }



    public static Stage startApplication(ID_APPLICATION app) {
        Stage res = null;
        if (app == ID_APPLICATION.ADD_WORD) {
            AddWordApplication addApp = AddWordApplication.Instance();
            res = addApp.getApplication();
        }
        else if(app == ID_APPLICATION.CHANGE_WORD){
            ChangeWordApplication changeApp = ChangeWordApplication.Instance();
            res = changeApp.getApplication();
        }
        return res;
    }

    private static Style getStyle(ID_STYLE style) {
        if (style == ID_STYLE.BUTTONSTYLE1) {
            return new Style("-fx-font-size: 15px;\n" +
                    "    -fx-font-weight: 700;\n" +
                    "    -fx-text-fill:black;\n" +
                    "    -fx-color: #fff;\n" +
                    "    -fx-background-color: transparent;\n" +
                    "    -fx-border-width: 2px;\n" +
                    "    -fx-border-style: solid;\n" +
                    "    -fx-border-color: #d7dadb;\n" +
                    "    -fx-cursor: pointer;\n" +
                    "    -fx-width: max-content;",
                    "-fx-font-size: 15px;\n" +
                    "    -fx-font-weight: 700;\n" +
                    "    -fx-text-fill:black;\n" +
                    "    -fx-color: #42b0f5;\n" +
                    "    -fx-background-color: #42b0f5;\n" +
                    "    -fx-border-width: 2px;\n" +
                    "    -fx-border-style: solid;\n" +
                    "    -fx-border-color: #42b0f5;\n" +
                    "    -fx-cursor: pointer;\n" +
                    "    -fx-width: max-content;" ,
                    "-fx-font-size: 15px;\n" +
                    "    -fx-font-weight: 700;\n" +
                    "    -fx-text-fill:white;\n" +
                    "    -fx-color: #e63e3e;\n" +
                    "    -fx-background-color: #e63e3e;  \n" +
                    "    -fx-border-width: 2px;\n" +
                    "    -fx-border-style: solid;\n" +
                    "    -fx-border-color: #f50057;\n" +
                    "    -fx-cursor: pointer;\n" +
                    "    -fx-width: max-content;");
        } else if (style == ID_STYLE.BUTTONSTYLE2) {
            return new Style("-fx-font-size: 13px;\n" +
                    "    -fx-font-weight: 500;\n" +
                    "    -fx-text-fill:white;\n" +
                    "    -fx-color: #fff;\n" +
                    "    -fx-background-color: #3b7d70;\n" +
                    "    -fx-cursor: pointer;\n" +
                    "    -fx-width: max-content;",
                    "-fx-font-size: 13px;\n" +
                    "    -fx-font-weight: 500;\n" +
                    "    -fx-text-fill:white;\n" +
                    "    -fx-color: #fff;\n" +
                    "    -fx-background-color: #3b7d70;\n" +
                    "    -fx-cursor: pointer;\n" +
                    "    -fx-width: max-content;" ,
                    "-fx-font-size: 13px;\n" +
                    "    -fx-font-weight: 500;\n" +
                    "    -fx-text-fill:white;\n" +
                    "    -fx-color: #4bd6ba;\n" +
                    "    -fx-background-color: #4bd6ba;  \n" +
                    "    -fx-cursor: pointer;\n" +
                    "    -fx-width: max-content;");

        } else if (style == ID_STYLE.BUTTONSTYLE3) {

        }
        return null;
    }

}
