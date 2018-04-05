
public class Weapon extends Item {

    private int maxDamage;
    private int minDamage;

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public Weapon(String name, String description, int minDamage, int maxDamage){
        super(name, description, true);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    @Override
    public String toString(){
        return name + ": " + description + "\nmin Dmg: " + minDamage + "\nmax Dmg: "+ maxDamage;
    }
}
