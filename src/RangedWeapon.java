public class RangedWeapon extends Weapon {

    int ammunition;

    public RangedWeapon(String shortName, String longName, int damage, int ammunition) {
        super(shortName, longName, damage);
        this.ammunition = ammunition;
    }

    public boolean canUse() {
        if (ammunition > 0){
        return true;} else {return false;}
    }

    public void use() {
    ammunition --;
    }

    public String getAttackVerb() {
        return "fire";
    }

    public String getUsesLeftText() {
        return "Ammo: " + ammunition;
    }
}