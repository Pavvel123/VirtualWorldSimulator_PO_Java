package AbstractClasses;

import Exceptions.NoMoreSpaceAvailableException;
import WorldPack.MyButton;
import WorldPack.World;
import WorldPack.Direction8;

import javax.swing.*;
import java.awt.*;

public abstract class Organism
{
    protected int strength;
    protected int initiative;
    protected int xPos;
    protected int yPos;
    protected int age;
    protected boolean isDead;
    protected World world;

    public Organism(World world)
    {
        strength = 0;
        initiative = 0;
        xPos = 0;
        yPos = 0;
        age = 0;
        isDead = false;
        this.world = world;
    }
    public void AddToLogBorn()
    {
        world.logs += " *  ";
        world.logs += OrganismName();
        world.logs += " has been added on (";
        world.logs += xPos;
        world.logs += ", ";
        world.logs += yPos;
        world.logs += ").\n";
    }
    public int GetXPos()
    {
        return xPos;
    }
    public int GetYPos()
    {
        return yPos;
    }
    public int GetAge()
    {
        return age;
    }
    public boolean GetIsDead()
    {
        return isDead;
    }
    public int GetStrength()
    {
        return strength;
    }
    public int GetInitiative()
    {
        return initiative;
    }
    public void SetAge(int age)
    {
        this.age = age;
    }
    public void SetXPos(int xPos)
    {
        this.xPos = xPos;
    }
    public void SetYPos(int yPos)
    {
        this.yPos = yPos;
    }
    public void SetIsDead(boolean isDead)
    {
        this.isDead = isDead;
    }
    public void SetStrength(int strength)
    {
        this.strength = strength;
    }
    public abstract void Action() throws NoMoreSpaceAvailableException;
    public abstract void Collision(Organism organism);
    public abstract void Print();
    public boolean IfDefendedTheAttack(Organism offensive)
    {
        return false;
    }
    public void EreasePrint()
    {
        for (MyButton button : world.GetButtons())
        {
            if (button.getRow() == yPos && button.getColumn() == xPos)
            {
                button.setBackground(new Color(255,255,255, 255));
                button.setText("");
                break;
            }
        }
    }
    public Organism CollidedWith()
    {
        int organismsLength = world.GetOrganisms().size();
        boolean foundSelf = false;
        for (int o = 0; o < organismsLength; o++)
        {
            if (world.GetOrganisms().get(o).GetXPos() == xPos && world.GetOrganisms().get(o).GetYPos() == yPos)
            {
                if (this.OrganismName() == world.GetOrganisms().get(o).OrganismName())
                {
                    if (foundSelf)
                    {
                        return world.GetOrganisms().get(o);
                    }
                    else
                    {
                        foundSelf = true;
                    }
                }
			    else
                {
                    return world.GetOrganisms().get(o);
                }
            }
        }
        return null;
    }
    public abstract String OrganismName();
    public void NewPosIn8Neighbourhood(int[] pos) throws NoMoreSpaceAvailableException {
        boolean possibleN = true;
        boolean possibleNE = true;
        boolean possibleE = true;
        boolean possibleSE = true;
        boolean possibleS = true;
        boolean possibleSW = true;
        boolean possibleW = true;
        boolean possibleNW = true;
        do
        {
            Direction8 direction8 = Direction8.randomDirection();
            switch (direction8)
            {
                case N:
                    if (possibleN && yPos > 1 && !world.IsOrganismOnXY(xPos, yPos - 1))
                    {
                        pos[1]--;
                    }
                    else
                    {
                        possibleN = false;
                    }
                    break;
                case NE:
                    if (possibleNE && xPos < world.GetWidth() && yPos > 1 && !world.IsOrganismOnXY(xPos + 1, yPos - 1))
                    {
                        pos[0]++;
                        pos[1]--;
                    }
                    else
                    {
                        possibleNE = false;
                    }
                    break;
                case E:
                    if (possibleE && xPos < world.GetWidth() && !world.IsOrganismOnXY(xPos + 1, yPos))
                    {
                        pos[0]++;
                    }
                    else
                    {
                        possibleE = false;
                    }
                    break;
                case SE:
                    if (possibleSE && xPos < world.GetWidth() && yPos < world.GetHeight() && !world.IsOrganismOnXY(xPos + 1, yPos + 1))
                    {
                        pos[0]++;
                        pos[1]++;
                    }
                    else
                    {
                        possibleSE = false;
                    }
                    break;
                case S:
                    if (possibleS && yPos < world.GetHeight() && !world.IsOrganismOnXY(xPos, yPos + 1))
                    {
                        pos[1]++;
                    }
                    else
                    {
                        possibleS = false;
                    }
                    break;
                case SW:
                    if (possibleSW && yPos < world.GetHeight() && xPos > 1 && !world.IsOrganismOnXY(xPos - 1, yPos + 1))
                    {
                        pos[0]--;
                        pos[1]++;
                    }
                    else
                    {
                        possibleSW = false;
                    }
                    break;
                case W:
                    if (possibleW && xPos > 1 && !world.IsOrganismOnXY(xPos - 1, yPos))
                    {
                        pos[0]--;
                    }
                    else
                    {
                        possibleW = false;
                    }
                    break;
                case NW:
                    if (possibleNW && xPos > 1 && yPos > 1 && !world.IsOrganismOnXY(xPos - 1, yPos - 1))
                    {
                        pos[0]--;
                        pos[1]--;
                    }
                    else
                    {
                        possibleNW = false;
                    }
                    break;
                default:
                    break;
            }

            if (!(possibleN || possibleNE || possibleE || possibleSE || possibleS || possibleSW || possibleW || possibleNW))
            {
                throw new NoMoreSpaceAvailableException();
            }
        } while (pos[0] == xPos && pos[1] == yPos);
    }
    public abstract void AddToLogDeath();
}