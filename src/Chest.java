import java.util.Hashtable;

public class Chest extends Item {

    private Hashtable<String, Item> items = new Hashtable<>();
    private boolean locked;
    private String key;

    public Chest(String name, String description){
        super(name, description, false);
        locked = false;
        key = "";
    }

    public Chest(String name, String description, String key){
        super(name, description, false);
        this.key = key;
        locked = true;
    }

    public void open(){
        locked = true;
    }

    public String getKey(){
        return key;
    }

    public boolean isLocked(){
        return locked;
    }

    public void addItem(Item item){
        items.put(item.getName(), item);
    }

    public Hashtable<String, Item> getItems(){
        Hashtable<String, Item> temp = items;
        items = null;
        return temp;
    }

    public boolean isEmpty(){
        if(items == null) return true;
        return false;
    }

    @Override
    public String toString(){
        return name + ": " + description + " Locked " + locked;
    }

}
