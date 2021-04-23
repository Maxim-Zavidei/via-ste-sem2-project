import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        Model model = new ModelManager();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        try {
            viewHandler.start(primaryStage);
        } catch (Exception e) {
            System.err.println("Couldn't start the application.");
            System.err.println(e.getMessage());
        }
    }
}
