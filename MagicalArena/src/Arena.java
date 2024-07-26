public class Arena {
    private Player playerA;
    private Player playerB;
    private Dice dice;
    
    
    public Arena(Player playerA, Player playerB) {
        this(playerA, playerB, new Dice());
    }
    
    public Arena(Player playerA, Player playerB, Dice dice) {
		super();
		this.playerA = playerA;
		this.playerB = playerB;
		this.dice = dice;
	}
   
    public void fight() {
        while (playerA.isAlive() && playerB.isAlive()) {
            if (playerA.getHealth() < playerB.getHealth()) {
                performAttack(playerA, playerB);
            } else {
                performAttack(playerB, playerA);
            }
        }

        if (playerA.isAlive()) {
            System.out.println(playerA.getName() + " wins!");
        } else {
            System.out.println(playerB.getName() + " wins!");
        }
    }
    private void performAttack(Player attacker, Player defender) {
        int attackRoll = dice.roll();
        int defendRoll = dice.roll();

        int attackDamage = attacker.getAttack() * attackRoll;
        int defendDamage = defender.getStrength() * defendRoll;

        int actualDamage = Math.max(0, attackDamage - defendDamage);
        defender.reduceHealth(actualDamage);

        System.out.println(attacker.getName() + " attacked with " + attackDamage + " damage. " +
                defender.getName() + " defended with " + defendDamage + " defense. " +
                "Actual damage: " + actualDamage + ". " +
                defender.getName() + "'s health: " + defender.getHealth());  
    }
	
	
}