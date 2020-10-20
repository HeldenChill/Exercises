import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Comparator;

public class Word {
    private String wordTarget;
    private String wordExplain;
    private String phonetic;
    private static Comparator<Word> wordTargetComparator = null;
    private static Comparator<Word> wordExplainComparator = null;
    private Button button;
    private ArrayList<WordDescription> descriptions;
    private Pane display = null;

    Word(String wordTarget, String wordExplain) {
        this.wordTarget = DictionaryManagement.nomalize(wordTarget);
        this.wordExplain = wordExplain;
        descriptions = new ArrayList<WordDescription>();
    }
    Word(String wordTarget,String wordExplain,String phonetic){
        this.wordTarget = DictionaryManagement.nomalize(wordTarget);
        this.wordExplain = wordExplain.trim();
        this.phonetic = phonetic.trim();
        descriptions = new ArrayList<WordDescription>();
    }

    //For sort word in array list
    private static class WordTargetCompare implements Comparator<Word> {
        public int compare(Word one, Word two) {
            return one.getWordTarget().compareTo(two.getWordTarget());
        }
    }

    private static class WordExplainCompare implements Comparator<Word> {
        public int compare(Word one, Word two) {
            return one.getWordExplain().compareTo(two.getWordExplain());
        }
    }

    public static Comparator<Word> getWordTargetCom() {
        if (wordTargetComparator == null) {
            wordTargetComparator = new WordTargetCompare();
        }
        return wordTargetComparator;
    }

    public static Comparator<Word> getWordExplainCom() {
        if (wordExplainComparator == null) {
            wordExplainComparator = new WordExplainCompare();
        }
        return wordExplainComparator;
    }
    //---
    //Get set method for property of class
    public String getWordTarget() {
        return wordTarget;
    }

    public void setWordTarget(String _wordTarget) {
        wordTarget = _wordTarget;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public void setWordExplain(String _wordExplain) {
        wordExplain = _wordExplain;
    }

    public String getPhonetic(){return phonetic;}

    public void setPhonetic(String phonetic){
        this.phonetic = phonetic;
    }

    public Button getButton(){
        return button;
    }

    public void setButton(Button button){
        this.button = button;
    }

    public void setDescriptions(ArrayList<WordDescription> descriptions){
        this.descriptions = descriptions;
    }
    public void addDescription(WordDescription description){
        this.descriptions.add(description);
    }
    public ArrayList<WordDescription> getDescriptions(){
        return descriptions;
    }

    public void setDisplay(Pane display){
        this.display = display;
    }
    public Pane getDisplay(){
        return display;
    }
    @Override
    public String toString(){
        return wordTarget+" | "+wordExplain;
    }

}
