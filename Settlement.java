import java.util.ArrayList;
import java.util.Random;

public class Settlement {
    private ArrayList<String> humans;
    private String humanPosition;
    private String stoneHumanPosition;

    public Settlement(){
        this.humans = new ArrayList<String>(2);
        this.humans.add("human");
        this.humans.add("human");
        this.humanPosition = "left"; //start on left side
        this.stoneHumanPosition = "right"; //start on right side
    }
   
    public String getHumanPosition(){
        return this.humanPosition;
    }

    public int getPopulation(){
        return this.humans.size();
    }

    public String getStoneHumanPosition(){
        return this.stoneHumanPosition;
    }

    public String attack(boolean guess){ //if the dodo guesses correctly, a human is removed, if they're wrong, a stone is
        swapPlace();
        if(guess){
            String temp = this.humans.get(0);
            this.humans.remove(0);
            System.out.println("aaaah! Human eaten!");
            return temp; //returns the string at index 0;
        } else{
            System.out.println("You got a ... stone!");
            return "stone";
        }
    }

    //randomizes human and stone positions, only called by dodo attacks, if the human is right, stones are left and vice versa
    public void swapPlace(){
        Random rand = new Random(); //gets us a randomized position for people/stones positions, 0 = left, 1 = right for humans
        if(rand.nextInt(2) == 0){
            this.humanPosition = "left";
            this.stoneHumanPosition = "right";
        } else{
            this.humanPosition = "right";
            this.stoneHumanPosition = "left";
        }
    }

    public static void main(String[] args) {
        Settlement home = new Settlement();
        System.out.println(home.getHumanPosition());
        home.swapPlace();
        System.out.println(home.getHumanPosition());

    }

}
