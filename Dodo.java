
public class Dodo{
    private int hunger;

    public Dodo(){
        this.hunger = -1; //a Dodo is born... hungry
    }


    public int getHunger(){
        return this.hunger;
    }
    /**
     * Function for grabbing an item need to make it make sense
     * @param item the thing being grabbed and or eaten
     */
    public void grab(String item){
        if(this.examine(item)){
            this.hunger += 1;
        } 
    }


    // public String drop(String item){
    //     if(this.possessions.contains(item)){
    //         this.possessions.remove(item);
    //         return item;
    //     } else{
    //         throw new RuntimeException(item + " cannot be dropped, it was never grabbed.");
    //     }
    // }

    //dodo must examine the thing it catches, is it human and edible? Or is it inedible, like a rock human
    public boolean examine(String item){
        if(item.equals("human")){
            return true;
        } else{
            System.out.println("SQUAAAAAAAAAK");
            return false;
        }
    }

    // public boolean walk(String direction){
    //     if(direction.equals("left")){
    //         if(this.position != 0){ //if the Dodo isn't already on the left side of a settlement
    //             this.position = 0;
    //             return true;
    //         } else{
    //             throw new RuntimeException("Already on the left side of the settlement!");
    //         }
    //     } else if(direction.equals("right")){
    //         if(this.position != 1){
    //             this.position = 1;
    //             return true;
    //         } else {
    //             throw new RuntimeException("Already on the right side of the settlement");
    //         }
    //     } else{
    //         throw new RuntimeException("Invalid input, must enter 'left' or 'right'.");
    //     }
    // }
    

}
