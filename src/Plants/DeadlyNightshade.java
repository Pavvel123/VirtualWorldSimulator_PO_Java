package Plants;

import AbstractClasses.Organism;
import AbstractClasses.Plant;
import WorldPack.MyButton;
import WorldPack.World;

import javax.swing.*;
import java.awt.*;

public class DeadlyNightshade extends Plant
{
    public DeadlyNightshade(int xPos, int yPos, World world)
    {
        super(world);
        strength = 99;
        this.xPos = xPos;
        this.yPos = yPos;
        this.world = world;
        AddToLogBorn();
    }
    public DeadlyNightshade(World world)
    {
        super(world);
        int[] pos = {0, 0};
        world.RandomPos(pos);
        strength = 99;
        this.xPos = pos[0];
        this.yPos = pos[1];
        this.world = world;
        AddToLogBorn();
    }
    @Override
    public void Action()
    {
        super.Action();
    }
    @Override
    public void Collision(Organism organism)
    {
        organism.SetIsDead(true);
    }
    @Override
    public void Print()
    {
        for (MyButton button : world.GetButtons())
        {
            if (button.getRow() == yPos && button.getColumn() == xPos)
            {
                button.setBackground(new Color(100,100,50));
                button.setText("DN");
                break;
            }
        }
    }
    @Override
    public String OrganismName()
    {
        return "Deadly Nightshade";
    }
    @Override
    public void finalize()
    {
        super.finalize();
        AddToLogDeath();
    }
}
