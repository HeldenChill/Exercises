import java.util.Scanner;

public class DictionaryManagement {
    Scanner scan;
    Dictionary dictionary;
    private static DictionaryManagement inst = null;

    private DictionaryManagement() {
        scan = new Scanner(System.in);
        dictionary = Dictionary.Instance();
    }

    public static DictionaryManagement Instance() {
        if (inst == null) {
            inst = new DictionaryManagement();
        }
        return inst;
    }

    void insertFromCommandline() {
        boolean input = true;
        String targetWord = "";
        String explainWord = "";
        String unprocessStr = "";
        System.out.println("Enter target word and the corresponding explanation word,separated by a \"|\" (Enter 0 to stop enter)!");
        while (input) {
            unprocessStr = scan.nextLine();

            String[] tokens = unprocessStr.split("/", 0);
            targetWord = tokens[0];
            if (targetWord.equals("0")) {
                dictionary.sort(Word.getWordTargetCom());
                input = false;
                break;
            }
            explainWord = tokens[1];
            Word newWord = new Word(targetWord, explainWord);
            dictionary.addWord(newWord);
        }
    }

    void insertFormFile() {

    }

    /**
     * Find word by commandline.
     */
    String dictionaryLookUp(String wordTarget) {
        return "";
    }

    boolean changWord(String wordTarget) {
        return true;
    }

    boolean deleteWord(String wordTarget, String wordExplain) {
        return true;
    }

    /**
     * Export data to file.
     */
    boolean dictionaryExportToFile() {
        return true;
    }
}
