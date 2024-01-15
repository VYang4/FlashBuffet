package ui;

import model.Offering;
import model.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Adapted from SimpleDrawingPlayer
// Surplus buffet listing application for offering GUI
public class OfferingGUI extends JFrame implements ActionListener {

    public static final int WIDTH_FRAME = 600;
    public static final int HEIGHT_FRAME = 500;

    private Restaurant restaurant;
    private ListingAppGUI listingGUI;

    private Font font1;

    private ImageIcon icon;

    private JPanel mainPanel;
    private JPanel labelPanel;
    private JPanel buttonsPanel;
    private JPanel displayPanel;
    private JPanel offeringPanel;

    private JLabel label1;

    private JButton buttonAddOffering;
    private JButton buttonRemoveOffering;
    private JButton buttonBack;

    // EFFECT: constructor sets up a visual window of given restaurant
    public OfferingGUI(Restaurant restaurant, ListingAppGUI listingGUI) {
        super("FlashBuffet");
        this.restaurant = restaurant;
        this.listingGUI = listingGUI;

        initializeOfferingFields();
        initializeOfferingGraphics();
    }

    // MODIFIES: this
    // EFFECT: sets fields for OfferingGUI class
    //         this method is called by the OfferingGUI constructor
    public void initializeOfferingFields() {
        this.font1 = new Font("Arial", Font.PLAIN, 11);
        this.icon = new ImageIcon("");

        this.displayPanel = new JPanel();
        this.labelPanel = new JPanel();
        this.mainPanel = new JPanel();
        this.buttonsPanel = new JPanel();
    }

