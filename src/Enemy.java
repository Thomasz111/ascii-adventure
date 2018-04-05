import java.util.Random;

public class Enemy {

    private String name;
    private String description;
    private Weapon weapon;
    private Armor armor;
    private int lives;
    private boolean defend;

    private Random random;

    public Enemy(String name, String description, Weapon weapon, Armor armor, int lives){
        this.name = name;
        this.description = description;
        this.weapon = weapon;
        this.armor = armor;
        this.lives = lives;
        random = new Random();
        defend = false;
    }

    public String getName(){
        return name;
    }

    public void setDefend(boolean defend){
        this.defend = defend;
    }

    public boolean lives(){
        return (lives > 0);
    }

    public int getDamage(int damage){
        if(defend){
            damage = damage/2;
        }
        if((damage-armor.getDmgReduction())>0){
            lives -= (damage-armor.getDmgReduction());
            return damage-armor.getDmgReduction();
        }else{
            return 0;
        }
    }

    public int attack(){
        return weapon.getMinDamage()+random.nextInt(weapon.getMaxDamage()-weapon.getMinDamage()+1);
    }

    public String[] input(){
        if(random.nextInt()%5 == 0)
            return new String[] {"defend"};
        else
            return new String[] {"attack"};
    }

    @Override
    public String toString(){
        return name + ": " + description + "\nHealth: " + lives + "\n" + weapon.toString() + "\n" + armor.toString();
    }

}
