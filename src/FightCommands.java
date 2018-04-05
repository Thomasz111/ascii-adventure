
public class FightCommands {


    protected void runLogic(){
        System.out.println("You escaped battle");
        GameManager.fight = false;
        GameManager.commandsList.clear();
        GameManager.commandsList.putAll(GameManager.standardCommandsList);
        GameManager.endMove = true;
    }

    protected void spyLogic(){
        System.out.println(GameManager.enemy.toString());
        GameManager.endMove = true;
    }

    protected void attackLogic(){
        System.out.println("You dealt " + GameManager.enemy.getDamage(GameManager.player.attack()) + " damage");
        if(!GameManager.enemy.lives()){
            System.out.println("You defeated " + GameManager.enemy.getName());
            GameManager.player.getCurrentRoom().getEnemies().remove(GameManager.enemy.getName());
            GameManager.enemy = null;
            GameManager.fight = false;
            GameManager.endMove = true;
            GameManager.commandsList.clear();
            GameManager.commandsList.putAll(GameManager.standardCommandsList);
        }
        GameManager.endMove = true;
    }

    protected void defendLogic(){
        GameManager.player.setDefend(true);
        System.out.println("You are defending yourself");
        GameManager.endMove = true;
    }
}
