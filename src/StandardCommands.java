import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

public class StandardCommands {

    protected void exitLogic(){
        GameManager.exit = true;
        GameManager.endMove = true;
    }

    protected void statsLogic(){
        System.out.println(GameManager.player.showStats());
        GameManager.endMove = true;
    }

    protected void goLogic(String[] input){
        String doorName = secondArg(input);
        if(GameManager.player.canGoToRoom(doorName)){
            GameManager.player.goToRoom(doorName);
            System.out.println("You went to room " + GameManager.player.getCurrentRoom().getRoomName());
            GameManager.endMove = true;
        } else{
            System.out.println("You can't go there");
            GameManager.endMove = false;
        }
    }

    protected void itemsLogic(){
        Hashtable<String, Item> items = GameManager.player.getItems();
        if(items.isEmpty()){
            System.out.println("You have got 0 items");
            GameManager.endMove = false;
        }
        System.out.println("Your items:");
        Set<String> keys = items.keySet();
        for(String key : keys){
            System.out.println(items.get(key).toString());
        }
        GameManager.endMove = true;
    }

    protected void helpLogic(HashMap<String, Runnable> commandsList){
        System.out.println("Commands:");
        Set<String> keys = commandsList.keySet();
        for(String key : keys){
            System.out.print(key + " ");
        }
        System.out.print("\n");
        GameManager.endMove = false;
    }

    protected void nameLogic(){
        System.out.println("Current room: " + GameManager.player.getCurrentRoom().getRoomName());
        GameManager.endMove = true;
    }

    protected void lookLogic(){
        System.out.println("Room description: " + GameManager.player.getCurrentRoom().getRoomDescription());
        GameManager.endMove = true;
    }

    protected void fightLogic(String[] input){
        String enemyName = secondArg(input);
        if(GameManager.player.getCurrentRoom().getEnemies().containsKey(enemyName)){
            System.out.println("You started fight with "+ enemyName);
            GameManager.enemy = GameManager.player.getCurrentRoom().getEnemies().get(enemyName);
            GameManager.fight = true;
            GameManager.commandsList.clear();
            GameManager.commandsList.putAll(GameManager.fightCommandsList);
            GameManager.endMove = false;
        }else {
            System.out.println("There is no such an enemy");
            GameManager.endMove = false;
        }
    }

    protected void equipLogic(String[] input){
        String item = secondArg(input);
        if(GameManager.player.getItems().containsKey(item)){
            if(GameManager.player.getItems().get(item).getClass().getName().equals("Armor") || GameManager.player.getItems().get(item).getClass().getName().equals("Weapon")) {
                GameManager.player.equipItem(item);
                System.out.println("You equipped " + item);
                GameManager.endMove = true;
            } else{
                System.out.println("You can't equip " + item);
                GameManager.endMove = false;
            }
        }else{
            System.out.println("There is no such an item");
            GameManager.endMove = false;
        }
    }

    protected void eatLogic(String[] input){
        String item = secondArg(input);
        if(GameManager.player.getItems().containsKey(item)){
            if(GameManager.player.getItems().get(item).getClass().getName().equals("Health")) {
                Health health = (Health)(GameManager.player.getItems().get(item));
                GameManager.player.changeLife(health.getHealth());
                System.out.println("You recovered " + health.getHealth() + " lives");
                GameManager.player.getItems().remove(health.getName());
                GameManager.endMove = true;
            } else{
                System.out.println("You can't eat " + item);
                GameManager.endMove = false;
            }
        }else{
            System.out.println("There is no such an item");
            GameManager.endMove = false;
        }
    }

    protected void examineLogic(String[] input){
        String item = secondArg(input);
        if(GameManager.player.getCurrentRoom().getItems().containsKey(item)){
            System.out.println(GameManager.player.getCurrentRoom().getItems().get(item).toString());
            GameManager.endMove = true;
        }else{
            System.out.println("There is no such an item");
            GameManager.endMove = false;
        }
    }

    protected void collectLogic(String[] input){
        String item = secondArg(input);
        if(GameManager.player.getCurrentRoom().getItems().containsKey(item)){
            if(GameManager.player.getCurrentRoom().getItems().get(item).isCollectible()){
                System.out.println("You collected " + GameManager.player.getCurrentRoom().getItems().get(item).toString());
                GameManager.player.collectItem(item);
                GameManager.endMove = true;
            }
            else{
                System.out.println("You can't collect " + item);
                GameManager.endMove = false;
            }
        }else{
            System.out.println("There is no such an item");
            GameManager.endMove = false;
        }
    }

    protected void openLogic(String[] input){
        String chestName = secondArg(input);
        if(GameManager.player.getCurrentRoom().getItems().containsKey(chestName)){
            if(GameManager.player.getCurrentRoom().getItems().get(chestName).getClass().getName().equals("Chest")){
                Chest chest = (Chest)(GameManager.player.getCurrentRoom().getItems().get(chestName));
                if(chest.isLocked()){
                    if(GameManager.player.getItems().containsKey(chest.getKey())){
                        chest.open();
                        if(chest.isEmpty()){
                            System.out.println("This chest is empty");
                            GameManager.endMove = true;
                        }else{
                            GameManager.player.getItems().putAll(chest.getItems());
                            System.out.println("You collected items from chest");
                            GameManager.endMove = true;
                        }
                    }else{
                        System.out.println("You don't have key for this chest");
                        GameManager.endMove = false;
                    }
                }else{
                    if(chest.isEmpty()){
                        System.out.println("This chest is empty");
                        GameManager.endMove = false;
                    }else{
                        GameManager.player.getItems().putAll(chest.getItems());
                        System.out.println("You collected items from chest");
                        GameManager.endMove = true;
                    }
                }
            }
            else{
                System.out.println("You can't open " + chestName);
                GameManager.endMove = false;
            }
        }else{
            System.out.println("There is no such an item");
            GameManager.endMove = false;
        }
    }

    private String secondArg(String[] input){
        if(input.length == 1){
            System.out.println("What do you want to " + input[0] +"?");
            return GameManager.reader.nextLine();
        }
        else{
            return input[1];
        }
    }
}
