public class Player {
    private int health;
    private int strength;
    private int attack;
    private String name;

    public Player(String name, int health, int strength, int attack) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int damage) {
        this.health = Math.max(0, this.health - damage);
    }

    public int getStrength() {
        return strength;
    }

    public int getAttack() {
        return attack;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}