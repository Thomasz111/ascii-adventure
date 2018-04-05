import java.util.Hashtable;
import java.util.Random;

public class Player {

    private Room currentRoom;
    final private Hashtable<String, Item> items = new Hashtable<>();
    private Weapon weapon;
    private Armor armor;
    private int lives;
    private boolean defend;

    private Random random;

    public Player(Room startRoom){
        currentRoom = startRoom;
        lives = 50;
        weapon = new Weapon("Fist","FISTS OF FURRY!",1,1);
        armor = new Armor("Hat", "nice hat", 1);
        random = new Random();
        defend = false;
    }

    public boolean lives(){
        return (lives > 0);
    }

    public void setDefend(boolean defend){
        this.defend = defend;
    }

    public int getDamage(int damage){
        if(defend){
            damage = damage/2;
        }
        if((damage-armor.getDmgReduction())>0){
            lives -= (damage-armor.getDmgReduction());
            return (damage-armor.getDmgReduction());
        }else{
            return 0;
        }
    }

    public int attack(){
        return weapon.getMinDamage()+random.nextInt(weapon.getMaxDamage()-weapon.getMinDamage()+1);
    }

    public void changeLife(int delta){
        lives += delta;
    }

    public void goToRoom(String door){currentRoom = currentRoom.getDoors().get(door);}

    public boolean canGoToRoom(String door){return currentRoom.getDoors().containsKey(door);}

    public Room getCurrentRoom(){
        return currentRoom;
    }

    public void collectItem(String item) {
        items.put(currentRoom.getItems().get(item).getName(), currentRoom.getItems().get(item));//bierzemy z ewkipunku na podstawie normalnej nazwy
        currentRoom.getItems().remove(item);
    }

    public void equipItem(String item){
        Item tmp = items.get(item);
        if(tmp.getClass().getName().equals("Weapon")){
            items.put(weapon.getName(),weapon);
            weapon = (Weapon) tmp;
            items.remove(weapon.getName());
        }else {//Armor
            items.put(armor.getName(),armor);
            armor = (Armor) tmp;
            items.remove(armor.getName());
        }
    }

    public Hashtable<String, Item> getItems(){return items;}

    public String showStats(){
        return "Stats: " + "\nHealth: " + lives + "\nWeapon: " + weapon.toString() + "\nArmor: " + armor.toString();
    }
}
