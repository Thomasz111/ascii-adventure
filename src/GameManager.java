import java.util.HashMap;
import java.util.Scanner;

public class GameManager {

    private Map map;

    protected static Scanner reader;
    protected static String[] input;

    protected static Player player;
    protected static Enemy enemy;

    protected static HashMap<String, Runnable> standardCommandsList = new HashMap<>();
    protected static HashMap<String, Runnable> fightCommandsList = new HashMap<>();
    protected static HashMap<String, Runnable> commandsList = new HashMap<>();
    protected static HashMap<String, Runnable> enemyCommandsList = new HashMap<>();

    protected static Boolean exit;
    protected static Boolean endMove;
    protected static Boolean fight;

    public GameManager(){
        MapCreator mapCreator = new MapCreator();
        map = mapCreator.CreateMap();
        player = new Player(map.getStartingRoom());
        enemy = null;
        reader = new Scanner(System.in);
        exit = false;
        endMove = false;
        fight = false;
        initStandardCommands();
        initFightCommands();
        initEnemyCommands();
        commandsList.clear();
        commandsList.putAll(standardCommandsList);
    }

    private void initEnemyCommands(){
        EnemyCommands enemyCommands = new EnemyCommands();
        enemyCommandsList.put("attack", () -> enemyCommands.enemyAttackLogic());
        enemyCommandsList.put("defend", () -> enemyCommands.enemyDefendLogic());
    }

    private void initFightCommands(){
        StandardCommands standardCommands = new StandardCommands();
        FightCommands fightCommands = new FightCommands();
        fightCommandsList.put("help", () -> standardCommands.helpLogic(fightCommandsList));
        fightCommandsList.put("attack",() -> fightCommands.attackLogic());
        fightCommandsList.put("defend", () -> fightCommands.defendLogic());
        fightCommandsList.put("spy", () -> fightCommands.spyLogic());
        fightCommandsList.put("items", () -> standardCommands.itemsLogic());
        fightCommandsList.put("stats", () -> standardCommands.statsLogic());
        fightCommandsList.put("equip", () -> standardCommands.equipLogic(input));
        fightCommandsList.put("eat", () -> standardCommands.eatLogic(input));
        fightCommandsList.put("run", () -> fightCommands.runLogic());
    }

    private void initStandardCommands(){
        StandardCommands standardCommands = new StandardCommands();
        standardCommandsList.put("help", () -> standardCommands.helpLogic(standardCommandsList));
        standardCommandsList.put("name", () -> standardCommands.nameLogic());
        standardCommandsList.put("look", () -> standardCommands.lookLogic());
        standardCommandsList.put("examine", () -> standardCommands.examineLogic(input));
        standardCommandsList.put("collect", () -> standardCommands.collectLogic(input));
        standardCommandsList.put("equip", () -> standardCommands.equipLogic(input));
        standardCommandsList.put("open", () -> standardCommands.openLogic(input));
        standardCommandsList.put("eat", () -> standardCommands.eatLogic(input));
        standardCommandsList.put("fight", () -> standardCommands.fightLogic(input));
        standardCommandsList.put("items", () -> standardCommands.itemsLogic());
        standardCommandsList.put("stats", () -> standardCommands.statsLogic());
        standardCommandsList.put("go", () -> standardCommands.goLogic(input));
        standardCommandsList.put("exit", () -> standardCommands.exitLogic());
    }

    public void startLoop(){
        standardCommandsList.get("help").run();
        while(true){

            endMove = false;
            player.setDefend(false);

            do{
                System.out.println("----------------------------");
                input = reader.nextLine().split(" ");
                if(commandsList.containsKey(input[0])) commandsList.get(input[0]).run();
                else System.out.println("???");
            }while(!endMove);

            if(!player.lives()){
                System.out.println("You died!");
                return;
            }

            if(exit){
                System.out.println("You left the game");
                return;
            }

            if(fight){
                enemy.setDefend(false);
                input = enemy.input();
                enemyCommandsList.get(input[0]).run();
            }

            if(!player.lives()){
                System.out.println("You died!");
                return;
            }
        }
    }

}
