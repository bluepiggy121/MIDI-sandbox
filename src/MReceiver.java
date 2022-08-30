import javafx.event.ActionEvent;
import javafx.scene.text.Text;

import javax.sound.midi.*;

import static javax.sound.midi.ShortMessage.NOTE_OFF;
import static javax.sound.midi.ShortMessage.NOTE_ON;

public class MReceiver implements Receiver {

    String _name;

    public MReceiver(String name) {
        _name = name;
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {
        if(message instanceof ShortMessage) {
            ShortMessage sm = (ShortMessage) message;
            int channel = sm.getChannel();

            if (sm.getCommand() == NOTE_ON) {
                int key = sm.getData1();
                int velocity = sm.getData2();
                Note note = new Note(key);
                System.out.println(note);
                Main.setNoteDisplay(note.getName());


            } else if (sm.getCommand() == NOTE_OFF) {
                /*
                int key = sm.getData1();
                int velocity = sm.getData2();
                Note note = new Note(key);
                System.out.println(note);
                 */
            } else {
                System.out.println("Command:" + sm.getCommand());
            }
        }
    }

    public ActionEvent notePressDisplayEvent(Note note, Text display) {
        ActionEvent noteDisplay = new ActionEvent(note, display);
        return noteDisplay;
    }

    @Override
    public void close() {}
}
