import java.util.Scanner;
import java.util.ArrayList;
public class Adventure {

    //Room currentRoom;
    //UserInterface ui = new UserInterface();
    private Player player;

    public boolean go(String direction) {
        return player.move(direction);
    }

    public Item takeItem(String shortName) {return player.takeItem(shortName);}
    public ArrayList<Item> getInventory() {return player.getInventory();}
    public Item dropItem(String shortName) {return player.dropItem(shortName);}
    public int getHealth() {return player.getHealth();}
    //public Item eat() {return player}
    public EatOutcome eat(String shortName) {
        return player.eat(shortName);
    }
    public EquipResult equip(String shortName) {
        return player.equip(shortName);
    }
    public AttackOutcome attack() {
        return player.attack();
    }
    public AttackOutcome attack(String enemyName){
        return player.attack(enemyName);

    }
    //public Enemy attack() {return player.attack()}
    public Weapon getEquipped() {return player.getEquipped();}
    public void startGame(){
    Map map = new Map();
    map.buildMap();



    player = new Player(map.getStartingRoom());

        }



        public Room getCurrentRoom(){
        return player.getCurrentRoom();
        }
    }

