import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

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
            String content;
            String[] tokens;
            while (scan.hasNextLine()) {
                content = scan.nextLine();
                tokens = content.split("\t");
                wordTarget = nomalize(tokens[0]);

                try {
                    wordExplain = nomalize(tokens[1]);

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
     * nomalize target word and explain word
     */
    public static String nomalize(String str){
        int indexFirstLetter = 0;
        while(str.charAt(indexFirstLetter) == ' '){
            indexFirstLetter++;
        }
        str = str.substring(indexFirstLetter);

        String firstLetter = str.charAt(0)+"";
        firstLetter = firstLetter.toUpperCase();
        String remanderLetter = str.substring(1);
        remanderLetter = remanderLetter.toLowerCase();

        return firstLetter+remanderLetter;
    }

    /**0
     * Find word by commandline.
     */
    public Word dictionaryLookUp() {
        String searchTerm;
        scan = new Scanner(System.in);
        System.out.print("Enter the target word:");
        searchTerm = nomalize(scan.nextLine());

        ArrayList<Word> dic = Dictionary.Instance().getDictionary();
        int high = dic.size()-1;
        int low = 0;

        while(low<high-1){
            int indexCheck = (high+low)/2;
            int valueSearch = searchTerm.compareToIgnoreCase(dic.get(indexCheck).getWordTarget());
            if(valueSearch == 0){
                return dic.get(indexCheck);
            }
            else if(valueSearch > 0){
                low = indexCheck;
                continue;
            }
            else{
                high = indexCheck;
                continue;
            }
        }

        return null;
    }

    public int[] dictionarySeacher(){
        String searchTerm;
        int[] bound = new int[2];
        scan = new Scanner(System.in);
        System.out.print("Enter the word to search:");
        searchTerm = nomalize(scan.nextLine());

        ArrayList<Word> dic = Dictionary.Instance().getDictionary();
        int high = dic.size()-1;
        int low = 0;

        while(low<high-1){
            int indexCheck = (high+low)/2;
            int valueSearch = searchTerm.compareToIgnoreCase(dic.get(indexCheck).getWordTarget());
            if(dic.get(indexCheck).getWordTarget().startsWith(searchTerm)){
                bound[0]=indexCheck;
                bound[1]=indexCheck;
                while(bound[0]>=0 && dic.get(bound[0]).getWordTarget().startsWith(searchTerm)){
                    bound[0]--;
                }
                bound[0]++;
                while((bound[1]<dic.size()-1) && dic.get(bound[1]).getWordTarget().startsWith(searchTerm)){
                    bound[1]++;
                }
                bound[1]--;
                return bound;
            }
            else if(valueSearch > 0){
                low = indexCheck;
                continue;
            }
            else{
                high = indexCheck;
                continue;
            }
        }

        return null;
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
        File exportFile = new File("exportFile.txt");
        if(exportFile.exists()){
            if(exportFile.delete()){
                System.out.println("Delete file!");
            }
            else{
                System.out.println("Can't delete file!");
            }
        }
        try{
            if(exportFile.createNewFile()){
                FileWriter fileWriter = new FileWriter(exportFile);
                for(int i=0;i<dic.size();i++){
                    Word word = dic.get(i);
                    fileWriter.write(word.getWordTarget()+"\t"+word.getWordExplain()+"\n");
                }
                fileWriter.close();
                System.out.println("Successfully wrote to the file.");
            }
        }
        catch (IOException e){
            System.out.println("Can't create new file");
        }
        return true;
    }
}
