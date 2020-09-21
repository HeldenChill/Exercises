import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class DictionaryManagement {
    private Scanner scan;
    private Dictionary dictionary;
    private static DictionaryManagement inst = null;
    private File fileSaveWord = null;

    private DictionaryManagement() {
        dictionary = Dictionary.Instance();
        fileSaveWord = new File("E:\\Study\\IT\\SaveJava\\Git\\BigTask\\Exercises\\dictionaries.txt");
    }

    public static DictionaryManagement Instance() {
        if (inst == null) {
            inst = new DictionaryManagement();
        }
        return inst;
    }

    void insertFromCommandline() {
        boolean input = true;
        scan = new Scanner(System.in);
        String targetWord = "";
        String explainWord = "";
        String unprocessStr = "";
        System.out.println("Enter target word and the corresponding explanation word,separated by a \"/\" (Enter 0 to stop enter)!");
        while (input) {
            unprocessStr = scan.nextLine();

            String[] tokens = unprocessStr.split("/", 0);
            targetWord = tokens[0];
            if (targetWord.equals("0")) {
                dictionary.sort(Word.getWordTargetCom());
                input = false;
                break;
            }

            try {
                explainWord = tokens[1];
            } catch (Exception e) {
                System.out.println("No explain word found !");
                break;
            }

            Word newWord = new Word(targetWord, explainWord);
            dictionary.addWord(newWord);
        }
    }

    void insertFormFile() {
        try {
            scan = new Scanner(fileSaveWord);
            String wordTarget;
            String wordExplain;
            String content;
            String[] tokens;
            while (scan.hasNextLine()) {
                content = scan.nextLine();
                tokens = content.split("\t");
                wordTarget = tokens[0];

                try {
                    wordExplain = tokens[1];
                } catch (Exception e) {
                    System.out.println("No explain word found !");
                    break;
                }

                Word newWord = new Word(wordTarget, wordExplain);
                Dictionary.Instance().addWord(newWord);
            }
            Dictionary.Instance().sort(Word.getWordTargetCom());
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!");
        }
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
