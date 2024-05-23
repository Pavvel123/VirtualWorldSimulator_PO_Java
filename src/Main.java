//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import UI.MyFrame;
import WorldPack.World;

import javax.swing.*;
import java.awt.EventQueue;

public class Main
{
    public static void main(String[] args)
    {
        int width = Integer.parseInt(JOptionPane.showInputDialog("width:"));
        int height = Integer.parseInt(JOptionPane.showInputDialog("height:"));
        World world = new World(width, height);
        MyFrame myFrame = new MyFrame(height, width, world);
        world.Print();

        world.MakeTurn();
        System.out.println("s");
    }
}