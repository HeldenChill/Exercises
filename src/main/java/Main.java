public class Main {
    public static void main(String[] args)  {



        DictionaryApplication application = new DictionaryApplication();
        DictionaryCommandline dc=DictionaryCommandline.Instance();
        DictionaryManagement dm= DictionaryManagement.Instance();
        dm.insertFormFile();
        //dc.dictionaryBasic();
        //dc.dictionarySearcher();

        WordManagement.Instance().insertDescriptionFromFile();
        application.startApplication(args);

        dm.dictionaryExportToFile();
    }
}
