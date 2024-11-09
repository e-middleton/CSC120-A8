public interface Contract {

    void grab(String item);
    String drop(String item);
    void examine(String item);
    void use(String item);
    boolean walk(String direction);
    boolean fly(int x, int y);
    Number shrink();
    Number grow();
    void rest();
    void undo();

    //killer Dodo, lesser known geologic extinction event
    //good and bad Dodos, killer dodos and minions
    //maybe undo is for disbanding an army?

}