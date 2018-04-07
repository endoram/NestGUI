import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import javax.management.StringValueExp;


//Class in charge of the gui also extends class Application
public class Gui extends Application {

    //Define the vars for labels buttons stages...
    Label label1;
    Button getcode;
    TextField textfieldcode;
    static Label ambientTemp1;


    Stage window;
    Scene scene, scene1;

    static GuiDel gd;
    static double ambientTemp;

    public static void startgui(GuiDel gd){
        Gui.gd = gd;
        launch();
    }

    public static void displayNest(NestInformation ti) {
        Gui.ambientTemp = ti.getAmbient();
 //       Gui.ambientTemp1.setLineSpacing(ambientTemp);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;                                                         //Sets the primary stage to the var window
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();       //Gets the boundary for the screen


        //Define vars for layout1
        Label emtpy = new Label("Code:");           //Creates a label var
        TextField textfieldcode = new TextField();       //Creates a empty text field var
        Button getcode = new Button("Activate");    //Creates var for button

        //Button for activating code in text field
        getcode.setOnAction(e -> {
            String codeinfo = textfieldcode.getText();
            gd.codeEnter(codeinfo);
            window.setScene(scene1);
        });

        //This is the first layout, this layout hs you input the code
        GridPane layout1 = new GridPane();                          //Creates first layout as Grid Pane
        layout1.setAlignment(Pos.CENTER);                           //Sets alignment to center
        layout1.add(emtpy, 3, 3);           //Adds empty space
        layout1.add(textfieldcode, 4, 3);   //Adds text field
        layout1.add(getcode, 5, 3);         //Adds button
        scene = new Scene(layout1, 500, 500);          //Sets new Scene



        Label label1 = new Label("Ambient temperature");
        Label ambientTemp1 = new Label("" );

        //Creates the second layout or scene, we use this to easily see what you see
        GridPane grid = new GridPane();                     //Creates a new GridPane layout called grid
        grid.add(label1, 0, 2);         //Adds label 1 to the screen
        grid.add(ambientTemp1, 0, 3);
        scene1 = new Scene(grid, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());    //Creates a new scene and add the grid layout to it



        window.setScene(scene);         //Taking the window var and setting what scene to display
        window.setTitle("NestGUI");     //Sets the title of the window or state
        window.show();                  //Displays window
    }
}