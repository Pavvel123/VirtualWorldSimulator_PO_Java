package UI;

import AbstractClasses.Organism;
import Animals.*;
import Plants.*;
import WorldPack.MyButton;
import WorldPack.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.*;

public class MyFrame extends JFrame
{
    public World world;
    public MyFrame(int rows, int cols, World world)
    {
        super("Hello World!");
        this.world = world;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1550, 820);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(rows, cols));
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 1));
        JPanel logsPanel = new JPanel();

        JButton saveButton = new JButton("SAVE");
        buttonsPanel.add(saveButton);
        saveButton.addActionListener((e) ->
        {
            File file = new File("SAVE.txt");
            world.SaveToFile(file);
        });
        JButton loadButton = new JButton("LOAD");
        buttonsPanel.add(loadButton);
        loadButton.addActionListener((e) ->
        {
            for (Organism organism : world.GetOrganisms())
            {
                organism.EreasePrint();
            }
            File file = new File("SAVE.txt");
            world.LoadFromFile(file);
            world.Print();
        });
        JButton nextRoundButton = new JButton("Next Round");
        buttonsPanel.add(nextRoundButton);
        nextRoundButton.addActionListener((e) ->
        {
            world.MakeTurn();
            world.logs = "";
        });

        JTextArea textArea = new JTextArea(40, 30);
        world.logTextArea = textArea;
        textArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(textArea);
        logsPanel.add(logScrollPane);

        JPanel selectPanel = new JPanel();
        selectPanel.add(new JLabel("Choose new organism:"));
        String[] organisms = {"Antelope", "Fox", "Sheep", "Tortoise", "Wolf", "Deadly Nightshade", "Grass", "Guarana", "Sonchus", "Sosnowsky's Hogweed"};
        JComboBox organismSelection = new JComboBox<>(organisms);
        selectPanel.add(organismSelection);


        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
            {
                MyButton button = new MyButton(r + 1, c + 1);
                //button.addKeyListener(new MyKeyListener(world));
                int finalR = r + 1;
                int finalC = c + 1;
                button.addActionListener((e) ->
                {
                        String selectedOrganism = (String) organismSelection.getSelectedItem();
                        if (selectedOrganism.equals("Grass"))
                        {
                            Grass newGrass = new Grass(finalC, finalR, world);
                            world.AddOrganism(newGrass);
                            newGrass.Print();
                        }
                        else if (selectedOrganism.equals("Wolf"))
                        {
                            Wolf newWolf = new Wolf(finalC, finalR, world);
                            world.AddOrganism(newWolf);
                            newWolf.Print();
                        }
                        else if (selectedOrganism.equals("Sheep"))
                        {
                            Sheep newSheep = new Sheep(finalC, finalR, world);
                            world.AddOrganism(newSheep);
                            newSheep.Print();
                        }
                        else if (selectedOrganism.equals("Guarana"))
                        {
                            Guarana newGuarana = new Guarana(finalC, finalR, world);
                            world.AddOrganism(newGuarana);
                            newGuarana.Print();
                        }
                        else if (selectedOrganism.equals("Sosnowsky's Hogweed"))
                        {
                            SosnowskysHogweed newSos = new SosnowskysHogweed(finalC, finalR, world);
                            world.AddOrganism(newSos);
                            newSos.Print();
                        }
                        else if (selectedOrganism.equals("Sonchus"))
                        {
                            Sonchus newSon = new Sonchus(finalC, finalR, world);
                            world.AddOrganism(newSon);
                            newSon.Print();
                        }
                        else if (selectedOrganism.equals("Deadly Nightshade"))
                        {
                            DeadlyNightshade newDed = new DeadlyNightshade(finalC, finalR, world);
                            world.AddOrganism(newDed);
                            newDed.Print();
                        }
                        else if (selectedOrganism.equals("Tortoise"))
                        {
                            Tortoise newTor = new Tortoise(finalC, finalR, world);
                            world.AddOrganism(newTor);
                            newTor.Print();
                        }
                        else if (selectedOrganism.equals("Fox"))
                        {
                            Fox newFox = new Fox(finalC, finalR, world);
                            world.AddOrganism(newFox);
                            newFox.Print();
                        }
                        else if (selectedOrganism.equals("Antelope"))
                        {
                            Antelope newAntelope = new Antelope(finalC, finalR, world);
                            world.AddOrganism(newAntelope);
                            newAntelope.Print();
                        }

                });
                button.setBackground(new Color(255,255,255, 255));
                button.setText("");
                boardPanel.add(button);
                world.AddButton(button);
            }
        }
        setTitle("Virtual World Simulator - Paweł Kurpiewski 198203");
        //pack();
        add(boardPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.WEST);
        add(logsPanel, BorderLayout.EAST);
        add(selectPanel, BorderLayout.NORTH);
        setVisible(true);
    }
}
