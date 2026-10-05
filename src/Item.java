public class Item {
    String shortName;
    String longName;

    Item(String shortName, String longName){
        this.shortName = shortName;
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

}
