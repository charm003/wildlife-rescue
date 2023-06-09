package ui;

import javax.swing.*;
import java.awt.*;


//class that represents the panel beside the sidebar that shows the list of species in the accounts favorites list.
public class AnimalListPanel extends JPanel {

    //EFFECTS: constructs a favorites panel
    public AnimalListPanel() {
        super();
        this.setOpaque(true);
        this.setSize(WildlifeRescueUI.WIDTH, WildlifeRescueUI.HEIGHT);
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(51, 94, 17));
        this.setVisible(true);
        this.setBounds(500, 900, 750, 900);
        this.setLayout(new GridLayout(getComponentCount(), 1));
    }
}
