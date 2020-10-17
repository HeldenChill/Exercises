import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordManagement {
    public static Map<String, Button> buttonToWord;
    private Map<String, ArrayList<WordDescription>> descriptions;
    private static WordManagement inst = null;

    private WordManagement() {
        buttonToWord = new HashMap<String, Button>();
        descriptions = new HashMap<String, ArrayList<WordDescription>>();
    }

    public static WordManagement Instance() {
        if (inst == null) {
            inst = new WordManagement();
        }
        return inst;
    }

    //For button
    public Button createButton(Pane display, Word word, double width) throws Exception {
        Button button = SupportApplication.createButtonStyle(word.getWordTarget(), SupportApplication.ID_STYLE.BUTTONSTYLE1);
        button.setMinSize(width, 20);
        buttonToWord.put(word.getWordTarget(), button);
        button.setOnAction(e -> {
            MainApplication.oldClikedWord = word;
            display.getChildren().removeAll(display.getChildren());
            display.getChildren().add(word.getDisplay());
        });
        return button;
    }


    public Map<String, Button> getMapButton() {
        return buttonToWord;
    }

    //---
    //For description
    private String[] pathDescriptions = new String[5];
    private String pathDirectory = ".\\WordDescription\\";

    public boolean insertDescriptionFromFile() {
        pathDescriptions[0] = "nounDescription.txt";
        pathDescriptions[1] = "verbDescription.txt";
        pathDescriptions[2] = "adjectiveDescription.txt";
        pathDescriptions[3] = "adverbDescription.txt";
        pathDescriptions[4] = "prepositionDescription.txt";
        ArrayList<Word> dic = Dictionary.Instance().getDictionary();
        for (int i = 0; i < dic.size(); i++) {
            descriptions.put(dic.get(i).getWordTarget(), new ArrayList<WordDescription>());
        }
        File file;
        for (int i = 0; i < 5; i++) {
            file = new File(pathDirectory + pathDescriptions[i]);
            if (!file.exists()) continue;
            try {
                System.out.println("OK" + i);
                Scanner scan = new Scanner(file);
                String wordTarget = null;
                String content = "";
                String des = "";
                while (scan.hasNextLine()) {
                    content = scan.nextLine();
                    if (content.equals("")) {
                        continue;
                    }
                    if (content.charAt(0) == '@') {
                        wordTarget = DictionaryManagement.nomalize(content.substring(1));
                    } else if (wordTarget == null) {
                        continue;
                    } else if (content.charAt(0) == '#' && !des.equals("")) {
                        switch (i) {
                            case 0:
                                descriptions.get(wordTarget).add(new NounDescription(des));
                                break;
                            case 1:
                                descriptions.get(wordTarget).add(new VerbDescription(des));
                                break;
                            case 2:
                                descriptions.get(wordTarget).add(new AdjectiveDescription(des));
                                break;
                            case 3:
                                descriptions.get(wordTarget).add(new AdverbDescription(des));
                                break;
                            case 4:
                                descriptions.get(wordTarget).add(new PrepositionDescription(des));
                                break;
                        }
                        des = "";
                    } else if (content.charAt(0) != '#') {
                        des += content;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found!!");
                return false;
            }
        }
        Word word;
        for (int i = 0; i < dic.size(); i++) {
            word = dic.get(i);
            word.setDescriptions(descriptions.get(word.getWordTarget()));
        }
        return true;
    }

    //--
    //For display
    public Pane createWordDisplay(Word word, double width) {
        if (word.getDisplay() != null) {
            return word.getDisplay();
        }

        VBox root = new VBox();
        Text wordTargetText = new Text(word.getWordTarget());
        wordTargetText.setStyle("-fx-font-size:17;" +
                "-fx-font-weight:900;" +
                "-fx-fill:#f5428d;");
        root.setMargin(wordTargetText, new Insets(20, 20, 20, 20));
        root.getChildren().add(wordTargetText);
        ArrayList<WordDescription> descriptions = word.getDescriptions();
        for (int i = 0; i < descriptions.size(); i++) {
            WordDescription des = descriptions.get(i);
            if (des.getDisplay() == null) {
                des.createDisplay(null, width);
            }
            root.getChildren().add(des.getDisplay());
        }
        word.setDisplay(root);
        return root;
    }
}
