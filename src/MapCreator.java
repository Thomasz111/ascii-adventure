
public class MapCreator {

    public MapCreator(){
    }

    public Map CreateMap(){
        Item key = new Item("key", "nice old key", true);
        Health pork = new Health("pork", "good food", 10);
        Weapon sword = new Weapon("sword", "sharp and shiny", 3, 5);
        Chest chest = new Chest("chest", "old, not so nice chest","key");
        Armor helmet = new Armor("helmet", "ideal for work", 2);
        chest.addItem(sword);
        chest.addItem(pork);

        Weapon axe = new Weapon("axe", "so sharp", 4, 4);
        Enemy Bandit = new Enemy("Bandit", "Watch out, he has axe", axe, helmet,20);

        Room one = new Room("one", "[chest] by the wall, [helmet] and [key] on the floor, [blue_door] in front");
        one.getItems().put("key", key);
        one.getItems().put("helmet", helmet);
        one.getItems().put("chest", chest);
        Room two = new Room("two", "Matrix([blue_door],[red_door]) or [fall], there is [Bandit] in this room");
        two.getEnemies().put("Bandit", Bandit);
        Room three = new Room("three", "Staircase [stairs_down]! In the east wall [red_door]");
        Room four = new Room("four", "Dark room with [door] and staircase [stairs_up]");
        Room five = new Room("five", "Only one [door]");

        one.getDoors().put("blue_door", two);
        two.getDoors().put("blue_door", one);
        two.getDoors().put("red_door", three);
        two.getDoors().put("fall", four);
        three.getDoors().put("red_door", two);
        three.getDoors().put("stairs_down", four);
        four.getDoors().put("stairs_up", three);
        four.getDoors().put("door", five);
        five.getDoors().put("door", four);

        Map map = new Map("one");
        map.addRoom(one, "one");
        map.addRoom(two, "two");
        map.addRoom(three, "three");
        map.addRoom(four, "four");
        map.addRoom(five, "five");
        return map;
    }

}
