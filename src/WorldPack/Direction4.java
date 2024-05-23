package WorldPack;

import java.util.Random;

public enum Direction4
{
    N,
    E,
    S,
    W;

    private static final Random PRNG = new Random();

    public static Direction4 randomDirection()  {
        Direction4[] directions = values();
        return directions[PRNG.nextInt(directions.length)];
    }
}
