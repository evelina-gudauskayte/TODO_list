package DESIGN;

import javax.swing.*;
import java.awt.*;

public class DesignedButton extends JButton {
    public DesignedButton(String label){
        super(label);
        this.setBorder(new RoundedBorder(50));
        //this.setForeground(Color.cyan);
    }
}
