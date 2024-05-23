package Animals;

import AbstractClasses.Animal;
import AbstractClasses.Organism;
import Exceptions.NoMoreSpaceAvailableException;
import WorldPack.MyButton;
import WorldPack.World;

import javax.swing.*;
import java.awt.*;

public class Tortoise extends Animal
{
    public Tortoise(int xPos, int yPos, World world)
    {
        super(world);
        strength = 2;
        initiative = 1;
        this.xPos = xPos;
        this.yPos = yPos;
        this.world = world;
        AddToLogBorn();
    }
    public Tortoise(World world)
    {
        super(world);
        int[] pos = {0, 0};
        world.RandomPos(pos);
        strength = 2;
        initiative = 1;
        this.xPos = pos[0];
        this.yPos = pos[1];
        this.world = world;
        AddToLogBorn();
    }
    @Override
    public boolean IfDefendedTheAttack(Organism offensive)
    {
        if (offensive.GetStrength() < 5)
        {
            world.logs += " !  Tortoise from (";
            world.logs += xPos;
            world.logs += ", ";
            world.logs += yPos;
            world.logs += ") has defended the";
            world.logs += offensive.OrganismName();
            world.logs += "'s attack!\n";
            return true;
        }
        else
        {
            return false;
        }
    }
    @Override
    public void Action() throws NoMoreSpaceAvailableException
    {
        if (Math.random() % 100 < 25) // 25 - move probability
        {
            super.Action();
        }
        else
        {
            world.logs += " >  Tortoise won't go anywhere.\n";
        }
    }
    @Override
    public void Collision(Organism organism)
    {
        super.Collision(organism);
        if (!(organism instanceof Tortoise))
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
                button.setBackground(new Color(200,0,120));
                button.setText("TR");
                break;
            }
        }
    }
    @Override
    public String OrganismName()
    {
        return "Tortoise";
    }
    @Override
    public void finalize()
    {
        super.finalize();
        AddToLogDeath();
    }
}
