
public class Health extends Item {

    private int health;

    public Health(String name, String description, int health){
        super(name, description, true);
        this.health = health;
    }

    public int getHealth(){
        return health;
    }

    @Override
    public String toString(){
        return name + ": " + description + "\nHealth: " + health;
    }
}
