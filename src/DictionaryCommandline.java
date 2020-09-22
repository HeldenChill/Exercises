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
    void dictionarySearcher() {

    }
}
