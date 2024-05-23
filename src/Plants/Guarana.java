package Plants;

import AbstractClasses.Organism;
import AbstractClasses.Plant;
import WorldPack.MyButton;
import WorldPack.World;

import javax.swing.*;
import java.awt.*;

public class Guarana extends Plant
{
    public Guarana(int xPos, int yPos, World world)
    {
        super(world);
        strength = 0;
        this.xPos = xPos;
        this.yPos = yPos;
        this.world = world;
        AddToLogBorn();
    }
    public Guarana(World world)
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
        super.Action();
    }
    @Override
    public void Collision(Organism organism)
    {
        organism.SetStrength(organism.GetStrength() + 3);

        world.logs += " ^  Guarana from (";
        world.logs += xPos;
        world.logs += ", ";
        world.logs += yPos;
        world.logs += ") has increased ";
        world.logs += organism.OrganismName();
        world.logs += "'s strength to ";
        world.logs += organism.GetStrength();
        world.logs += ".\n";
    }
    @Override
    public void Print()
    {
        for (MyButton button : world.GetButtons())
        {
            if (button.getRow() == yPos && button.getColumn() == xPos)
            {
                button.setBackground(new Color(0,180,0));
                button.setText("GU");
                break;
            }
        }
    }
    @Override
    public String OrganismName()
    {
        return "Guarana";
    }
    @Override
    public void finalize()
    {
        super.finalize();
        AddToLogDeath();
    }
}
