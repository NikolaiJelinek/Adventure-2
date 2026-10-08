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
    // attack method without target
    public AttackOutcome attack(){
        Enemy enemy = null;
        if(!currentRoom.getEnemies().isEmpty()){
            enemy = currentRoom.getEnemies().get(0);
            System.out.println(enemy.getShortName()); }
            if (equipped == null) {
            return new AttackOutcome(AttackResult.NO_WEAPON, null, 0, 0);
        }
        if (equipped.canUse()) {
            equipped.use();

            int damage = equipped.getDamage();
           enemy.takeDamage(damage);
            return new AttackOutcome(AttackResult.ATTACKED, enemy.getShortName(), damage, enemy.getHealth());}
        else {
            return new AttackOutcome(AttackResult.OUT_OF_AMMO, null, 0, 0);
        }
    }
    // attack method with target
    public AttackOutcome attack(String enemyName) {
        Enemy enemy = currentRoom.findEnemy(enemyName);
        if (enemy == null){
            return new AttackOutcome(AttackResult.NO_ENEMY, null, 0, 0);
        }

        if (equipped == null) {

            return new AttackOutcome(AttackResult.NO_WEAPON, null, 0, 0);
        }
        if (equipped.canUse()) {
            equipped.use();
            int damage = equipped.getDamage();
            enemy.takeDamage(damage);
            if(enemy.isDead()){
                currentRoom.removeEnemy(enemy);
                return new AttackOutcome(AttackResult.ENEMY_DEAD, enemy.getShortName(), damage, 0);
            }
            return new AttackOutcome(AttackResult.ATTACKED, enemy.getShortName(), damage, enemy.getHealth());
        } else {

            return new AttackOutcome(AttackResult.OUT_OF_AMMO, null, 0, 0);
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
