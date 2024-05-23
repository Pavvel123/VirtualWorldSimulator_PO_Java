package AbstractClasses;

import Animals.*;
import WorldPack.World;
import WorldPack.Direction4;
import WorldPack.Direction8;
import Exceptions.NoMoreSpaceAvailableException;

public abstract class Animal extends Organism
{
    protected int prevXpos;
    protected int prevYpos;

    public Animal(World world)
    {
        super(world);
        prevXpos = 0;
        prevYpos = 0;
        world.SetAnimalsLength(world.GetAnimalsLength() + 1);
    }
    @Override
    public void Action() throws NoMoreSpaceAvailableException
    {
        this.prevXpos = xPos;
        prevYpos = yPos;
        Direction4 direction = Direction4.randomDirection();
        world.logs += "~>  ";
        world.logs += OrganismName();
        world.logs += " is going ";

        if (direction == Direction4.N && yPos != 1 || direction == Direction4.S && yPos == world.GetHeight())
        {
            yPos--;
            world.logs += "north ";
        }
        else if (direction == Direction4.E && xPos != world.GetWidth() || direction == Direction4.W && xPos == 1)
        {
            xPos++;
            world.logs += "east ";
        }
        else if (direction == Direction4.S && yPos != world.GetHeight() || direction == Direction4.N && yPos == 1)
        {
            yPos++;
            world.logs += "south ";
        }
        else //if (direction == W && xPos != 1 || direction == E && xPos == world.GetWidth())
        {
            xPos--;
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
        if (OrganismName() == organism.OrganismName())
        {
            int[] newPos = { xPos, yPos };
            if (age > 5 && organism.GetAge() > 5)
            {
                try
                {
                    NewPosIn8Neighbourhood(newPos);
                }
                catch (NoMoreSpaceAvailableException e)
                {
                }
                if (organism instanceof Antelope)
                {
                    Antelope newAntelope = new Antelope(newPos[0], newPos[1], world);
                    world.AddOrganism(newAntelope);
                    newAntelope.Print();
                }
                else if (organism instanceof Fox)
                {
                    Fox newFox = new Fox(newPos[0], newPos[1], world);
                    world.AddOrganism(newFox);
                    newFox.Print();
                }
                else if (organism instanceof Sheep)
                {
                    Sheep newSheep = new Sheep(newPos[0], newPos[1], world);
                    world.AddOrganism(newSheep);
                    newSheep.Print();
                }
                else if (organism instanceof Tortoise)
                {
                    Tortoise newTortoise = new Tortoise(newPos[0], newPos[1], world);
                    world.AddOrganism(newTortoise);
                    newTortoise.Print();
                }
                else if (organism instanceof Wolf)
                {
                    Wolf newWolf = new Wolf(newPos[0], newPos[1], world);
                    world.AddOrganism(newWolf);
                    newWolf.Print();
                }
            }
            newPos[0] = xPos;
            newPos[1] = yPos;
            try
            {
                NewPosIn8Neighbourhood(newPos);
                xPos = newPos[0];
                yPos = newPos[1];
            }
            catch (NoMoreSpaceAvailableException e)
            {
                isDead = true;
            }
        }
    }
    @Override
    public void AddToLogDeath()
    {

    }
    @Override
    public void finalize()
    {
        world.SetAnimalsLength(world.GetAnimalsLength() - 1);
    }
}
