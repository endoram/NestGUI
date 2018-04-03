import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Gui extends Application {

    Label label1;


    public static void startgui(){
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("NestGUI");

        Label label1 = new Label("Hey Test");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(label1, 1, 2);

        Scene scene = new Scene(grid, 500,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}