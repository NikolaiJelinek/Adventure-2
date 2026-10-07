public class AttackOutcome {
    private AttackResult result;
    private String enemyName;
    private int damage;
    private int enemyHealth;

    public AttackOutcome(AttackResult result, String enemyName, int damage, int enemyHealth) {
        this.result = result;
        this.enemyName = enemyName;
        this.damage = damage;
        this.enemyHealth = enemyHealth;
    }

    public AttackResult getResult() {
        return result;
    }
    public int getEnemyHealth(){
        return enemyHealth;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public int getDamage() {
        return damage;
    }
}