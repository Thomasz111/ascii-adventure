import java.util.Hashtable;

public class Map {

    private Hashtable<String, Room> rooms = new Hashtable<>();
    private String startingRoom;

    public Map(String startingRoom){
        this.startingRoom = startingRoom;
    }

    public void addRoom(Room room, String name){
        rooms.put(name, room);
    }

    public Room getStartingRoom(){return rooms.get(startingRoom);}
}
