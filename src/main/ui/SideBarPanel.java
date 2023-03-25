package ui;

import model.Account;
import model.Animal;
import model.NotValidCardException;
import model.Zoo;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SideBarPanel extends JPanel implements ActionListener {
    private Account account;
    private Zoo zoo;
    private List<Animal> animalList;
    private AnimalButtons animalButtons;

    private JButton save;
    private JButton load;
    private JButton favorites;
    private JButton species;
    private JButton donatedTo;
    private JButton critical;
    private JButton endangered;
    private JButton vulnerable;

    private JPanel master;
    private CardLayout cards;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private SpeciesPanel grid;
    private WildlifeRescueUI parentFrame;

    private static final String FILENAME = "./data/saved.json";

    public SideBarPanel(WildlifeRescueUI parentFrame, Account account, Zoo zoo, JLabel welcomeString, JPanel master,
                        CardLayout cl, SpeciesPanel grid) {
        super();
        this.parentFrame = parentFrame;
        this.grid = grid;
        this.account = account;
        this.zoo = zoo;
        this.animalList = zoo.getAnimalList();
        this.animalButtons = new AnimalButtons();
        this.jsonWriter = new JsonWriter(FILENAME);
        this.jsonReader = new JsonReader(FILENAME);
        this.master = master;
        this.cards = cl;

        this.setSize(350, HEIGHT);
        this.setBackground(new Color(124, 155, 98));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(welcomeString);
        initializeOtherButtons(this);
    }

    public void initializeOtherButtons(JPanel panel) {
        species = new JButton("Species");
        species.addActionListener(this);
        species.setHorizontalAlignment(SwingConstants.CENTER);

        save = new JButton("Save");
        save.addActionListener(this);
        save.setHorizontalAlignment(SwingConstants.CENTER);

        load = new JButton("Load");
        load.addActionListener(this);
        load.setHorizontalAlignment(SwingConstants.CENTER);

        initializeListButtons();

        addButtons(panel);
    }

    private void initializeListButtons() {
        favorites = new JButton("Favorites");
        favorites.addActionListener(this);
        favorites.setHorizontalAlignment(SwingConstants.CENTER);

        donatedTo = new JButton("Animals Donated To");
        donatedTo.addActionListener(this);
        donatedTo.setHorizontalAlignment(SwingConstants.CENTER);

        critical = new JButton("Critically Endangered");
        critical.addActionListener(this);
        critical.setHorizontalAlignment(SwingConstants.CENTER);

        endangered = new JButton("Endangered Animals");
        endangered.addActionListener(this);
        endangered.setHorizontalAlignment(SwingConstants.CENTER);

        vulnerable = new JButton("Vulnerable Animals");
        vulnerable.addActionListener(this);
        vulnerable.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void addButtons(JPanel panel) {
        panel.add(species);
        panel.add(save);
        panel.add(load);
        panel.add(favorites);
        panel.add(donatedTo);
        panel.add(critical);
        panel.add(endangered);
        panel.add(vulnerable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == save) {
            this.saveFile();
        } else if (source == load) {
            this.loadFile();
        } else if (source == favorites) {
            handleFavorites();
        } else if (source == species) {
            handleSpecies();
        } else if (source == donatedTo) {
            handleDonated();
        } else if (source == critical) {
            handleStatus("CE");
        } else if (source == endangered) {
            handleEndangered();
        } else if (source == vulnerable) {
            handleStatus("V");
        } else {
            handleAnimalButtons(e);
        }
    }

    private void handleSpecies() {
        cards.first(master);
    }

    private void handleFavorites() {
        FavoritesPanel panel = new FavoritesPanel(grid);

        for (Animal a : this.account.getFavorites()) {
            JButton animalButton = animalButtons.getAnimalButton(a.getName());
            animalButton.addActionListener(this);
            panel.add(animalButton);
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        master.add(scrollPane, "2");
        cards.show(master, "2");
    }

    private void handleDonated() {
        DonatedToPanel panel = new DonatedToPanel();
        for (Animal animal : this.account.getDonatedTo()) {
            JButton animalButton = animalButtons.getAnimalButton(animal.getName());
            animalButton.addActionListener(this);
            panel.add(animalButton);
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        master.add(scrollPane, "3");
        cards.show(master, "3");
    }

    private void handleStatus(String status) {
        CriticalPanel panel = new CriticalPanel();
        for (Animal animal : animalList) {
            if (animal.getStatus() == status) {
                JButton animalButton = animalButtons.getAnimalButton(animal.getName());
                animalButton.addActionListener(this);
                panel.add(animalButton);
            }
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        master.add(scrollPane, "4");
        cards.show(master, "4");
    }

    private void handleEndangered() {
        CriticalPanel panel = new CriticalPanel();
        for (Animal animal : animalList) {
            if (animal.getStatus() == "CE" || animal.getStatus() == "E") {
                JButton animalButton = animalButtons.getAnimalButton(animal.getName());
                animalButton.addActionListener(this);
                panel.add(animalButton);
            }
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        master.add(scrollPane, "4");
        cards.show(master, "4");
    }


    public void handleAnimalButtons(ActionEvent e) {
        grid.handleActions(e);
    }

    private void saveFile() {
        try {
            jsonWriter.openFile();
            jsonWriter.writeToFile(account);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File Not Found.", null,
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadFile() {
        try {
            this.account = jsonReader.read();
            System.out.println("Loaded " + account.getUsername() + "'s account from " + FILENAME);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File Not Found.", null,
                    JOptionPane.ERROR_MESSAGE);
        } catch (NotValidCardException e) {
            JOptionPane.showMessageDialog(null, "Invalid card.", null,
                    JOptionPane.ERROR_MESSAGE);

        }
    }
}