    // MODIFIES: this
    // EFFECT: draws the JFrame window where OfferingGUI will operate, populates JPanels, JLabels and JButtons
    public void initializeOfferingGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH_FRAME, HEIGHT_FRAME));
        getContentPane().setBackground(Color.BLACK);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(restaurant.getName());

        initializePanels();
        initializeLabels();
        initializeButtons();

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initialize JPanels for title, window and buttons
    public void initializePanels() {
        labelPanel.setLayout(new BorderLayout());
        labelPanel.setBackground(Color.WHITE);
        labelPanel.setPreferredSize(new Dimension(WIDTH_FRAME,70));

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 220));

        add(labelPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        initializeButtonsPanel();
        initializeDisplayPanel();
    }

    // MODIFIES: this
    // EFFECTS: initializes the buttons panel
    public void initializeButtonsPanel() {
        buttonsPanel.setLayout(new GridLayout(4,1));
        buttonsPanel.setBackground(new Color(245, 245, 220));
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: initializes the display panel for the middle window of application, sets colour of background
    public void initializeDisplayPanel() {
        displayPanel.setBackground(new Color(245, 245, 220));

        mainPanel.add(displayPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: initializes the labels
    public void initializeLabels() {
        label1 = new JLabel(restaurant.getName());
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setForeground(new Color(139,0,0));
        label1.setFont(new Font("Arial", Font.PLAIN, 30));
        label1.setBounds(0,60,0,0);
        labelPanel.add(label1, BorderLayout.AFTER_LAST_LINE);
    }

    // MODIFIES: this
    // EFFECTS: initializes buttons for the button panel; sets font and colour
    public void initializeButtons() {
        buttonAddOffering = setUpButton("Add offering");
        buttonRemoveOffering = setUpButton("Remove offering");
        buttonBack = setUpButton("Back");

        addButtons();
        registerButtonListener();
    }

    // MODIFIES: this
    // EFFECTS: sets up and returns each button with set font and position
    public JButton setUpButton(String name) {
        JButton button = new JButton(name);
        button.setBackground(new Color(245, 245, 220));
        button.setFont(font1);
        button.setHorizontalTextPosition(JButton.CENTER);
        return button;
    }

    // MODIFIES: this
    // EFFECTS: adds initialized buttons to button panel
    public void addButtons() {
        buttonsPanel.add(buttonAddOffering);
        buttonsPanel.add(buttonRemoveOffering);
        buttonsPanel.add(buttonBack);
    }

    // MODIFIES: this
    // EFFECTS: registers each button to ActionListener
    public void registerButtonListener() {
        buttonAddOffering.addActionListener(this);
        buttonRemoveOffering.addActionListener(this);
        buttonBack.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: assign each button to an action to be performed when clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAddOffering) {
            addNewOffering();
        } else if (e.getSource() == buttonRemoveOffering) {
            removeOffering();
        } else if (e.getSource() == buttonBack) {
            this.dispose();
            listingGUI.setVisible(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the new offering to current restaurant.
    public void addNewOffering() {
        offeringPanel = new JPanel();
        JTextField id = new JTextField(10);
        JTextField price = new JTextField(10);
        JTextField time = new JTextField(10);
        JTextField location = new JTextField(10);
        JTextField foodType = new JTextField(10);
        id.setFont(font1);
        price.setFont(font1);
        time.setFont(font1);
        location.setFont(font1);
        foodType.setFont(font1);
        JLabel l1 = new JLabel("Enter ID: ");
        l1.setFont(font1);
        JLabel l2 = new JLabel("Enter price: ");
        l2.setFont(font1);
        JLabel l3 = new JLabel("Enter time: ");
        l3.setFont(font1);
        JLabel l4 = new JLabel("Enter location: ");
        l4.setFont(font1);
        JLabel l5 = new JLabel("Enter food type: ");
        l5.setFont(font1);

        addOfferingToPanel(id, price, time, location, foodType, l1, l2, l3, l4, l5);
        addOffering(id, price, time, location, foodType);
    }

    // MODIFIES: this
    // EFFECTS: adds components required for new Offering to Offering panel
    private void addOfferingToPanel(JTextField id, JTextField price, JTextField time, JTextField location,
                                    JTextField foodType, JLabel l1, JLabel l2, JLabel l3, JLabel l4, JLabel l5) {
        offeringPanel.add(l1);
        offeringPanel.add(id);
        offeringPanel.add(l2);
        offeringPanel.add(price);
        offeringPanel.add(l3);
        offeringPanel.add(time);
        offeringPanel.add(l4);
        offeringPanel.add(location);
        offeringPanel.add(l5);
        offeringPanel.add(foodType);
    }

    // MODIFIES: this
    // EFFECTS: add the offering to restaurant
    private void addOffering(JTextField id, JTextField price, JTextField time,
                                JTextField location, JTextField foodType) {
        int reply = JOptionPane.showConfirmDialog(displayPanel, offeringPanel,
                "New offering", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);

        if (reply == JOptionPane.OK_OPTION) {
            restaurant.addOffering(new Offering(Integer.parseInt(id.getText()), Integer.parseInt(price.getText()),
                    time.getText(), location.getText(), foodType.getText()));
            displayOfferings();
        }
    }

    // REQUIRES: the given offering exists
    // MODIFIES: this
    // EFFECTS: removes the offering from current restaurant.
    public void removeOffering() {
        offeringPanel = new JPanel();
        JTextField offeringID = new JTextField(10);
        offeringID.setFont(font1);
        JLabel message = new JLabel("Enter the ID of offering to delete: ");
        message.setFont(font1);
        offeringPanel.add(message);
        offeringPanel.add(offeringID);

        int reply = JOptionPane.showConfirmDialog(displayPanel, offeringPanel,
                "Remove Offering", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);

        if (reply == JOptionPane.OK_OPTION) {
            restaurant.removeOffering(restaurant.getOffering(Integer.parseInt(offeringID.getText())));
            displayOfferings();
        }
    }

    // MODIFIES: this
    // EFFECT: display the loaded wishlist on displayPanel
    public void displayOfferings() {
        JList<String> currentOfferings = convertToJList();
        currentOfferings.setFont(font1);

        displayPanel.removeAll();
        displayPanel.add(currentOfferings);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: converts a list of String to a JList, and returns it
    public JList<String> convertToJList() {
        List<String> names = this.restaurant.getOfferingDetails();
        String[] str = new String[names.size()];
        return new JList<>(names.toArray(str));
    }
}
