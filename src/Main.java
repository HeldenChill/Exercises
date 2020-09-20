public class Main {
    public static void main(String[] args) {
        DictionaryManagement dm= DictionaryManagement.Instance();
        dm.insertFromCommandline();
        System.out.print(dm.dictionary);
    }
}
