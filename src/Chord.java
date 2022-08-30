public class Chord {

    public static final int[] MAJOR_OCT_STEPS = {2, 2, 1, 2, 2, 2, 1};
    public static final int[] MINOR_OCT_STEPS = {2, 1, 2, 2, 1, 2, 2};

    private Note root;
    private Note[] scale;

    public Chord(Note root) {
        this.root = root;
        scale = constructScale(MAJOR_OCT_STEPS);
    }

    protected Note[] constructScale(int[] mode) {
        Note[] octScale = new Note[7];
        int keyStepper = root.getKey();
        for (int i = 0; i < octScale.length; i++) {
            octScale[i] = new Note(keyStepper);
            keyStepper += mode[i];
        }
        return octScale;
    }

}
