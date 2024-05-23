package Animals;

import AbstractClasses.Animal;
import AbstractClasses.Organism;
import Exceptions.NoMoreSpaceAvailableException;
import WorldPack.MyButton;
import WorldPack.World;

import javax.swing.*;
import java.awt.*;

public class Wolf extends Animal
{
    public Wolf(int xPos, int yPos, World world)
    {
        super(world);
        strength = 9;
        initiative = 5;
        this.xPos = xPos;
        this.yPos = yPos;
        this.world = world;
        AddToLogBorn();
    }
    public Wolf(World world)
    {
        super(world);
        int[] pos = {0, 0};
        world.RandomPos(pos);
        strength = 9;
        initiative = 5;
        this.xPos = pos[0];
        this.yPos = pos[1];
        this.world = world;
        AddToLogBorn();
    }
    @Override
    public void Action() throws NoMoreSpaceAvailableException
    {
        super.Action();
    }
    @Override
    public void Collision(Organism organism)
    {
        super.Collision(organism);
        if (!(organism instanceof Wolf))
        {
            if (organism.IfDefendedTheAttack(this))
            {
                xPos = prevXpos;
                yPos = prevYpos;
                //isDead = false; ???
            }
            else
            {
                if (strength > organism.GetStrength())
                {
                    organism.SetIsDead(true);
                }
                else
                {
                    isDead = true;
                }
            }
        }
    }
    @Override
    public void Print()
    {
        for (MyButton button : world.GetButtons())
        {
            if (button.getRow() == yPos && button.getColumn() == xPos)
            {
                button.setBackground(new Color(150,125,200));
                button.setText("WF");
                break;
            }
        }
    }
    @Override
    public String OrganismName()
    {
        return "Wolf";
    }
    @Override
    public void finalize()
    {
        super.finalize();
        AddToLogDeath();
    }
}
