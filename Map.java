import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    public ArrayList<Settlement> settlements;
    
    public Map(){
        this.settlements = new ArrayList<Settlement>();
        Settlement settlement1 = new Settlement();
        this.settlements.add(settlement1);
    }

    public boolean checkPopulation(int n){
        if ((this.settlements.get(n)).getPopulation() > 0){ // checks to see if the human population is > 0
            return true;
        } else{
            return false;
        }
    }

    public static void main(String[] args) {
        Map battleGround = new Map();
        Dodo killerDodo = new Dodo();
        Scanner input = new Scanner(System.in);
        int guess;
        int currentSettlement = 0;

        while(killerDodo.getHunger() <= 0){
            while(battleGround.checkPopulation(currentSettlement)){ //while there are still humans in a given settlement
                System.out.println("Where do you think the humans are? (0 = left, 1 = right): ");
                guess = input.nextInt();
                if(guess == (battleGround.settlements.get(currentSettlement).getHumanPosition())){
                    killerDodo.grab(battleGround.settlements.get(currentSettlement).attack(true)); //if the dodo guesses correctly, he goes into the current settlement he's in on the map and attacks
                } else{
                    killerDodo.grab(battleGround.settlements.get(currentSettlement).attack(false));
                }
                if(killerDodo.getHunger()<=0){
                    System.out.println("The dodo still has a hunger of..." + killerDodo.getHunger());
                    System.out.println();
                } else{
                    System.out.println("The dodo has been sated");
                }
            }

        }

        input.close();
    }
}
