import java.util.ArrayList;

public class Player {

    int health = 100;

    private Room currentRoom;
    private ArrayList<Item> inventory = new ArrayList<>();

    private Weapon equipped;


    public Player(Room startingRoom) {
        currentRoom = startingRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public void addItem(Item item){
        inventory.add(item);
    }
    public void removeItem(Item item){
        inventory.remove(item);
    }
    public Item findItem(String shortName){
        for (Item item : inventory) {
            if (item.getShortName().equals(shortName)){
                return item;
            }
        }
        return null;
    }
    public Item takeItem(String shortName){
        Item item = currentRoom.findItem(shortName);

        if (item != null) {
            currentRoom.removeItem(item);
            addItem(item);
            return item;
        }

        return null;
    }

    public Item dropItem(String shortName){
        Item item = findItem(shortName);

        if (item != null) {
            removeItem(item);
            currentRoom.addItem(item);
            if(item == equipped){
                equipped = null;
            }
            return item;
        }

        return null;
    }
    public EatOutcome eat(String shortName) {
        boolean itemWasInInventory = true;
        Item item = findItem(shortName);
        if (item == null) {
            item = currentRoom.findItem(shortName);
            itemWasInInventory = false;
            if (item == null) {
                return new EatOutcome(EatResult.NOT_FOUND, null, 0);
            }
        }
        if (!(item instanceof Food) ) {
            return new EatOutcome(EatResult.NOT_FOOD, item.longName, 0);
        }

        if(itemWasInInventory){
            removeItem(item);
        } else {
            currentRoom.removeItem(item);
        }
        Food food = (Food) item;
        int healthChange = food.getHealthPoints();
        health += healthChange;

        return new EatOutcome(EatResult.EATEN, item.longName, ((Food) item).getHealthPoints());
    }

    public int getHealth() {
        return health;
    }
    public Weapon getEquipped(){
        return equipped;
    }
    public AttackResult attack() {
        if (equipped == null) {

            return AttackResult.NO_WEAPON;
        }
        if (equipped.canUse()) {
            equipped.use();
            return AttackResult.ATTACKED;
        } else {
            return AttackResult.OUT_OF_AMMO;
        }
    }

    public EquipResult equip(String shortName) {
        //boolean weaponWasInInventory = true;
        Item item = findItem(shortName);
        if (item == null) {
            return EquipResult.NOT_FOUND;
        }
        if (!(item instanceof Weapon)) {
            return EquipResult.NOT_WEAPON;
        } else {
            equipped = (Weapon) item;
            return EquipResult.EQUIPPED;
        }
    }
    public boolean move(String direction) {

        Room desiredRoom = switch (direction) {
            case "north" -> currentRoom.getNorth();
            case "south" -> currentRoom.getSouth();
            case "east"  -> currentRoom.getEast();
            case "west"  -> currentRoom.getWest();
            default -> null;
        };

        if (desiredRoom != null) {
            currentRoom = desiredRoom;
            return true;
        }
        else {
            return false;
        }
    }





}
