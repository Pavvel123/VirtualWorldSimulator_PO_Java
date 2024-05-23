package Animals;

import AbstractClasses.Animal;
import AbstractClasses.Organism;
import WorldPack.MyButton;
import WorldPack.World;

import javax.swing.*;
import java.awt.*;

public class Human extends Animal
{
    private boolean activeAlzursShield;
    private int timeoutAlzursShield;

    private void AlzursShieldAction()
    {
        if (timeoutAlzursShield > 0)
        {
            timeoutAlzursShield--;
        }

        if (timeoutAlzursShield == 0 && activeAlzursShield)
        {
            activeAlzursShield = false;
            timeoutAlzursShield = 5;
            world.logs += " =! Alzur's Shield deactivated.\n";
        }
    }
    private void AlzursShieldActivation()
    {
        if (!activeAlzursShield && timeoutAlzursShield == 0)
        {
            activeAlzursShield = true;
            timeoutAlzursShield = 5;
            world.logs += " == Alzur's Shield activated ==\n";
        }
        else if (activeAlzursShield)
        {
            world.logs += " =! Alzur's Shield is active; can not be activated now.\n";
        }
        else
        {
            world.logs += " =! Timeout for Alzur's Shield have not passed yet!\n";
        }
    }

    public Human(int xPos, int yPos, World world)
    {
        super(world);
        activeAlzursShield = false;
        timeoutAlzursShield = 5;
        strength = 5;
        initiative = 4;
        this.xPos = xPos;
        this.yPos = yPos;
        this.world = world;
        AddToLogBorn();
    }
    public Human(World world)
    {
        super(world);
        activeAlzursShield = false;
        timeoutAlzursShield = 5;
        int[] pos = {0, 0};
        world.RandomPos(pos);
        strength = 5;
        initiative = 4;
        this.xPos = pos[0];
        this.yPos = pos[1];
        this.world = world;
        AddToLogBorn();
    }
    public boolean GetActiveAlzursShield()
    {
        return activeAlzursShield;
    }
    public int GetTimeoutAlzursShield()
    {
        return timeoutAlzursShield;
    }
    public void SetActiveAlzursShield(boolean value)
    {
        activeAlzursShield = value;
    }
    public void SetTimeoutAlzursShield(int value)
    {
        timeoutAlzursShield = value;
    }
    @Override
    public boolean IfDefendedTheAttack(Organism offensive)
    {
        if (activeAlzursShield && offensive instanceof Animal)
        {
            world.logs += " !  Human from (";
            world.logs += xPos;
            world.logs += ", ";
            world.logs += yPos;
            world.logs += ") thanks to the Alzur's Shield has defended the";
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
    public void Action()
    {

        prevXpos = xPos;
        prevYpos = yPos;
        int xPosTemp = xPos;
        int yPosTemp = yPos;
        int direction = Integer.parseInt(JOptionPane.showInputDialog("Choose direction:"));
        switch (direction)
        {
            case 4:
                xPosTemp = this.xPos - 1;
                yPosTemp = this.yPos;
                world.logs += "->  Human is going west to (";
                break;
            case 7:
                xPosTemp = this.xPos - 1;
                yPosTemp = this.yPos - 1;
                world.logs += "->  Human is going north west to (";
                break;
            case 8:
                xPosTemp = this.xPos;
                yPosTemp = this.yPos - 1;
                world.logs += "->  Human is going north to (";
                break;
            case 9:
                xPosTemp = this.xPos + 1;
                yPosTemp = this.yPos - 1;
                world.logs += "->  Human is going north east to (";
                break;
            case 6:
                xPosTemp = this.xPos + 1;
                yPosTemp = this.yPos;
                world.logs += "->  Human is going east to (";
                break;
            case 3:
                xPosTemp = this.xPos + 1;
                yPosTemp = this.yPos + 1;
                world.logs += "->  Human is going south east to (";
                break;
            case 2:
                xPosTemp = this.xPos;
                yPosTemp = this.yPos + 1;
                world.logs += "->  Human is going south to (";
                break;
            case 1:
                xPosTemp = this.xPos - 1;
                yPosTemp = this.yPos + 1;
                world.logs += "->  Human is going south west to (";
                break;
            case 5:
                AlzursShieldActivation();
                break;
        }

        EreasePrint();
        xPos = xPosTemp;
        yPos = yPosTemp;
        Print();
        world.logs += xPos;
        world.logs += ", ";
        world.logs += yPos;
        world.logs += ").\n";
        AlzursShieldAction();
    }
    @Override
    public void Collision(Organism organism)
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
    public void Print()
    {
        for (MyButton button : world.GetButtons())
        {
            if (button.getRow() == yPos && button.getColumn() == xPos)
            {
                button.setBackground(new Color(0,255,0));
                button.setText("HU");
                break;
            }
        }
    }
    @Override
    public String OrganismName()
    {
        return "Human";
    }
    @Override
    public void finalize()
    {
        super.finalize();
    }
}
