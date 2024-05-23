package WorldPack;

import java.util.Random;

public enum Direction8
{
    N,
    NE,
    E,
    SE,
    S,
    SW,
    W,
    NW;

    private static final Random PRNG = new Random();

    public static Direction8 randomDirection()  {
        Direction8[] directions = values();
        return directions[PRNG.nextInt(directions.length)];
    }
}
