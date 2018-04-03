import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Gui extends Application {

    Label label1;
    Button getcode;
    TextField textfieldcode;

    Stage Window;
    Scene scene, scene1;

    static GuiDel gd;

    public static void startgui(GuiDel gd){
        Gui.gd = gd;
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("NestGUI");
        Window = primaryStage;

        Label label1 = new Label("Hey Test");

        TextField textfieldcode = new TextField();


        Button getcode = new Button("Activate");
        getcode.setOnAction(e -> {
            String codeinfo = textfieldcode.getText();
            gd.codeEnter(codeinfo);

       //     Window.setScene(scene1);
        });



        //Layout1   Get authentication code
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));


        grid.add(getcode, 1, 3);
        grid.add(textfieldcode, 1, 2);


        Scene scene = new Scene(grid, 500,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}