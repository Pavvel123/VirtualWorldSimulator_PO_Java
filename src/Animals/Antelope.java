package Animals;

import AbstractClasses.Animal;
import AbstractClasses.Organism;
import Exceptions.NoMoreSpaceAvailableException;
import WorldPack.Direction4;
import WorldPack.MyButton;
import WorldPack.World;

import javax.swing.*;

import java.awt.*;

import static WorldPack.Direction4.*;



public class Antelope extends Animal
{
    public Antelope(int xPos, int yPos, World world)
    {
        super(world);
        strength = 4;
        initiative = 4;
        this.xPos = xPos;
        this.yPos = yPos;
        this.world = world;
        AddToLogBorn();
    }
    public Antelope(World world)
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
    public void Fight(Organism organism)
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
    @Override
    public void Action()
    {
        prevXpos = xPos;
        prevYpos = yPos;
        Direction4 direction = Direction4.randomDirection();
        world.logs += "~~> Antelope is running ";

        if (direction == N && yPos > 2 || direction == S && yPos >= world.GetHeight() - 1)
        {
            yPos -= 2;
            world.logs += "north ";
        }
        else if (direction == E && xPos < world.GetWidth() - 1 || direction == W && xPos <= 2)
        {
            xPos += 2;
            world.logs += "east ";
        }
        else if (direction == S && yPos < world.GetHeight() - 1 || direction == N && yPos <= 2)
        {
            yPos += 2;
            world.logs += "south ";
        }
        else// if (direction == W && xPos > 2 || direction == E && xPos >= world.GetWidth() - 1)
        {
            xPos -= 2;
            world.logs += "west ";
        }

        world.logs += "to (";
        world.logs += xPos;
        world.logs += ", ";
        world.logs += yPos;
        world.logs += ").\n";
    }
    @Override
    public void Collision(Organism organism)
    {
        super.Collision(organism);
        if (!(organism instanceof Antelope))
        {
            if (Math.random() % 100 < 50) // 50 - Getaway probability
            {
                isDead = false;
                int[] newPos = {xPos, yPos};
                try
                {
                    NewPosIn8Neighbourhood(newPos);
                    xPos = newPos[0];
                    yPos = newPos[1];
                }
                catch (NoMoreSpaceAvailableException e)
                {
                    Fight(organism);
                }
            }
            else
            {
                Fight(organism);
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
                button.setBackground(new Color(100,10,210));
                button.setText("AN");
                break;
            }
        }
    }
    @Override
    public String OrganismName()
    {
        return "Antelope";
    }
    @Override
    public void finalize()
    {
        super.finalize();
        AddToLogDeath();
    }
}
