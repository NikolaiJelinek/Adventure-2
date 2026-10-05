import java.util.Scanner;

public class UserInterface {
    Adventure adventure = new Adventure();
    Scanner userInput = new Scanner(System.in);
    private Player player;
    boolean gameRunning;



    public void cantGoThere(){
        System.out.println("You cannot go this way");
    }
    public void look(){
        Room currentRoom = adventure.getCurrentRoom();
        System.out.println( currentRoom.getName()  + ", " + currentRoom.getDescription());
    }
    public void help(){
        System.out.println("Available commands:\nlook\nhelp\nexit\ntake\ndrop\ninventory\nnorth / south / east / west");
    }
    public void itemTaken(){
        System.out.println("You picked up ");
    }
    public void checkingInventory(){
        System.out.println("Your inventory has: ");
    }
    public void showHealth() {
        int health = adventure.getHealth();

        if (health >= 100) {
            System.out.println("Health: " + health + " - you are in perfect health");
        } else if (health >= 50) {
            System.out.println("Health: " + health + " - you are in good health");
        } else if (health >= 25) {
            System.out.println("Health: " + health + " - you are wounded");
        } else if (health >= 1) {
            System.out.println("Health: " + health + " - you are barely alive");
        } else {
            System.out.println("Health: " + health + " - you should be dead");
        }}


    public void enteringRoom(){
        Room currentRoom = adventure.getCurrentRoom();
        System.out.println("You enter " + currentRoom.getName() + ", " + currentRoom.getDescription());
        for (Item item : currentRoom.getItems()) {
            System.out.println(item.getLongName());
        }
    }
    public void startProgram() {
        System.out.println("Game Starting!");
        adventure.startGame();
        enteringRoom();
        gameRunning = true;

        while (gameRunning) {
            String input = userInput.nextLine();
            System.out.println("Insert command: ");
            switch (input.toLowerCase()) {

                case "exit" -> gameRunning = false;
                case "look" -> look();
                case "help" -> help();
                case "health" -> showHealth();

                case "go north", "north", "n" -> {
                    if (adventure.go("north")) {
                        enteringRoom();
                    } else {
                        cantGoThere();
                    }
                }
                case "go east", "east", "e" -> {
                    if (adventure.go("east")) {
                        enteringRoom();
                    } else {
                        cantGoThere();
                    }
                }
                case "go south", "south", "s" -> {
                    if (adventure.go("south")) {
                        enteringRoom();
                    } else {
                        cantGoThere();
                    }
                }
                case "go west", "west", "w" -> {
                    if (adventure.go("west")) {
                        enteringRoom();
                    } else {
                        cantGoThere();
                    }
                }
                case "inventory", "i" -> {
                    if (!adventure.getInventory().isEmpty()) {
                        checkingInventory();
                        for (Item item : adventure.getInventory()) {
                            System.out.println(item.getLongName());
                        }

                    } else {
                        System.out.println("No items");
                    }
                    Weapon equipped = adventure.getEquipped();
                    if (equipped != null) {
                        System.out.println("Equipped: " + equipped.getLongName());
                    } else {
                        System.out.println("Equipped: nothing");
                    }

                }
                case "attack" -> {//adventure.attack();
                switch (adventure.attack()){
                    case ATTACKED -> {
                        Weapon weapon = adventure.getEquipped();
                        System.out.println("You " + weapon.getAttackVerb() + " " + weapon.getLongName() + " at the empty air. " + weapon.getUsesLeftText());
                    }
                    case NO_WEAPON ->
                            System.out.println("You don't have a weapon equipped.");

                    case OUT_OF_AMMO ->
                            System.out.println("Your weapon is out of ammunition.");
                }
                }

            }
            //String input = userInput.nextLine();
           // String input =  userInput;
            if (input.startsWith("take ")) {
                String itemName = input.substring(5);
                Item pickedUpItem = adventure.takeItem(itemName);
                if (pickedUpItem != null) {
                    System.out.println("You picked up " + pickedUpItem.longName);
                } else {System.out.println("That item is not here.");}
            }
            if (input.startsWith("drop ")) {
                String itemName = input.substring(5);
                Item droppedItem = adventure.dropItem(itemName);
                if (droppedItem != null) {
                    System.out.println("You drop " + droppedItem.longName);

                } else {System.out.println("You don't have that item.");}


            }

            if (input.startsWith("eat ")) {
                String foodToEat = input.substring(4);
                EatOutcome outcome = adventure.eat(foodToEat);

                switch (outcome.getResult()) {

                    case EATEN -> {
                        System.out.println("You eat " + outcome.getItemName());
                        showHealth();
                    }

                    case NOT_FOOD -> {
                        System.out.println(outcome.getItemName() + " is not food.");
                    }

                    case NOT_FOUND -> {
                        System.out.println("You cannot find that item.");
                    }
                }
            }

            if (input.startsWith("equip ")) {
                String equippedItem = input.substring(6);


                switch (adventure.equip(equippedItem)) {

                    case EQUIPPED -> { System.out.println("EQUiPPED");}

                    case NOT_FOUND -> {System.out.println("NOT FOUND");}

                    case NOT_WEAPON -> {System.out.println("NOT WEAPON");}
                }
            }


        }


    }

}