import org.junit.Test;
import static org.junit.Assert.*;

public class ChordTest {

    @Test
    public void testConstructMajScale() {
        String[] CMajor = {"C", "D", "E", "F", "G", "A", "B"};
        Note rootC = new Note(0);
        Chord chordC = new Chord(rootC);
        Note[] octScaleC = chordC.constructScale(Chord.MAJOR_OCT_STEPS);
        for (int i = 0; i < octScaleC.length; i++) {
            assertEquals(octScaleC[i].getName(), CMajor[i]);
        }
    }

    @Test
    public void testConstructMinScale() {
        String[] AMinor = {"A", "B", "C", "D", "E", "F", "G"};
        Note rootA = new Note(9);
        Chord chordA = new Chord(rootA);
        Note[] octScaleA = chordA.constructScale(Chord.MINOR_OCT_STEPS);
        for (int i = 0; i < octScaleA.length; i++) {
            assertEquals(octScaleA[i].getName(), AMinor[i]);
        }
    }
}
