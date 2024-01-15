package ui;

import model.*;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// Adapted from SimpleDrawingPlayer
// Surplus buffet listing application for Listing GUI
public class ListingAppGUI extends JFrame implements ActionListener {
    public static final int WIDTH_FRAME = 750;
    public static final int HEIGHT_FRAME = 700;

    private Restaurants restaurants;

    private static final String JSON_STORE = "./data/listings.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Font font;

    private ImageIcon icon;
    private ImageIcon icon2;

    private JPanel windowPanel;
    private JPanel buttonsPanel;
    private JPanel displayPanel;
    private JPanel restaurantPanel;

    private JLabel back;

    private JButton buttonAdd1;
    private JButton buttonRemove1;
    private JButton buttonAdd2;
    private JButton buttonRemove2;
    private JButton buttonSearch;
    private JButton buttonSave;
    private JButton buttonLoad;
    private JButton buttonQuit;

    // EFFECT: constructor sets up a visual window for the app
    public ListingAppGUI() {
        super("Restaurants");
        initializeFields();
        initializeGraphics();
        initializeWindow();
    }

    // MODIFIES: this
    // EFFECT: initializes the window for the JFrame
    private void initializeWindow() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog();
            }
        });
    }

    // EFFECT: prints out the current instance of log onto the console
    private void printLog() {
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.getDate() + "\n" + next.getDescription() + "\n");
        }
    }

    // MODIFIES: this
    // EFFECT: initializes fields for this class
    public void initializeFields() {
        this.restaurants = new Restaurants();
        this.font = new Font("Arial", Font.PLAIN, 12);
        this.icon = new ImageIcon("./data/icon3.png");

        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);

        this.displayPanel = new JPanel();
        displayPanel.setPreferredSize(new Dimension(WIDTH_FRAME, HEIGHT_FRAME));
        this.windowPanel = new JPanel();
        this.buttonsPanel = new JPanel();
    }

    // MODIFIES: this
    // EFFECT: draws the JFrame window to operate JPanels, JLabels and JButtons
    public void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH_FRAME, HEIGHT_FRAME));
        getContentPane().setBackground(Color.BLACK);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializePanels();
        initializeLabels();
        initializeButtons();

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initialize JPanels for title, window and buttons
    public void initializePanels() {
        windowPanel.setLayout(new BorderLayout());
        windowPanel.setPreferredSize(new Dimension(WIDTH_FRAME, HEIGHT_FRAME));

        add(windowPanel, BorderLayout.CENTER);

        initializeButtonsPanel();
        initializeDisplayPanel();
    }

    // MODIFIES: this
    // EFFECTS: initializes the buttons panel
    public void initializeButtonsPanel() {
        buttonsPanel.setLayout(new GridLayout(4,1));
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: initializes the labels
    public void initializeLabels() {
        initializeDisplayLabel();

        ImageIcon background = new ImageIcon("data/background1.jpg");
        Image img = background.getImage();
        Image temp = img.getScaledInstance(800,500,Image.SCALE_SMOOTH);
        background = new ImageIcon(temp);
        back = new JLabel(background);
        back.setLayout(null);
        back.setBounds(0,0,800,600);
        add(back, BorderLayout.NORTH);


    }

    // MODIFIES: this
    // EFFECTS: display all current restaurants
    public void initializeDisplayLabel() {
        JList<String> restaurantNames = convertToJList();
        restaurantNames.setFont(font);

        displayPanel.removeAll();
        displayPanel.add(restaurantNames);

        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: converts a list of String to a JList, and returns it
    public JList<String> convertToJList() {
        List<String> names = this.restaurants.getRestaurantNames();
        String[] str = new String[names.size()];
        return new JList<>(names.toArray(str));
    }

    // MODIFIES: this
    // EFFECTS: initializes the display panel for the middle window of application, sets colour of background
    public void initializeDisplayPanel() {
        windowPanel.add(displayPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: initializes buttons for the button panel; sets font and colour
    public void initializeButtons() {
        buttonAdd1 = setUpButton("Add offering");
        buttonRemove1 = setUpButton("Remove offering");
        buttonAdd2 = setUpButton("Add restaurant");
        buttonRemove2 = setUpButton("Remove restaurant");
        buttonSave = setUpButton("Save restaurants");
        buttonLoad = setUpButton("Load restaurants");
        buttonSearch = setUpButton("Search restaurant");
        buttonQuit = setUpButton("Quit");

        addButtons();
        registerButtonListenerToButton();
    }

    // MODIFIES: this
    // EFFECTS: adds initialized buttons to button panel
    public void addButtons() {
        buttonsPanel.add(buttonAdd1);
        buttonsPanel.add(buttonRemove1);
        buttonsPanel.add(buttonAdd2);
        buttonsPanel.add(buttonRemove2);
        buttonsPanel.add(buttonSave);
        buttonsPanel.add(buttonLoad);
        buttonsPanel.add(buttonSearch);
        buttonsPanel.add(buttonQuit);
    }

    // MODIFIES: this
    // EFFECTS: sets up and returns each button with set font and position
    public JButton setUpButton(String name) {
        JButton button = new JButton(name);
        //button.setBackground(new Color(88, 169, 252));
        button.setFont(font);

        button.setHorizontalTextPosition(JButton.CENTER);
        return button;
    }

    // MODIFIES: this
    // EFFECTS: registers each button to ActionListener
    public void registerButtonListenerToButton() {
        buttonAdd1.addActionListener(this);
        buttonRemove1.addActionListener(this);
        buttonAdd2.addActionListener(this);
        buttonRemove2.addActionListener(this);
        buttonSearch.addActionListener(this);
        buttonSave.addActionListener(this);
        buttonLoad.addActionListener(this);
        buttonQuit.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: assign each button to an action to be performed when clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAdd1) {
            manageOffering();
        } else if (e.getSource() == buttonRemove1) {
            manageOffering();
        } else if (e.getSource() == buttonAdd2) {
            addRestaurant();
        } else if (e.getSource() == buttonRemove2) {
            removeRestaurant();
        } else if (e.getSource() == buttonSearch) {
            searchRestaurant();
        } else if (e.getSource() == buttonSave) {
            saveRestaurants();
        } else if (e.getSource() == buttonLoad) {
            loadRestaurants();
        } else if (e.getSource() == buttonQuit) {
            printLog();
            System.exit(0);
        }
    }

    // REQUIRES: list of offerings is not empty
    // EFFECTS: display list of offerings for a given restaurant
    public void searchRestaurant() {
        restaurantPanel = new JPanel();
        JTextField name = new JTextField(10);
        name.setFont(font);
        JLabel message = new JLabel("Enter the name of restaurant to search: ");
        message.setFont(font);
        restaurantPanel.add(message);
        restaurantPanel.add(name);

        int reply = JOptionPane.showConfirmDialog(displayPanel, restaurantPanel,
                "New Search", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);

        if (reply == JOptionPane.OK_OPTION) {

            List<String> offeringDetails = restaurants.getRestaurant(name.getText()).getOfferingDetails();
            String[] str = new String[offeringDetails.size()];

            JList<String> display = new JList<>(offeringDetails.toArray(str));
            display.setFont(font);
            displayPanel.removeAll();
            displayPanel.add(display);

            revalidate();
            repaint();
        }
    }

    // MODIFIES: this
    // EFFECTS: access the OfferingGUI
    private void manageOffering() {
        restaurantPanel = new JPanel();
        JTextField name = new JTextField(10);
        name.setFont(font);
        JLabel message = new JLabel("Enter the name of restaurant to manage: ");
        message.setFont(font);
        restaurantPanel.add(message);
        restaurantPanel.add(name);

        int reply = JOptionPane.showConfirmDialog(displayPanel, restaurantPanel,
                "New Offering", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);

        if (reply == JOptionPane.OK_OPTION) {
            Restaurant restaurant = restaurants.getRestaurant(name.getText());
            this.setVisible(false);
            new OfferingGUI(restaurant, this);
        }
    }

    // MODIFIES: this
    // EFFECTS: add restaurant to restaurants
    private void addRestaurant() {
        restaurantPanel = new JPanel();
        JTextField name = new JTextField(10);
        name.setFont(font);
        JTextField contact = new JTextField(10);
        contact.setFont(font);
        JLabel l1 = new JLabel("Enter restaurant name: ");
        l1.setFont(font);
        JLabel l2 = new JLabel("Enter contact info: ");
        l2.setFont(font);
        restaurantPanel.add(l1);
        restaurantPanel.add(name);
        restaurantPanel.add(l2);
        restaurantPanel.add(contact);

        int reply = JOptionPane.showConfirmDialog(displayPanel, restaurantPanel,
                "New restaurant", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);

        if (reply == JOptionPane.OK_OPTION) {
            restaurants.addRestaurant(new Restaurant(name.getText(), contact.getText()));
            displayRestaurantNames();
        }
    }

    // REQUIRES: restaurants is not empty
    // MODIFIES: this
    // EFFECTS: removes the restaurant from current restaurants list.
    public void removeRestaurant() {
        restaurantPanel = new JPanel();
        JTextField name = new JTextField(10);
        name.setFont(font);
        JLabel message = new JLabel("Enter the name of restaurant to delete: ");
        message.setFont(font);
        restaurantPanel.add(message);
        restaurantPanel.add(name);

        int reply = JOptionPane.showConfirmDialog(displayPanel, restaurantPanel,
                "Remove restaurant", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);

        if (reply == JOptionPane.OK_OPTION) {
            restaurants.removeRestaurant(restaurants.getRestaurant(name.getText()));
            displayRestaurantNames();
        }
    }

    // This method references code from CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECT: load restaurants from file
    public void loadRestaurants() {
        try {
            restaurants = jsonReader.read();
            displayRestaurantNames();
            JOptionPane.showMessageDialog(displayPanel, "Loaded restaurants from " + JSON_STORE,
                    "Load Restaurants", JOptionPane.PLAIN_MESSAGE, icon);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(displayPanel, "Unable to read from file: " + JSON_STORE,
                    "Load Restaurants", JOptionPane.PLAIN_MESSAGE, icon);
        }
    }

    // MODIFIES: this
    // EFFECT: display the restaurant names on displayPanel
    public void displayRestaurantNames() {
        JList<String> restaurantNames = convertToJList();
        restaurantNames.setFont(font);

        displayPanel.removeAll();
        displayPanel.add(restaurantNames);
        revalidate();
        repaint();
    }

    // This method references code from CPSC210/JsonSerializationDemo
    // EFFECT: saves the restaurants to JSON_STORE file
    public void saveRestaurants() {
        try {
            jsonWriter.open();
            jsonWriter.write(restaurants);
            jsonWriter.close();

            JOptionPane.showMessageDialog(displayPanel, "Saved restaurants to " + JSON_STORE,
                    "Save Restaurants", JOptionPane.PLAIN_MESSAGE, icon);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(displayPanel, "Unable to write to file: " + JSON_STORE,
                    "Save Restaurants", JOptionPane.PLAIN_MESSAGE, icon);
        }
    }

}
