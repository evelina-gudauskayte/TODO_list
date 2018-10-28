package DESIGN;

import javax.swing.*;

public class DesignedFrames extends JFrame {
    public DesignedFrames(){
        super();
        ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"\\src\\DESIGN\\icon.png");
        this.setIconImage(icon.getImage());
    }
}
