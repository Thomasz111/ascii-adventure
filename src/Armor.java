
public class Armor extends Item {

    private int dmgReduction;

    public Armor(String name, String description, int dmgReduction){
        super(name, description, true);
        this.dmgReduction = dmgReduction;
    }

    public int getDmgReduction(){
        return dmgReduction;
    }

    @Override
    public String toString(){
        return name + ": " + description + "\nDmg Reduction: " + dmgReduction;
    }

}
