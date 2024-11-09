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

    public int getHunger(){
        return this.hunger;
    }
    /**
     * 
     * @param item the thing being grabbed
     */
    public void grab(String item){
        this.possessions.add(item); //indescriminately grabs items, doesn't check them
    }


    public String drop(String item){
        if(this.possessions.contains(item)){
            this.possessions.remove(item);
            return item;
        } else{
            throw new RuntimeException(item + " cannot be dropped, it was never grabbed.");
        }
    }

    //dodo must examine the thing it catches, is it human and edible? Or is it inedible, like a rock human
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
