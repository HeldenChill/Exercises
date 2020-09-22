import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Dictionary {

    private ArrayList<Word> data;
    static Dictionary inst = null;

    private Dictionary() {
        data = new ArrayList<>();
    }

    public static Dictionary Instance() {
        if (inst == null) {
            inst = new Dictionary();
        }
        return inst;
    }

    public boolean addWord(Word w) {
        data.add(w);
        return true;
    }

    public void sort(Comparator<Word> comparator) {
        Collections.sort(data, comparator);
    }

    public ArrayList<Word> getDictionary(){
        return data;
    }

    private final int space1 = 5;
    private final int space2 = 20;
    @Override
    public String toString() {
        String res = "";
        res += "No" + createSpace(space1 - 2) + "|English" + createSpace(space2 - 8) + "|Vietnamese\n";

        for (int i = 0; i < data.size(); i++) {
            String count = String.valueOf(i + 1);
            String englishWord = "|" + data.get(i).getWordTarget();
            String vietnameseWord = "|" + data.get(i).getWordExplain();
            res += count + createSpace(space1 - count.length()) + englishWord + createSpace(space2 - englishWord.length()) + vietnameseWord + "\n";
        }
        return res;
    }


    private String createSpace(int length) {
        String res = "";
        for (int i = 0; i < length; i++) {
            res += " ";
        }
        return res;
    }
}
