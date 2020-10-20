public class Main {
    public static void main(String[] args)  {

        /*Audio audio = Audio.getInstance();
        try{
            InputStream sound = audio.getAudio("Hello", Language.ENGLISH);
            audio.play(sound);
        }
        catch (Exception e){
            System.out.println("Error");
        }*/




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
