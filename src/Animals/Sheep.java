package Animals;

import AbstractClasses.Animal;
import AbstractClasses.Organism;
import Exceptions.NoMoreSpaceAvailableException;
import WorldPack.MyButton;
import WorldPack.World;

import javax.swing.*;
import java.awt.*;

public class Sheep extends Animal
{
    public Sheep(int xPos, int yPos, World world)
    {
        super(world);
        strength = 4;
        initiative = 4;
        this.xPos = xPos;
        this.yPos = yPos;
        this.world = world;
        AddToLogBorn();
    }
    public Sheep(World world)
    {
        super(world);
        int[] pos = {0, 0};
        world.RandomPos(pos);
        strength = 4;
        initiative = 4;
        this.xPos = pos[0];
        this.yPos = pos[1];
        this.world = world;
        AddToLogBorn();
    }
    @Override
    public void Action() throws NoMoreSpaceAvailableException {
        super.Action();
    }
    @Override
    public void Collision(Organism organism)
    {
        super.Collision(organism);
        if (!(organism instanceof Sheep))
        {
            if (organism.IfDefendedTheAttack(this))
            {
                xPos = prevXpos;
                yPos = prevYpos;
                isDead = false;
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
                button.setBackground(new Color(150,255,250));
                button.setText("SH");
                break;
            }
        }
    }
    @Override
    public String OrganismName()
    {
        return "Sheep";
    }
    @Override
    public void finalize()
    {
        super.finalize();
        AddToLogDeath();
    }
}
