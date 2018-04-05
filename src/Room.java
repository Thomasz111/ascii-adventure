import java.util.Hashtable;

public class Room {

    private String name;
    private String description;

    final private Hashtable<String, Room> doors = new Hashtable<>();
    final private Hashtable<String, Item> items = new Hashtable<>();
    final private Hashtable<String, Enemy> enemies = new Hashtable<>();

    public Room(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Hashtable<String, Item> getItems(){return items;}

    public Hashtable<String, Enemy> getEnemies(){return enemies;}

    public Hashtable<String, Room> getDoors(){return doors;}

    public String getRoomName(){
        return name;
    }

    public String getRoomDescription(){
        return description;
    }

}
