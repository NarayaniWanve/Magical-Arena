import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;

public class GameTest {
    
    private Player playerA;
    private Player playerB;

    @BeforeEach
    public void setUp() {
        playerA = new Player("Player A", 50, 5, 10);
        playerB = new Player("Player B", 100, 10, 5);
    }

    @Test
    public void testPlayerCreation() {
        assertEquals("Player A", playerA.getName());
        assertEquals(50, playerA.getHealth());
        assertEquals(5, playerA.getStrength());
        assertEquals(10, playerA.getAttack());
    }

    @Test
    public void testPlayerHealthReduction() {
        playerA.reduceHealth(20);
        assertEquals(30, playerA.getHealth());
        playerA.reduceHealth(50);
        assertEquals(0, playerA.getHealth());
    }

    @Test
    public void testFight() {
        Arena arena = new Arena(playerA, playerB);
        arena.fight();

        assertTrue(!playerA.isAlive() || !playerB.isAlive());
    }

    @Test
    public void testEqualHealthFight() {
        Player playerC = new Player("Player C", 50, 5, 10);
        Player playerD = new Player("Player D", 50, 5, 10);

        Arena arena = new Arena(playerC, playerD);
        arena.fight();

        assertTrue(!playerC.isAlive() || !playerD.isAlive());
    }

    @Test
    public void testPerformAttack() throws Exception {
        Dice diceMock = new Dice() {
            @Override
            public int roll() {
                return 6; 
            }
        };

        Arena arena = new Arena(playerA, playerB, diceMock);
        Method performAttack = Arena.class.getDeclaredMethod("performAttack", Player.class, Player.class);
        performAttack.setAccessible(true);

        int attackRoll = diceMock.roll();
        int defendRoll = diceMock.roll();

        int attackDamage = playerA.getAttack() * attackRoll;
        int defendDamage = playerB.getStrength() * defendRoll;
        int expectedDamage = Math.max(0, attackDamage - defendDamage);

        performAttack.invoke(arena, playerA, playerB);

        assertEquals(playerB.getHealth(), 100 - expectedDamage);
    }
}