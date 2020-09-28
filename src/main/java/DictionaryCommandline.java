import java.util.ArrayList;
public class DictionaryCommandline {
    static DictionaryCommandline inst = null;

    private DictionaryCommandline() {
    }

    public static DictionaryCommandline Instance() {
        if (inst == null) {
            inst = new DictionaryCommandline();
        }
        return inst;
    }

    /**
     * Show all word target and its correspondence word explain.
     */
    void showAllWord() {
        System.out.print(Dictionary.Instance());
    }

    /**
     * Can call to showAllWord() and insertFromCommandline().
     */
    void dictionaryBasic() {
        DictionaryManagement.Instance().insertFromCommandline();
        showAllWord();
    }

    /**
     * Can call to showAllWord(),insertFromFile and dictionaryLookUp().
     */
    void dictionaryAdvance() {
        DictionaryManagement.Instance().insertFormFile();
        showAllWord();
        System.out.println(DictionaryManagement.Instance().dictionaryLookUp());
    }

    /**
     * Find and recommend word target or explain when type each letter once
     */
    public int[] dictionarySearcher(String findedWord) {
        ArrayList<Word> dic = Dictionary.Instance().getDictionary();
        int[] bound = DictionaryManagement.Instance().dictionarySeacher(findedWord);
        if(bound == null){
            System.out.println("No word found!");
            return new int[]{-1,-2};
        }

        for(int i = bound[0];i<= bound[1];i++){
            System.out.println(dic.get(i).getWordTarget());
        }
        return bound;
    }
}
