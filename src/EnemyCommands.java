
public class EnemyCommands {

    protected void enemyAttackLogic() {
        System.out.println("You got " + GameManager.player.getDamage(GameManager.enemy.attack()) + " damage");
    }

    protected void enemyDefendLogic() {
        GameManager.enemy.setDefend(true);
        System.out.println("Enemy is defending himself");
    }

}
