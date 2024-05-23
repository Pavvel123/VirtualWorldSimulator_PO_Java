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

public class Fox extends Animal
{
    public Fox(int xPos, int yPos, World world)
    {
        super(world);
        strength = 3;
        initiative = 7;
        this.xPos = xPos;
        this.yPos = yPos;
        this.world = world;
        AddToLogBorn();
    }
    public Fox(World world)
    {
        super(world);
        int[] pos = {0, 0};
        world.RandomPos(pos);
        strength = 3;
        initiative = 7;
        xPos = pos[0];
        yPos = pos[1];
        this.world = world;
        AddToLogBorn();
    }
    @Override
    public void Action() throws NoMoreSpaceAvailableException {
        int newX = xPos;
        int newY = yPos;

        boolean possibleN = true;
        boolean possibleE = true;
        boolean possibleS = true;
        boolean possibleW = true;

        while (newX == xPos && newY == yPos)
        {
            Direction4 direction = Direction4.randomDirection();
            if (direction == N && yPos != 1 || direction == S && yPos == world.GetHeight())
            {
                if (direction == S)
                {
                    possibleS = false;
                }
                if (possibleN && CanMove(xPos, yPos - 1))
                {
                    newY--;
                    world.logs += "~>  Fox is going north to (";
                }
                else if (possibleN)
                {
                    possibleN = false;
                    world.logs += " !  There is organism on north (";
                    world.logs += xPos;
                    world.logs += ", ";
                    world.logs += yPos - 1;
                    world.logs += ") stronger than Fox. Won't go there.\n";
                }
            }
            else if (direction == E && xPos != world.GetWidth() || direction == W && xPos == 1)
            {
                if (direction == W)
                {
                    possibleW = false;
                }
                if (possibleE && CanMove(xPos + 1, yPos))
                {
                    newX++;
                    world.logs += "~>  Fox is going east to (";
                }
                else if (possibleE)
                {
                    possibleE = false;
                    world.logs += " !  There is organism on east (";
                    world.logs += xPos + 1;
                    world.logs += ", ";
                    world.logs += yPos;
                    world.logs += ") stronger than Fox. Won't go there.\n";
                }
            }
            else if (direction == S && yPos != world.GetHeight() || direction == N && yPos == 1)
            {
                if (direction == N)
                {
                    possibleN = false;
                }
                if (possibleS && CanMove(xPos, yPos + 1))
                {
                    newY++;
                    world.logs += "~>  Fox is going south to (";
                }
                else if (possibleS)
                {
                    possibleS = false;
                    world.logs += " !  There is organism on south (";
                    world.logs += xPos;
                    world.logs += ", ";
                    world.logs += yPos + 1;
                    world.logs += ") stronger than Fox. Won't go there.\n";
                }
            }
            else// if (direction == W && xPos != 1 || direction == E && xPos == world.GetWidth())
            {
                if (direction == E)
                {
                    possibleE = false;
                }
                if (possibleW && CanMove(xPos - 1, yPos))
                {
                    newX--;
                    world.logs += "~>  Fox is going west to (";
                }
                else if (possibleW)
                {
                    possibleW = false;
                    world.logs += " !  There is organism on west (";
                    world.logs += xPos - 1;
                    world.logs += ", ";
                    world.logs += yPos;
                    world.logs += ") stronger than Fox. Won't go there.\n";
                }
            }

            if (!(possibleN || possibleE || possibleS || possibleW))
            {
                world.logs += "!!  No more space for Fox on (";
                world.logs += xPos;
                world.logs += ", ";
                world.logs += yPos;
                world.logs += ")!\n";
                throw new NoMoreSpaceAvailableException();
            }
        }
        prevXpos = xPos;
        prevYpos = yPos;
        xPos = newX;
        yPos = newY;
        world.logs += xPos;
        world.logs += ", ";
        world.logs += yPos;
        world.logs += ").\n";
    }
    @Override
    public void Collision(Organism organism)
    {
        super.Collision(organism);
        if (!(organism instanceof Fox))
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
                button.setBackground(new Color(200,210,10));
                button.setText("FX");
                break;
            }
        }
    }

    public boolean CanMove(int newX, int newY)
    {
        for (Organism organism : world.GetOrganisms())
        {
            if (organism.GetXPos() == newX && organism.GetYPos() == newY && organism.GetStrength() > strength)
            {
                return false;
            }
        }
        return true;
    }
    @Override
    public String OrganismName()
    {
        return "Fox";
    }
    @Override
    public void finalize()
    {
        super.finalize();
        AddToLogDeath();
    }

}
