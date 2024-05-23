package AbstractClasses;

import Plants.*;
import WorldPack.Direction8;
import WorldPack.World;

public abstract class Plant extends Organism
{
    public Plant(World world)
    {
        super(world);
        initiative = 0;
        world.SetPlantsLength(world.GetPlantsLength() + 1);
    }
    @Override
    public void Action()
    {
        if (Math.random() % 100 < 15) // 15 - seeding probability
        {
            Direction8 direction = Direction8.randomDirection();
            int newX = xPos;
            int newY = yPos;
            switch (direction)
            {
                case N:
                    if (yPos > 1 && !world.IsOrganismOnXY(xPos, yPos - 1))
                    {
                        newY--;
                    }
                    break;
                case NE:
                    if (xPos < world.GetWidth() && yPos > 1 && !world.IsOrganismOnXY(xPos + 1, yPos - 1))
                    {
                        newX++;
                        newY--;
                    }
                    break;
                case E:
                    if (xPos < world.GetWidth() && !world.IsOrganismOnXY(xPos + 1, yPos))
                    {
                        newX++;
                    }
                    break;
                case SE:
                    if (xPos < world.GetWidth() && yPos < world.GetHeight() && !world.IsOrganismOnXY(xPos + 1, yPos + 1))
                    {
                        newX++;
                        newY++;
                    }
                    break;
                case S:
                    if (yPos < world.GetHeight() && !world.IsOrganismOnXY(xPos, yPos + 1))
                    {
                        newY++;
                    }
                    break;
                case SW:
                    if (yPos < world.GetHeight() && xPos > 1 && !world.IsOrganismOnXY(xPos - 1, yPos + 1))
                    {
                        newX--;
                        newY++;
                    }
                    break;
                case W:
                    if (xPos > 1 && !world.IsOrganismOnXY(xPos - 1, yPos))
                    {
                        newX--;
                    }
                    break;
                case NW:
                    if (xPos > 1 && yPos > 1 && !world.IsOrganismOnXY(xPos - 1, yPos - 1))
                    {
                        newX--;
                        newY--;
                    }
                    break;
            }

            if (newX != xPos || newY != yPos)
            {
                if (this instanceof DeadlyNightshade)
                {
                    world.AddOrganism(new DeadlyNightshade(newX, newY, world));
                }
                else if (this instanceof Grass)
                {
                    world.AddOrganism(new Grass(newX, newY, world));
                }
                else if (this instanceof Guarana)
                {
                    world.AddOrganism(new Guarana(newX, newY, world));
                }
                else if (this instanceof Sonchus)
                {
                    world.AddOrganism(new Sonchus(newX, newY, world));
                }
                else if (this instanceof SosnowskysHogweed)
                {
                    world.AddOrganism(new SosnowskysHogweed(newX, newY, world));
                }
            }
        }
    }
    @Override
    public void AddToLogDeath()
    {
        world.logs += " -  ";
        world.logs += OrganismName();
        world.logs += " from (";
        world.logs += xPos;
        world.logs += ", ";
        world.logs += yPos;
        world.logs += ") has been eaten.\n";
    }
    @Override
    public void finalize()
    {
        world.SetPlantsLength(world.GetPlantsLength() - 1);
    }
}
