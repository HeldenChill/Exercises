import javafx.scene.control.Button;
import java.util.Comparator;

public class Word {
    private String wordTarget;
    private String wordExplain;
    private static Comparator<Word> wordTargetComparator = null;
    private static Comparator<Word> wordExplainComparator = null;
    private Button button;

    Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        //button = new Button(wordTarget);
        //button.setMinSize(50,100);
    }

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

    String getWordTarget() {
        return wordTarget;
    }

    String getWordExplain() {
        return wordExplain;
    }

    void setWordTarget(String _wordTarget) {
        wordTarget = _wordTarget;
    }

    void setWordExplain(String _wordExplain) {
        wordExplain = _wordExplain;
    }

    Button getButton(){
        return button;
    }

    @Override
    public String toString(){
        return wordTarget+" | "+wordExplain;
    }
}
