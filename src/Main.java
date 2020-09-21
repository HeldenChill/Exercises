import java.util.Dictionary;

public class Main {
    public static void main(String[] args) {
        DictionaryCommandline dc=DictionaryCommandline.Instance();
        DictionaryManagement dm= DictionaryManagement.Instance();
        dm.insertFromCommandline();
        dc.showAllWord();
    }
}
