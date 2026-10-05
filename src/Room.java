import java.util.ArrayList;

public class Room {
    String name;
    String description;

    ArrayList<Item> items = new ArrayList<>();
    ArrayList<Item> getItems(){
        return items;
    }


    Room north;
    Room east;
    Room south;
    Room west;

    Room(String name, String description){
        this.name = name;
        this.description = description;
    }
    public void addItem(Item item){items.add(item);}

    public void removeItem(Item item){
        items.remove(item);
    }

    public Item findItem(String shortName) {
        for (Item item : items) {
            if (item.getShortName().equals(shortName)) {
                return item;
            }
        }
        return null;
    }


    public String getName() {return name;}
    public String getDescription() {return description;    }

    public void setNorth(Room north) {this.north = north;}
    public void setEast(Room east) {this.east = east;}
    public void setSouth(Room south) {this.south = south;}
    public void setWest(Room west) {this.west = west;}

    public Room getNorth() {return north;}
    public Room getEast() {return east;}
    public Room getSouth() {return south;}
    public Room getWest() {return west;}


}
