public class Map {

    private Room startingRoom;
    public void buildMap() {
        Room room1 = new Room("Room 1", "A narrow cave entrance with rough stone walls and old footprints in the dirt.");
        Room room2 = new Room("Room 2", "A damp tunnel with wooden supports and small goblin tools scattered on the ground.");
        Room room3 = new Room("Room 3", "A dark cave chamber with crude torches and old bones lying near the walls.");
        Room room4 = new Room("Room 4", "A wider tunnel with scratch marks on the walls and the smell of smoke in the air.");
        Room room5 = new Room("Room 5", "A hidden goblin treasure room filled with stolen coins, weapons, and strange glowing stones.");
        Room room6 = new Room("Room 6", "A messy goblin sleeping area with straw beds, blankets, and empty food bowls.");
        Room room7 = new Room("Room 7", "A rough storage cave filled with wooden crates, barrels, and broken equipment.");
        Room room8 = new Room("Room 8", "A large central chamber with a cold campfire and several paths leading deeper into the cave.");
        Room room9 = new Room("Room 9", "A small guard room with crude weapons, wooden shields, and signs that goblins were recently here.");

        // Room 1
        room1.setEast(room2);
        room1.setSouth(room4);
        // Room 2
        room2.setWest(room1);
        room2.setEast(room3);
        // Room 3
        room3.setWest(room2);
        room3.setSouth(room6);
        // Room 4
        room4.setNorth(room1);
        room4.setSouth(room7);
        // Room 5
        room5.setSouth(room8);
        // Room 6
        room6.setNorth(room3);
        room6.setSouth(room9);
        // Room 7
        room7.setNorth(room4);
        room7.setEast(room8);
        // Room 8
        room8.setNorth(room5);
        room8.setWest(room7);
        room8.setEast(room9);
        // Room 9
        room9.setNorth(room6);
        room9.setWest(room8);

        Item lamp = new Item("lamp", "a shiny brass lamp");
        Weapon sword = new MeleeWeapon("sword", "an old rusty sword", 20);
        Weapon bow = new RangedWeapon("bow", "a small archers bow", 15, 5);
        Item key = new Item("key", "a small rusty key");
        Item coin = new Item("coin", "an old gold coin");
        Item helmet = new Item("helmet", "a dented iron helmet");
        Item rope = new Item("rope", "a coil of worn rope");
        Item potion = new Item("potion", "a murky red potion");
        Item bone = new Item("bone", "a cracked human bone");
        Item gem = new Item("gem", "a strange green gemstone");
        Food steak = new Food("steak", "a delicious steak", 60);
        Food apple = new Food("apple", "a red apple", 20);
        Food potato = new Food("potato", "a poisonus looking potato", -30);


        room1.addItem(lamp);
        room1.addItem(sword);
        room1.addItem(bow);
        room1.addItem(steak);
        room1.addItem(apple);
        room1.addItem(coin);
        room2.addItem(potato);
        room2.addItem(bone);


        startingRoom = room1;
    }

    public Room getStartingRoom() {
        return startingRoom;
    }
}
