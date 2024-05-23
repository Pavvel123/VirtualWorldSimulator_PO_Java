package Plants;

import AbstractClasses.Animal;
import AbstractClasses.Organism;
import AbstractClasses.Plant;
import WorldPack.MyButton;
import WorldPack.World;

import java.awt.*;

public class SosnowskysHogweed extends Plant
{
    public SosnowskysHogweed(int xPos, int yPos, World world)
    {
        super(world);
        strength = 10;
        this.xPos = xPos;
        this.yPos = yPos;
        this.world = world;
        AddToLogBorn();
    }
    public SosnowskysHogweed(World world)
    {
        super(world);
        int[] pos = {0, 0};
        world.RandomPos(pos);
        strength = 10;
        this.xPos = pos[0];
        this.yPos = pos[1];
        this.world = world;
        AddToLogBorn();
    }
    @Override
    public void Action()
    {
        super.Action();
        for (Organism organism : world.GetOrganisms())
        {
            if (organism instanceof Animal)
            {
                for (int i = -1; i <= 1; i++)
                {
                    for (int k = -1; k <= 1; k++)
                    {
                        if (organism.GetXPos() == xPos + i && organism.GetYPos() == yPos + k && !(i == 0 && k == 0))
                        {
                            organism.EreasePrint();
                            organism.SetIsDead(true);
                            break;
                        }
                    }
                }
            }
        }
    }
    @Override
    public void Collision(Organism organism)
    {
        organism.SetIsDead(true);
    }
    @Override
    public void Print()
    {
        for (MyButton button : world.GetButtons())
        {
            if (button.getRow() == yPos && button.getColumn() == xPos)
            {
                button.setBackground(new Color(120,10,30));
                button.setText("SH");
                break;
            }
        }
    }
    @Override
    public String OrganismName()
    {
        return "Sosnowsky's Hogweed";
    }
    @Override
    public void finalize()
    {
        super.finalize();
        AddToLogDeath();
    }
}
