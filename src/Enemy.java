public class Enemy {
    String shortName;
    String longName;
    String description;
    int health;


    Enemy(String shortName, String longName, int health){
        this.shortName = shortName;
        this.longName = longName;
        this.health = health;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }
    public void takeDamage(int damage){
        health -= damage;
        int damageTaken = damage;

    }
    public boolean isDead(){
        return health <= 0;
    }

    public String getDescription() {
        return description;
    }

    public int getHealth() {
        return health;
    }
}
