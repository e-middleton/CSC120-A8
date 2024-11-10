import java.util.ArrayList;

public class Dodo implements Contract{
    private int hunger;
    private int size;
    public ArrayList<String> possessions;
    private int position;

    public Dodo(){
        this.hunger = -1; //a Dodo is born... hungry
        this.size = 1; //normal dodo size
        possessions = new ArrayList<String>(); //initalizes empty array list
        this.position = 0; //dodo begins in quadrant/settlement 0
    }

    public boolean fly(int x, int y){
        if(x == this.position && y == (x+1)){ //you can only fly forward to the next quadrant
            if(this.hunger >= 0){
                this.position = y;
                return true;
            } else{
                throw new RuntimeException("Not enough energy to fly, must eat a person before moving on.");
            }
        } else{
            throw new RuntimeException("Cannot fly to a settlement unless it is the one nearby.");
        }
    }

    //fly back to the quadrant you just came from, doesn't require any energy
    public void undo(){
        if(this.position >= 0){
            this.position -= 1;
        }
    }

    public int getPosition(){
        return this.position;
    }

    public int getSize(){
        return this.size;
    }

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
    public void examine(String item){
        if(item.equals("human")){
            this.hunger += 1; //if it's a human, it is eaten
        } else{
            drop(item); //drop the item, probably a stone in rage
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

    public Number shrink(){
        if(this.hunger < -1){
            this.size -= 1; //if the dodo gets too hungry, it shrinks, called by flying
            return this.size;
        } else{
            return this.size; //otherwise it doesn't shrink 
        }
    }

    /**
     * Function that causes the dodo to grow if it has enough energy/can eat enough people for energy, either it eats people from its inventory and grows,
     * or it already has sufficient energy and uses that to grow, but if it doesn't have enough, the size doesn't change.
     * @return the size of the dodo
     */
    public Number grow(){
        if(this.possessions.contains("human")){ //if the dodo has a human in store, it eats it for energy
            int snack = (this.possessions.indexOf("human")); 
            examine(this.possessions.get(snack));
            drop(this.possessions.get(snack)); 
            if(this.possessions.contains("human")){ //if there is a second human, it is also eaten for energy (2 hunger/energy points needed to grow)
                int snack2 = (this.possessions.indexOf("human")); 
                examine(this.possessions.get(snack2));
                drop(this.possessions.get(snack2));
                this.hunger -= 2;
                this.size += 1;
            } else{
                if(this.hunger >= 1){
                    this.hunger -=2 ;
                    this.size += 1;
                }
            }
        } else{ 
            if(this.hunger >=1 ){ //if the dodo has enough energy already from past eaten people, it can also grow
                this.hunger -= 2; //energy is used
                this.size += 1;
            } else{
                System.out.println("Not enough energy or people to eat, can't grow.");
            }
        }
        return this.size;
    }

    public void rest(){
        if(this.possessions.contains("sleeping bag")){
            drop("sleeping bag"); //gets rid of the sleeping bag from inventory because it's been used
            this.hunger += 1; //the same effect as eating a person, resting gives the dodo energy
        } else{
            System.out.println("Unable to rest, no cozy blanket!");
        }
    }

    public void showOptions(){
        System.out.println("The options for dodo actions are: \n + 'walk' - this tries to guess where the humans are in a settlement \n + 'fly' - moves to a new settlement from the old one \n + 'grow' - increases dodo size, but only if you have 2 humans in possession to eat for energy \n + 'drop' - removes an item from possession");
    }

    public static void main(String[] args) {
        Dodo dodo = new Dodo();
        dodo.showOptions();
    }
    

}
