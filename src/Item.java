
public class Item {

    private boolean isCollectible;
    protected String name;
    protected String description;

    public Item(String name, String description, boolean isCollectible){
        this.name = name;
        this.description = description;
        this.isCollectible = isCollectible;
    }

    public boolean isCollectible(){
        return isCollectible;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public String toString(){
        return name + ": " + description;
    }
}
