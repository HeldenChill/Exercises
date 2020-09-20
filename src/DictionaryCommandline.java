public class DictionaryCommandline {
    DictionaryCommandline inst = null;

    private DictionaryCommandline() {
    }

    public DictionaryCommandline Instance() {
        if (inst == null) {
            inst = new DictionaryCommandline();
        }
        return inst;
    }

    /**
     * Show all word target and its correspondence word explain.
     */
    void showAllWord() {

    }

    /**
     * Can call to showAllWord() and insertFromCommandline().
     */
    void dictionaryBasic() {

    }

    /**
     * Can call to showAllWord(),insertFromFile and dictionaryLookUp().
     */
    void dictionaryAdvance() {

    }

    /**
     * Find and recommend word target or explain when type each letter once
     */
    void dictionarySearcher() {

    }
}
