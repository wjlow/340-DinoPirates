import View.ModeSelect;
import Model.ModelInterface;
import javax.swing.*;

public class Main extends JFrame
{
    public static void main(String[] args)
    {
        ModelInterface m = new ModelInterface();
        ModeSelect ms = new ModeSelect(m);

        ms.setVisible(true);
    }
}
