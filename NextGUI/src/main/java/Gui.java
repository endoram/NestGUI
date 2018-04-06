import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//Incharge of the gui
public class Gui extends Application {

    //Define the vars for labels buttons stages...
    Label label1;
    Button getcode;
    TextField textfieldcode;

    Stage window;
    Scene scene, scene1;

    static GuiDel gd;

    public static void startgui(GuiDel gd){
        Gui.gd = gd;
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Set title to NestGUI
        window = primaryStage;


        Label label1 = new Label("Hey Test");


        Label emtpy = new Label("Code:");
        TextField textfieldcode = new TextField();
        Button getcode = new Button("Activate");

        getcode.setOnAction(e -> {
            String codeinfo = textfieldcode.getText();
            gd.codeEnter(codeinfo);
            window.setScene(scene1);
        });


        GridPane layout1 = new GridPane();
        layout1.setAlignment(Pos.CENTER);
        layout1.add(emtpy, 3, 3);
        layout1.add(textfieldcode, 4, 3);
        layout1.add(getcode, 5, 3);
        scene = new Scene(layout1, 500, 500);



        GridPane grid = new GridPane();
        grid.add(label1, 1, 2);
        scene1 = new Scene(grid, 500, 500);



        window.setScene(scene);
        window.setTitle("NestGUI");
        window.show();
    }
}