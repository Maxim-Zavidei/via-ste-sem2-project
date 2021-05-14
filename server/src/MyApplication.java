import javafx.application.Application;
import javafx.stage.Stage;
import mediator.Server;
import model.Model;
import model.ModelManager;

public class MyApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        Model model = new ModelManager();
        try {
            Server server = new Server(model);
        } catch (Exception e) {
            System.err.println("Could not start the server.");
            System.err.println(e.getMessage());
        }
    }
}
