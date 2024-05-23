package Plants;

import AbstractClasses.Organism;
import AbstractClasses.Plant;
import WorldPack.MyButton;
import WorldPack.World;

import javax.swing.*;
import java.awt.*;

public class Sonchus extends Plant
{
    public Sonchus(int xPos, int yPos, World world)
    {
        super(world);
        strength = 0;
        this.xPos = xPos;
        this.yPos = yPos;
        this.world = world;
        AddToLogBorn();
    }
    public Sonchus(World world)
    {
        super(world);
        int[] pos = {0, 0};
        world.RandomPos(pos);
        strength = 0;
        this.xPos = pos[0];
        this.yPos = pos[1];
        this.world = world;
        AddToLogBorn();
    }
    @Override
    public void Action()
    {
        for (int i = 0; i < 3; i++)
        {
            super.Action();
        }
    }
    @Override
    public void Collision(Organism organism)
    {
    }
    @Override
    public void Print()
    {
        for (MyButton button : world.GetButtons())
        {
            if (button.getRow() == yPos && button.getColumn() == xPos)
            {
                button.setBackground(new Color(100,200,100));
                button.setText("SN");
                break;
            }
        }
    }
    @Override
    public String OrganismName()
    {
        return "Sonchus";
    }
    @Override
    public void finalize()
    {
        super.finalize();
        AddToLogDeath();
    }
}
