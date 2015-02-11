import Controller.*;
import View.*;
import Model.*;
import javax.swing.*;

public class Main extends JFrame
{
    public static void main(String[] args)
    {//
        Model m = new Model();
        View v = new View(m);
        new Controller(m, v);

        v.setVisible(true);
    }
}
