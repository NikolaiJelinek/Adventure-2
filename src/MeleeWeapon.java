public class MeleeWeapon extends Weapon{


   public MeleeWeapon(String shortName, String longName, int damage) {
        super(shortName, longName, damage);
    }

    public boolean canUse(){
        //return true or false
        return true;
    }
    public void use(){

    }
    public String getAttackVerb(){
        return "swing";
    }

    public String getUsesLeftText(){
        return "";
    }

}
