import java.util.Dictionary;

public class Main {
    public static void main(String[] args) {
        DictionaryCommandline dc=DictionaryCommandline.Instance();
        DictionaryManagement dm= DictionaryManagement.Instance();
        dm.insertFormFile();
        dc.showAllWord();
        String s1 = "Compatible";
        String s2 = "Constrain";
        String s3 = "Conversion";
        System.out.println(DictionaryManagement.nomalize("     hello WoRlD"));
    }
}
