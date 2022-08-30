import javax.sound.midi.*;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent> {

    Button testButton;
    static Text noteDisplay;


    public static void main(String... args) {
        MidiDevice device;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (int i = 0; i < infos.length; i++) {
            try {
                device = MidiSystem.getMidiDevice(infos[i]);
                //does the device have any transmitters?
                //if it does, add it to the device list
                System.out.println(infos[i]);

                //get all transmitters
                List<Transmitter> transmitters = device.getTransmitters();
                //and for each transmitter

                for (int j = 0; j < transmitters.size(); j++) {
                    //create a new receiver
                    transmitters.get(j).setReceiver(new MReceiver(device.getDeviceInfo().toString())
                    );
                }

                Transmitter trans = device.getTransmitter();
                trans.setReceiver(new MReceiver(device.getDeviceInfo().toString()));

                //open each device
                device.open();
                //if code gets this far without throwing an exception
                //print a success message
                System.out.println(device.getDeviceInfo() + " Was Opened");




            } catch (MidiUnavailableException e) {
            }
        }
        launch(args);

    }


    @Override
    public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("Welcome to the Main Stage!");
        testButton = new Button("Dis a button");
        testButton.setOnAction(this); //change handle class
        noteDisplay = new Text("Test");
        noteDisplay.setFont(Font.font("Serif", FontWeight.NORMAL, FontPosture.REGULAR, 150));
        noteDisplay.setFill(Color.WHITE);


        StackPane layout = new StackPane();
        layout.getChildren().add(noteDisplay);


        Scene mainScene = new Scene(layout, 600, 400);
        mainScene.getStylesheets().add("MidiMain.css");
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == testButton) {
            noteDisplay.setText("touched");
        } else if (event.getSource() instanceof Note) {
            noteDisplay.setText(((Note) event.getSource()).getName());
        }
    }

    public static void setNoteDisplay(String note) {
        noteDisplay.setText(note);
    }
}
