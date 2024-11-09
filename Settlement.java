import java.util.ArrayList;

public class Settlement {
    private ArrayList<String> humans;
    private ArrayList<String> stoneHumans;
    private int humanPosition;
    private int stoneHumanPosition;

    public Settlement(){
        this.humans = new ArrayList<String>(2);
        this.humans.add("human");
        this.humans.add("human");
        this.stoneHumans = new ArrayList<String>(2);
        this.stoneHumans.add("stone");
        this.stoneHumans.add("stone");
        this.humanPosition = 0; //start on left side
        this.stoneHumanPosition = 1; //start on right side
    }
   
    public int getHumanPosition(){
        return this.humanPosition;
    }

    public int getStoneHumanPosition(){
        return this.stoneHumanPosition;
    }

    public String attack(boolean guess){ //if the dodo guesses correctly, a human is removed, if they're wrong, a stone is
        if(guess){
            String temp = this.humans.get(0);
            this.humans.remove(0);
            System.out.println("aaaah! A fallen bretheren!");
            return temp; //returns the string at index 0;
        } else{
            String temp = this.stoneHumans.get(0);
            this.stoneHumans.remove(0);
            System.out.println("Oh thank god! We live another day!");
            return temp;
        }
    }
}
