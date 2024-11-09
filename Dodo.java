import java.util.ArrayList;

public class Dodo{
    private int hunger;
    public ArrayList<String> possessions;

    public Dodo(){
        this.hunger = -1; //a Dodo is born... hungry
        possessions = new ArrayList<String>(); //initalizes empty array list
    }

    // boolean fly(int x, int y);
    // Number shrink();
    // Number grow();
    // void rest();
    // void undo();

    /**
     * getter for the hunger level of the dodo
     * @return int for hunger level, which initalizes at -1, hungry
     */
    public int getHunger(){
        return this.hunger;
    }

    /**
     * method for grabbing something, it doesn't check anything it grabs, so it could be either stones or humans
     * @param item the thing being grabbed
     */
    public void grab(String item){
        this.possessions.add(item); 
    }


    /**
     * method for dropping something from the dodo's list of possessions, usually when the dodo examines it and finds out it's a stone
     * @param item the item being dropped from the dodo's inventory
     * @return the item being dropped
     */
    public String drop(String item){
        if(this.possessions.contains(item)){
            this.possessions.remove(item);
            return item;
        } else{
            throw new RuntimeException(item + " cannot be dropped, it was never grabbed.");
        }
    }

    /**
     * in order to eat, the dodo has to check if what it grabbed is a human, and edible, or a rock, and inedible. If it is a human, the dodo automatically eats it.
     * @param item the item the dodo is checking
     * @return t/f it's a human
     */
    public boolean examine(String item){
        if(item.equals("human")){
            this.hunger += 1; //if it's a human, it is eaten
            return true;
        } else{
            drop(item); //drop the item, probably a stone in rage
            return false;
        }
    }

    /**
     * Function that makes the dodo walk
     * @param direction the direction the dodo is walking in, left or right
     * @return t/f true for walking left, false for walking right
     */
    public boolean walk(String direction){
        if(direction.equals("left")){
            return true;
        } else if(direction.equals("right")){
          return false;
        } else{
            throw new RuntimeException("Invalid input, must enter 'left' or 'right'.");
        }
    }

    public void use(String item){
        if(item.equals("infrared goggles")){
            hunger += 1; //the infrared goggles allow you to eat a person without grabbing and checking, you can see it's not a stone from afar
        } else{
            throw new RuntimeException("I didn't plan for any other tools? What on earth have you given the dodo?");
        }
    }
    

}
