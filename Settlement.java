import java.util.ArrayList;
import java.util.Random;

/**
 * Settlement class, contains array list of human population and methods for returning either a stone or human when attacked
 */
public class Settlement {
    private ArrayList<String> humans;
    private String humanPosition;

    /**
     * default constructor initializes settlements wtih two humans, starting on the left side of the settlement
     */
    public Settlement(){
        this.humans = new ArrayList<String>(2);
        this.humans.add("human");
        this.humans.add("human");
        this.humanPosition = "left"; //start on left side
    }
   
    /**
     * getter for the human position
     * @return string "left" or "right" depending on the randomized location
     */
    public String getHumanPosition(){
        return this.humanPosition;
    }

    /**
     * getter for the population of a settlement
     * @return int for the size of the array list of humans, aka the population
     */
    public int getPopulation(){
        return this.humans.size();
    }


    /**
     * method for when the dodo has guessed the location, it either removes a human from the population, or it sends the dodo a rock, depending on the guess
     * @param guess t/f whether or not the dodo knows where the humans are
     * @return string "human" or "stone" depending on which the dodo is grabbing
     */
    public String attack(boolean guess){ //if the dodo guesses correctly, a human is removed, if they're wrong, a stone is
        swapPlace(); //after every attack the humans consider swapping position or staying in the same place
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

    /**
     * usually called by attack(), this method randomizes the position of the humans within a settlement, either to the left (0) or right (1)
     */
    public void swapPlace(){
        Random rand = new Random(); //gets us a randomized position for people positions, 0 = left, 1 = right for humans, stones are wherever humans aren't
        if(rand.nextInt(2) == 0){
            this.humanPosition = "left";
        } else{
            this.humanPosition = "right";
        }
    }

    /**
     * main method, used for testing
     * @param args empty array of strings
     */
    public static void main(String[] args) {
        Settlement home = new Settlement();
        System.out.println(home.getHumanPosition());
        home.swapPlace();
        System.out.println(home.getHumanPosition());

    }

}
