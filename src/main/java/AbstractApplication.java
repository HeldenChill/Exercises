import javafx.stage.Stage;

public abstract class AbstractApplication {
    protected Stage app = null;
    public Stage getApplication() throws Exception{
        return app;
    }

    public void runAfterShow() throws Exception{

    }
}
