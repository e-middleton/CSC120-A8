import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Map {
    public ArrayList<Settlement> settlements;
    public String[] tools;
    
    public Map(){
        this.settlements = new ArrayList<Settlement>();
        Settlement settlement1 = new Settlement();
        this.settlements.add(settlement1);
        
        this.tools = new String[3];
        this.tools[0] = "infrared goggles";
        this.tools[1] = "extra large mouth";
        this.tools[2] = "whoops! you lose a turn";
    }

    /**
     * Checks to see if the human population of a given settlement is greater than 0
     * @param n the number/index of the settlement being checked
     * @return true/false there is a human left living
     */
    public boolean checkPopulation(int n){
        if ((this.settlements.get(n)).getPopulation() > 0){ // checks to see if the human population is > 0
            return true;
        } else{
            return false;
        }
    }

    /**
     * 
     * @param direction which direction the dodo walks in t: left, f: right
     * @param location the current settlement in the arrayList the battle is in
     * @return t/f if the dodo guessed the correct side where the humans are
     */
    public boolean guess(boolean direction, int location){
        if(direction){ //direction that the dodo walks, left = true, right = false
            if(this.settlements.get(location).getHumanPosition().equals("left")){ //if the humans are also on the left, the dodo guessed correctly
                return true;
            } else{ //but if the humans are on the right, the dodo has guessed wrong
                return false;
            }
        } else{
            if(this.settlements.get(location).getHumanPosition().equals("right")){
                return true;
            } else{
                return false;
            }
        }
    }

    public static void main(String[] args) {
        Map battleGround = new Map();
        Dodo killerDodo = new Dodo();
        Scanner input = new Scanner(System.in);
        Random chance = new Random(); //every once in awhile, the dodo can get lucky and get a special tool
        int currentSettlement = 0;

        //as long as the dodo is still hungry, they keep trying to find the people
        while(killerDodo.getHunger() <= 0){
            while(battleGround.checkPopulation(currentSettlement)){ //game continues while there are still humans in a given settlement
                System.out.println("Where do you think the humans are? 'left'/'right': ");
                String dodoDirection = input.nextLine(); //Dodo is able to walk left or right, walk() function returns true for left, false for right

                //if the randomized number is 7, the dodo gets a special tool, right now it's always heat goggles and it gets to eat an extra person
                if(chance.nextInt(10) == 7){
                    System.out.println();
                    System.out.println("but wait....");
                    System.out.println();
                    System.out.println("The dodo gods have smiled upon you, it is your lucky day and you have found heat vision goggles...");
                    System.out.println("You get to eat and extra person, regardless of if you have guessed incorrectly.");
                    killerDodo.use(battleGround.tools[0]); //dodo uses the tool
                    System.out.println();

                }

                //gets the string "human" or "stone" depending on if the Dodo guessed correctly or incorrectly 
                String spoilsOfWar = battleGround.settlements.get(currentSettlement).attack(battleGround.guess(killerDodo.walk(dodoDirection), currentSettlement));
            
                //either way the dodo grabs the stone/human before checking it with .examine()
                killerDodo.grab(spoilsOfWar);
                killerDodo.examine(spoilsOfWar);

               if(killerDodo.getHunger()<=0){
                    System.out.println("The dodo is still hungry!");
                    System.out.println();
               } else{
                    System.out.println("The dodo has been sated");
                }
                System.out.println();
            }

        }

        input.close();
    }
}
