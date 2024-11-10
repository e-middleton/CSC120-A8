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
        this.tools[2] = "sleeping bag";
    }

    public int numSettlements(){
        return this.settlements.size();
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

    public void walk(Dodo killerDodo, Scanner input, int currentSettlement){
        System.out.println("Where do you think the humans are? 'left'/'right': ");
        String dodoDirection = null;

        try{ 
            dodoDirection = input.nextLine(); //Dodo is able to walk left or right, walk() function returns true for left, false for right
        } catch(RuntimeException e){
            System.out.println(e.getMessage());
            dodoDirection = input.nextLine(); //Exception isn't being handled?
        }

        //gets the string "human" or "stone" depending on if the Dodo guessed correctly or incorrectly 
        String spoilsOfWar = this.settlements.get(currentSettlement).attack(this.guess(killerDodo.walk(dodoDirection), currentSettlement));
            
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


    public static void main(String[] args) {
        Map battleGround = new Map();
        Dodo killerDodo = new Dodo();
        Scanner input = new Scanner(System.in);
        Random chance = new Random(); //every once in awhile, the dodo can get lucky and get a special tool
        int currentSettlement = killerDodo.getPosition();
        String action = "begin"; //to start out with

        System.out.println("Global warming has given rise to a new species of dodo... a hungry one.");
        System.out.println("If you, the dodo, can reach a size of 3, you win and humanity loses earth to its dodo overlord");
        System.out.println("You have a beginning size of: " + killerDodo.getSize());
        System.out.println("will you play? yes/no: ('end' to end the game)");
        action = input.nextLine();

        //as long as the dodo is still hungry, they keep trying to find the people
        outerloop:
        while(action != "end" || action != "no"){
            //if the randomized number is 7, the dodo gets a special tool, right now it's always heat goggles and it gets to eat an extra person
            if(chance.nextInt(10) == 7){
                System.out.println();
                System.out.println("but wait....");
                System.out.println();
                System.out.println("The dodo gods have smiled upon you, it is your lucky day and you have found heat vision goggles...");
                System.out.println("You get to eat and extra person, regardless of your next action.");
                killerDodo.use(battleGround.tools[0]); //dodo uses the tool
                System.out.println();
                } else if(chance.nextInt(10) == 2){
                    System.out.println();
                    System.out.println("but wait....");
                    System.out.println();
                    System.out.println("The dodo gods have smiled upon you, you have been given a sleeping bag...");
                    System.out.println("You get to rest and gain an energy/hunger point, regardless of your next action.");
                    killerDodo.grab(battleGround.tools[2]); //dodo grabs the sleeping bag
                    killerDodo.rest(); //and takes a nap
                    System.out.println();
                }

            System.out.println();
            System.out.println("Your options for dodo actions are ");
            killerDodo.showOptions();
            action = input.nextLine();

            while(battleGround.checkPopulation(currentSettlement)){ //game continues while there are still humans in a given settlement
                if(action.equals("walk")){
                    battleGround.walk(killerDodo, input, currentSettlement);
                } else if(action.equals("drop")){
                    System.out.println("What item would you like to drop? ");
                    String item = input.nextLine();
                    try{
                        killerDodo.drop(item);
                    } catch(RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                } else if(action.equals("fly")){
                    if(currentSettlement < battleGround.numSettlements()){ //if they aren't at the last settlement yet, they can fly to the next
                        killerDodo.fly(killerDodo.getPosition(), (killerDodo.getPosition() + 1));
                    } else {
                        System.out.println("At the last settlement, cannot go onward.");
                    }
                } else if(action.equals("grow")){
                    killerDodo.grow();
                    System.out.println("You are now a size of: " + killerDodo.getSize());
                    if(killerDodo.getSize() >= 3){
                        System.out.println("You've reached the max size, you win!");
                        break outerloop;
                    }

                } else if(action.equals("undo")){ //tries go go back, will hopefully catch the exception if they are trying to back up from zero
                    try{
                        killerDodo.undo();
                    } catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                } else{
                    System.out.println("Please enter a valid action: 'walk' 'fly' 'drop' or 'grow'.");
                }
            }
            if(killerDodo.getSize() >= 3){
                System.out.println("You've reached the max size, you win!");
                break;
            }
        }

        input.close();
    }
}
