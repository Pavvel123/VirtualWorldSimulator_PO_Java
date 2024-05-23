package WorldPack;

import AbstractClasses.Organism;
import Animals.*;
import Exceptions.NoMoreSpaceAvailableException;
import Plants.*;
import UI.MyFrame;

import javax.swing.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class World
{
    public JTextArea logTextArea;
    private Vector<MyButton> buttons;
    private Vector<Organism> organisms;
    private int animalsLength;
    private int plantsLength;
    private int width;
    private int height;

    public String logs;
    public World(int width, int height)
    {
        animalsLength = 0;
        plantsLength = 0;
        this.width = width;
        this.height = height;
        logs = "";
        buttons = new Vector<>();
        organisms = new Vector<>();
        organisms.add(new Fox(this));
        organisms.add(new Fox(this));
        organisms.add(new Wolf(this));
        organisms.add(new Wolf(this));

        organisms.add(new Antelope(this));
        organisms.add(new Antelope(this));
        organisms.add(new Human(this));
        organisms.add(new Sheep(this));
        organisms.add(new Sheep(this));
        organisms.add(new Tortoise(this));
        organisms.add(new Tortoise(this));

        organisms.add(new DeadlyNightshade(this));
        organisms.add(new Grass(this));
        organisms.add(new Guarana(this));
        organisms.add(new Sonchus(this));
        organisms.add(new SosnowskysHogweed(this));
    }

    public World(File file)
    {
        try
        {
            Scanner scanner = new Scanner(file);
            logs = "";
            animalsLength = 0;
            plantsLength = 0;
            String line;
            line = scanner.nextLine();
            boolean activeAlzursShieldTemp = line.charAt(0) == '1' ? true : false;
            int timeoutAlzursShieldTemp = (int)line.charAt(2);
            line = scanner.nextLine();
            width = 10 * (int)line.charAt(0) + (int)line.charAt(1);
            height = 10 * (int)line.charAt(3) + (int)line.charAt(4);
            organisms = new Vector<>();
            while (scanner.hasNextLine())
            {
                line = scanner.nextLine();
                int xPos = 10 * (int)line.charAt(1) + (int)line.charAt(2);
                int yPos = 10 * (int)line.charAt(4) + (int)line.charAt(5);
                switch (line.charAt(0))
                {
                    case 'A':
                        Antelope antelope;
                        antelope = new Antelope(xPos, yPos, this);
                        organisms.add(antelope);
                        break;
                    case 'D':
                        DeadlyNightshade deadlyNightshade;
                        deadlyNightshade = new DeadlyNightshade(xPos, yPos, this);
                        organisms.add(deadlyNightshade);
                        break;
                    case 'F':
                        Fox fox;
                        fox = new Fox(xPos, yPos, this);
                        organisms.add(fox);
                        break;
                    case 'G':
                        Grass grass;
                        grass = new Grass(xPos, yPos, this);
                        organisms.add(grass);
                        break;
                    case 'U':
                        Guarana guarana;
                        guarana = new Guarana(xPos, yPos, this);
                        organisms.add(guarana);
                        break;
                    case 'H':
                        Human human;
                        human = new Human(xPos, yPos, this);
                        human.SetActiveAlzursShield(activeAlzursShieldTemp);
                        human.SetTimeoutAlzursShield(timeoutAlzursShieldTemp);
                        organisms.add(human);
                        break;
                    case 'S':
                        Sheep sheep;
                        sheep = new Sheep(xPos, yPos, this);
                        organisms.add(sheep);
                        break;
                    case 'O':
                        Sonchus sonchus;
                        sonchus = new Sonchus(xPos, yPos, this);
                        organisms.add(sonchus);
                        break;
                    case 'E':
                        SosnowskysHogweed sosnowskysHogweed;
                        sosnowskysHogweed = new SosnowskysHogweed(xPos, yPos, this);
                        organisms.add(sosnowskysHogweed);
                        break;
                    case 'T':
                        Tortoise tortoise;
                        tortoise = new Tortoise(xPos, yPos, this);
                        organisms.add(tortoise);
                        break;
                    case 'W':
                        Wolf wolf;
                        wolf = new Wolf(xPos, yPos, this);
                        organisms.add(wolf);
                        break;
                    default:
                        break;
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException e)
        {

        }
    }
    public void LoadFromFile(File file)
    {
        try {
            Scanner scanner = new Scanner(file);
            logs = "";
            animalsLength = 0;
            plantsLength = 0;
            String line;
            line = scanner.nextLine();
            boolean activeAlzursShieldTemp = line.charAt(0) == '1' ? true : false;
            int timeoutAlzursShieldTemp = line.charAt(2) - '0';
            line = scanner.nextLine();
            width = 10 * (line.charAt(0) - '0') + (line.charAt(1) - '0');
            height = 10 * (line.charAt(3) - '0') + (line.charAt(4) - '0');
            organisms = new Vector<>();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                int xPos = 10 * (line.charAt(1) - '0') + line.charAt(2) - '0';
                int yPos = 10 * (line.charAt(4) - '0') + line.charAt(5) - '0';
                switch (line.charAt(0)) {
                    case 'A':
                        Antelope antelope;
                        antelope = new Antelope(xPos, yPos, this);
                        organisms.add(antelope);
                        break;
                    case 'D':
                        DeadlyNightshade deadlyNightshade;
                        deadlyNightshade = new DeadlyNightshade(xPos, yPos, this);
                        organisms.add(deadlyNightshade);
                        break;
                    case 'F':
                        Fox fox;
                        fox = new Fox(xPos, yPos, this);
                        organisms.add(fox);
                        break;
                    case 'G':
                        Grass grass;
                        grass = new Grass(xPos, yPos, this);
                        organisms.add(grass);
                        break;
                    case 'U':
                        Guarana guarana;
                        guarana = new Guarana(xPos, yPos, this);
                        organisms.add(guarana);
                        break;
                    case 'H':
                        Human human;
                        human = new Human(xPos, yPos, this);
                        human.SetActiveAlzursShield(activeAlzursShieldTemp);
                        human.SetTimeoutAlzursShield(timeoutAlzursShieldTemp);
                        organisms.add(human);
                        break;
                    case 'S':
                        Sheep sheep;
                        sheep = new Sheep(xPos, yPos, this);
                        organisms.add(sheep);
                        break;
                    case 'O':
                        Sonchus sonchus;
                        sonchus = new Sonchus(xPos, yPos, this);
                        organisms.add(sonchus);
                        break;
                    case 'E':
                        SosnowskysHogweed sosnowskysHogweed;
                        sosnowskysHogweed = new SosnowskysHogweed(xPos, yPos, this);
                        organisms.add(sosnowskysHogweed);
                        break;
                    case 'T':
                        Tortoise tortoise;
                        tortoise = new Tortoise(xPos, yPos, this);
                        organisms.add(tortoise);
                        break;
                    case 'W':
                        Wolf wolf;
                        wolf = new Wolf(xPos, yPos, this);
                        organisms.add(wolf);
                        break;
                    default:
                        break;
                }
            }
            scanner.close();
        }
        catch (Exception e)
        {

        }
    }
    public void AddButton(MyButton button)
    {
        buttons.add(button);
    }
    public void AddOrganism(Organism organism)
    {
        organisms.add(organism);
    }
    public int GetAnimalsLength()
    {
        return animalsLength;
    }
    public int GetPlantsLength()
    {
        return plantsLength;
    }
    public int GetWidth()
    {
        return width;
    }
    public int GetHeight()
    {
        return height;
    }
    public Vector<MyButton> GetButtons()
    {
        return buttons;
    }
    public Vector<Organism> GetOrganisms()
    {
        return organisms;
    }
    public void SetAnimalsLength(int length)
    {
        animalsLength = length;
    }
    public void SetPlantsLength(int length)
    {
        plantsLength = length;
    }
    public boolean IsOrganismOnXY(int x, int y)
    {
        for (Organism organism : organisms)
        {
            if (organism.GetXPos() == x && organism.GetYPos() == y)
            {
                return true;
            }
        }
        return false;
    }
    public int[] RandomPos(int[] output)
    {
        Random random = new Random();
        do
        {
            output[0] = (int)(random.nextInt(width + 1));
            output[1] = (int)(random.nextInt(height + 1));
        } while (IsOrganismOnXY(output[0], output[1]));
        return output;
    }
    public void SortOrganisms()
    {
        organisms.sort((a, b) ->
        {
            if (a.GetInitiative() != b.GetInitiative())
            {
                return Integer.compare(a.GetInitiative(), b.GetInitiative());
            }
            else
            {
                return Integer.compare(a.GetAge(), b.GetAge());
            }
        });
    }
    public int MakeTurn()
    {
        //int organismsLength = animalsLength + plantsLength;
        int organismsLength = organisms.size();
        for (int i = 0; i < organismsLength; i++)
        {
            try
            {
                if (!(organisms.get(i) instanceof Human))
                {
                    organisms.get(i).EreasePrint();
                }
            }
            catch (Exception e)
            {
                System.out.println("");
            }

            if (organisms.get(i).GetIsDead())
            {
                if (organisms.get(i) instanceof Human)
                {
                    logs += "\n ++ Human is dead ++\n\n";
                    //SetTextColour(15);
                    //Gotoxy(0, height + 5);
                    //cout << logs << '\n';
                    //Gotoxy(2 * width + 5, 3);
                    //SetTextColour(15);
                    //cout << "Human is dead. End of game.";
                    //return 0;
                }
                organisms.remove(organisms.get(i));
                organismsLength--;
                i--;
            }
		else
            {
                //Gotoxy(2 * width + 5, i + 3);
                try
                {
                    organisms.get(i).Action();
                }
                catch (NoMoreSpaceAvailableException e)
                {
                    organisms.get(i).SetIsDead(true);
                    //i--;
                    continue;
                }
                Organism organismColidedWith = organisms.get(i).CollidedWith();
                if (organismColidedWith != null)
                {
                    organisms.get(i).Collision(organismColidedWith);
                    if (organisms.get(i).OrganismName() != organismColidedWith.OrganismName() && organisms.get(i).GetXPos() == organismColidedWith.GetXPos() && organisms.get(i).GetYPos() == organismColidedWith.GetYPos())
                    {
                        organismColidedWith.Collision(organisms.get(i));
                    }
                    if (!organismColidedWith.GetIsDead())
                    {
                        organismColidedWith.Print();
                    }
                }
                organisms.get(i).Print();
                organisms.get(i).SetAge(organisms.get(i).GetAge() + 1);
            }
        }

        //SetTextColour(15);
        //Gotoxy(0, height + 5);
        //cout << logs << '\n';
        logTextArea.setText(logs);

        SortOrganisms();
        return 1;
    }
    public void Print()
    {
        for (Organism organism : organisms)
        {
            organism.Print();
        }
    }
    public void SaveToFile(File file)
    {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("SAVE.txt")))
        {

            for (Organism organism : organisms)
            {
                if (organism instanceof Human)
                {
                    Human h = (Human)organism;
                    fileWriter.write((h.GetActiveAlzursShield() ? "1" : "0") + "," + h.GetTimeoutAlzursShield() + "\n");
                    //fileWriter.write(',');
                    //fileWriter.write(h.GetTimeoutAlzursShield());
                    //fileWriter.write('\n');
                    break;
                }
            }
            if (width < 10)
            {
                fileWriter.write("0");
            }
            fileWriter.write(width + "x");
            if (height < 10)
            {
                fileWriter.write("0");
            }
            fileWriter.write(height + "\n");
            for (Organism organism : organisms)
            {
                int xPos = organism.GetXPos();
                int yPos = organism.GetYPos();
                if (organism instanceof Antelope) fileWriter.write("A");
                else if (organism instanceof DeadlyNightshade) fileWriter.write("D");
                else if (organism instanceof Fox) fileWriter.write("F");
                else if (organism instanceof Grass) fileWriter.write("G");
                else if (organism instanceof Guarana) fileWriter.write("U");
                else if (organism instanceof Human) fileWriter.write("H");
                else if (organism instanceof Sheep) fileWriter.write("S");
                else if (organism instanceof Sonchus) fileWriter.write("O");
                else if (organism instanceof SosnowskysHogweed) fileWriter.write("E");
                else if (organism instanceof Tortoise) fileWriter.write("T");
                else if (organism instanceof Wolf) fileWriter.write("W");

                if (xPos < 10) fileWriter.write("0");
                fileWriter.write(xPos + ",");
                if (yPos < 10) fileWriter.write("0");
                fileWriter.write(yPos + "\n");
            }
            fileWriter.close();
            logs += "##  Board saved to file succesfully.\n";
            //Gotoxy(2 * width + 3, 5);
            //SetTextColour(15);
            //cout << "Board saved to file succesfully.";
        }
        catch (Exception e)
        {

        }
    }
}
