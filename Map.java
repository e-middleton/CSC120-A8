import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    public ArrayList<Settlement> settlements;
    
    public Map(){
        this.settlements = new ArrayList<Settlement>();
        Settlement settlement1 = new Settlement();
        this.settlements.add(settlement1);
    }

    public static void main(String[] args) {
        Map battleGround = new Map();
        Dodo killerDodo = new Dodo();
        Scanner input = new Scanner(System.in);
        int guess;
        int currentSettlement = 0;

        while(killerDodo.getHunger() == -1){
            System.out.println("Where do you think the humans are? (0 = left, 1 = right): ");
            guess = input.nextInt();
            if(guess == (battleGround.settlements.get(currentSettlement).getHumanPosition())){
                killerDodo.grab(battleGround.settlements.get(currentSettlement).attack(true)); //if the dodo guesses correctly, he goes into the current settlement he's in on the map and attacks
            } else{
                killerDodo.grab(battleGround.settlements.get(currentSettlement).attack(false));
            }

        }

        input.close();
    }
}
