package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FavoritesPanel extends JPanel implements ActionListener {
    private SpeciesPanel grid;

    public FavoritesPanel(SpeciesPanel grid) {
        super();
        this.setOpaque(true);
        this.setSize(WildlifeRescueUI.WIDTH, WildlifeRescueUI.HEIGHT);
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(51, 94, 17));
        this.setVisible(true);
        this.setBounds(500, 900, 750, 900);
        this.setLayout(new GridLayout(11, 1));
        this.grid = grid;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        grid.handleActions(e);
    }
}