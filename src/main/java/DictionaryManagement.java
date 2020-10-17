import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    private Scanner scan;
    private Dictionary dictionary;
    private static DictionaryManagement inst = null;
    private File fileSaveWord = null;

    private DictionaryManagement() {
        dictionary = Dictionary.Instance();
        fileSaveWord = new File(".\\dictionaries.txt");
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
            targetWord = nomalize(tokens[0]);
            if (targetWord.equals("0")) {
                dictionary.sort(Word.getWordTargetCom());
                input = false;
                break;
            }

            try {
                explainWord = nomalize(tokens[1]);
            } catch (Exception e) {
                System.out.println("No explain word found !");
                break;
            }

            Word newWord = new Word(targetWord, explainWord);
            dictionary.addWord(newWord);
        }
    }

    /**
     * Insert word from file
     */
    void insertFormFile() {
        try {
            scan = new Scanner(fileSaveWord);
            String wordTarget;
            String wordExplain;
            String phonetic;
            String content;
            String[] tokens;
            while (scan.hasNextLine()) {
                content = scan.nextLine();
                tokens = content.split("\t");
                wordTarget = nomalize(tokens[0]);
                try {
                    wordExplain = nomalize(tokens[1]);
                    phonetic = tokens[2];

                } catch (Exception e) {
                    System.out.println("No explain word found !");
                    break;
                }

                Word newWord = new Word(wordTarget, wordExplain,phonetic);
                Dictionary.Instance().addWord(newWord);
            }
            Dictionary.Instance().sort(Word.getWordTargetCom());
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!");
        }
    }

    /**
     * nomalize target word and explain word
     */
    public static String nomalize(String str) {
        if (str == "") {
            return "";
        }

        int indexFirstLetter = 0;
        while (str.charAt(indexFirstLetter) == ' ') {
            indexFirstLetter++;
        }
        str = str.substring(indexFirstLetter);

        String firstLetter = str.charAt(0) + "";
        firstLetter = firstLetter.toUpperCase();
        String remanderLetter = str.substring(1);
        remanderLetter = remanderLetter.toLowerCase();

        return firstLetter + remanderLetter;
    }

    /**
     * 0
     * Find word by commandline.
     */
    public Word dictionaryLookUp() {
        String searchTerm;
        scan = new Scanner(System.in);
        System.out.print("Enter the target word:");
        searchTerm = nomalize(scan.nextLine());

        ArrayList<Word> dic = Dictionary.Instance().getDictionary();
        int high = dic.size() - 1;
        int low = 0;

        while (low < high - 1) {
            int indexCheck = (high + low) / 2;
            int valueSearch = searchTerm.compareToIgnoreCase(dic.get(indexCheck).getWordTarget());
            if (valueSearch == 0) {
                return dic.get(indexCheck);
            } else if (valueSearch > 0) {
                low = indexCheck;
                continue;
            } else {
                high = indexCheck;
                continue;
            }
        }

        return null;
    }

    public int[] dictionarySeacher(String findedWord) {
        ArrayList<Word> dic = Dictionary.Instance().getDictionary();
        String searchTerm;
        int[] bound = new int[2];
        bound[0] = 0;
        bound[1] = -1;
        if (findedWord == null) {
            scan = new Scanner(System.in);
            System.out.print("Enter the word to search:");
            searchTerm = scan.nextLine();
        } else if(findedWord.equals("")){
            bound[0] = 0;
            bound[1] = dic.size()-1;
            return bound;
        } else{
            searchTerm = findedWord;
        }
        searchTerm = nomalize(searchTerm);

        int high = dic.size() - 1;
        int low = 0;

        while (low < high) {
            int indexCheck;
            if (low == high - 1) {
                if (dic.get(low).getWordTarget().startsWith(searchTerm)) {
                    indexCheck = low;
                } else {
                    indexCheck = high;
                }
            } else {
                indexCheck = (high + low) / 2;
            }


            int valueSearch = searchTerm.compareToIgnoreCase(dic.get(indexCheck).getWordTarget());
            if (dic.get(indexCheck).getWordTarget().startsWith(searchTerm)) {
                bound[0] = indexCheck;
                bound[1] = indexCheck;
                while (bound[0] >= 0 && dic.get(bound[0]).getWordTarget().startsWith(searchTerm)) {
                    bound[0]--;
                }
                bound[0]++;
                while ((bound[1] < dic.size() ) && dic.get(bound[1]).getWordTarget().startsWith(searchTerm)) {
                    bound[1]++;
                }
                bound[1]--;
                return bound;
            } else if (low == high - 1) {
                return bound;
            } else if (valueSearch > 0) {
                low = indexCheck;
                continue;
            } else {
                high = indexCheck;
                continue;
            }
        }

        return bound;
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
        ArrayList<Word> dic = Dictionary.Instance().getDictionary();


        FileWriter fileWriteWord;
        try{
            fileWriteWord = new FileWriter(fileSaveWord);
            try{
                FileWriter fileWriter = new FileWriter(".\\WordDescription\\adjectiveDescription.txt");
                fileWriter.write("");
                fileWriter.close();
                fileWriter = new FileWriter(".\\WordDescription\\verbDescription.txt");
                fileWriter.write("");
                fileWriter.close();
                fileWriter = new FileWriter(".\\WordDescription\\nounDescription.txt");
                fileWriter.write("");
                fileWriter.close();
                fileWriter = new FileWriter(".\\WordDescription\\adverbDescription.txt");
                fileWriter.write("");
                fileWriter.close();
                fileWriter = new FileWriter(".\\WordDescription\\prepositionDescription.txt");
                fileWriter.write("");
                fileWriter.close();

            }
            catch (Exception e){

            }
            fileWriteWord.write("");
            fileWriteWord.close();
            fileWriteWord = new FileWriter(fileSaveWord,true);

            for(int  i = 0; i < dic.size(); i++){
                Word word = dic.get(i);
                fileWriteWord.write(word.getWordTarget()+"\t"+word.getWordExplain()+"\t"+word.getPhonetic()+"\n");
                ArrayList<WordDescription> des = word.getDescriptions();
                for(int j = 0;j < des.size();j++){
                    des.get(j).exportToFile(word);
                }
            }
            fileWriteWord.close();
        }
        catch (Exception e){
            System.out.println("Error when write data to file!");
        }

        return true;
    }

    boolean writeWordToFile(Word word,String data,String pathFile) {
        File exportFile = new File(pathFile);
        try {

            FileWriter fileWriter;
            fileWriter = new FileWriter(exportFile, true);
            fileWriter.write("\n"+"@" + word.getWordTarget() + "\n" + data + "\n" + "###" + "\n");
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return true;
    }
}
