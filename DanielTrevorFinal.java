import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DanielTrevorFinal extends JApplet
{
    //Array holding our food items
    private Food[] entreeMenu, mainMenu, dessertMenu, drinksMenu;
    private Food[] currentMenu; //Changes based on current menu
    private double subTotal = 0.0f;
    private double totalWithTax = 0.0f;
    
    //The main screens for our application
    private JPanel screen01, screen02, screen03;
    
    /* SCREEN 01 VARIABLES */
    private JLabel restaurantLogo;
    private JButton beginOrderButton;
    /* END SCREEN 01 VARIABLES */
    
    /* SCREEN 02 VARIABLES */ 
    private JList foodMenuList; //List for selecting food
    private JList orderList; //List for displaying the customer's cart
    private DefaultListModel foodMenuListModel;
    private DefaultListModel orderListModel;
    private JScrollPane foodScrollPane, orderScrollPane; //For scrolling lists
    
    //Middle Section variables
    private JPanel middleSectionContainer; //Container for everything
    private JPanel subtotalContainer; //Subtotal wrapper.  Placeholder
    private JComboBox menuSelector; //Combo Box for switching between menus
    private String[] menuSelectorOptions = 
        { "Entree", "Main", "Dessert", "Drinks" };
    
    //Buttons for the final row for ordering and checkout
    private JButton addToOrderButton, checkoutButton, removeFromOrderButton;
    /* END SCREEN 02 VARIABLES */
    
    /* SCREEN 03 VARIABLES */
    private JTextArea specialNotesField;
    private JCheckBox deliveryCheckBox;
    private ButtonGroup storeLocationGroup;
    private JRadioButton storeOneButton, storeTwoButton;
    private JButton editOrderButton, calculateTotalButton;
    private JLabel totalDisplayLabel;
    /* END SCREEN 03 VARIABLES */
    
    public DanielTrevorFinal()
    {
        //Create our food item lists
        this.initializeFoodItems();
        
        //Initialize our three screens
        this.constructScreen01(); //The splash screen
        this.constructScreen02(); //The ordering screen
        this.constructScreen03(); //The review and checkout screen
        
        this.add(screen01);
    }
    
    //Switch the active screen
    public void SwitchScreens(String screenName, String activeScreen)
    {
        //Remove previous screen from parent JApplet
        if (activeScreen == "Screen01")
            this.remove(screen01);
        else if (activeScreen == "Screen02")
            this.remove(screen02);
        else if (activeScreen == "Screen03")
            this.remove(screen03);
        
        //Add new screen to parent JApplet
        if (screenName == "Screen01")
            this.add(screen01);
        else if (screenName == "Screen02")
            this.add(screen02);
        else if (screenName == "Screen03")
            this.add(screen03);
        
        //Call validate and repaint to fix screens not transitioning properly
        this.validate();
        this.repaint();
    }
    
    //Create our food items and add them to the list
    private void initializeFoodItems()
    {
        //Initialize Entree Menu
        entreeMenu = new Food[4];
        entreeMenu[0] = new Food("Entree #1",  1.99);
        entreeMenu[1] = new Food("Entree #2",  7.99);
        entreeMenu[2] = new Food("Entree #3", 12.99);
        entreeMenu[3] = new Food("Entree #4",  10.00);
        
        //Initialize Main Menu
        mainMenu = new Food[7];
        mainMenu[0] = new Food("Main Item #1", 14.99);
        mainMenu[1] = new Food("Main Item #2", 12.90);
        mainMenu[2] = new Food("Main Item #3", 17.90);
        mainMenu[3] = new Food("Main Item #4", 12.92);
        mainMenu[4] = new Food("Main Item #5", 14.96);
        mainMenu[5] = new Food("Main Item #6", 14.97);
        mainMenu[6] = new Food("Main Item #7", 13.98);
        
        //Initialize Dessert Menu
        dessertMenu = new Food[7];
        dessertMenu[0] = new Food("Dessert #1", 14.99);
        dessertMenu[1] = new Food("Dessert #2", 12.90);
        dessertMenu[2] = new Food("Dessert #3", 17.90);
        dessertMenu[3] = new Food("Dessert #4", 12.92);
        dessertMenu[4] = new Food("Dessert #5", 14.96);
        
        //Initialize Drinks Menu
        drinksMenu = new Food[4];
        drinksMenu[0] = new Food("Water", 0.00);
        drinksMenu[1] = new Food("Dr. Pepper", 1.50);
        drinksMenu[2] = new Food("Coca-Cola", 1.50);
        drinksMenu[3] = new Food("Iced Tea", 1.25);
        
        currentMenu = entreeMenu;
        
        foodMenuListModel = new DefaultListModel();
        orderListModel = new DefaultListModel();
        
        //Initialize Food List
        for (int i = 0; i < currentMenu.length; i++)
        {
            foodMenuListModel.addElement(currentMenu[i]);
        }
    }
    
    //Create the UI elements here to prevent cluttering the main constructor
    private void constructScreen01()
    {
        screen01 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        
        //Restaurant Name
        String name = "Daniel Ashcraft and Trevor Fisher's Mexican Restaurant";
        restaurantLogo = new JLabel(name);
        
        beginOrderButton = new JButton("Begin Order");
        beginOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Switch to Screen01 from Screen02
                SwitchScreens("Screen02", "Screen01");
            }
        });
        
        screen01.add(restaurantLogo);
        screen01.add(beginOrderButton);
    }
    private void constructScreen02()
    {
        //Screen 2 Prototype
        //Set the screen to have a GridLayout with 1 row and 3 columns.
        screen02 = new JPanel(new GridLayout(2, 3, 2, 2));
        
        foodMenuList = new JList(foodMenuListModel);
        orderList = new JList(orderListModel);
        
        foodScrollPane = new JScrollPane(foodMenuList);
        orderScrollPane = new JScrollPane(orderList);
        
        //Limit the user from selecting more than one item per list
        foodMenuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        /* Middle Section */
        middleSectionContainer = new JPanel(new GridLayout(3, 1, 0, 2));
        
        menuSelector = new JComboBox(menuSelectorOptions);
        menuSelector.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e)
            {
                //Switch the current menu
                switchMenu((String)menuSelector.getSelectedItem());
            }
        });
        subtotalContainer = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JLabel subtotalLabel = new JLabel("Subtotal: $0.00");
        subtotalContainer.add(subtotalLabel);
        
        middleSectionContainer.add(subtotalContainer);
        middleSectionContainer.add(menuSelector);
        /* Middle Section */
        
        addToOrderButton = new JButton("Add to Order");
        addToOrderButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e)
            {
                //Add selected item to the order
                int selectedIndex = foodMenuList.getSelectedIndex();
                addToOrder(selectedIndex, subtotalLabel);
            }
        });
        
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e)
            {
                //Switch to Screen02 from Screen03
                SwitchScreens("Screen03", "Screen02");
            }
        });
        
        removeFromOrderButton = new JButton("Remove from Order");
        removeFromOrderButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e)
            {
                //Remove selected item from the order
                int selectedIndex = orderList.getSelectedIndex();
                removeFromOrder(selectedIndex, subtotalLabel);
            }
        });
        
        //Add the UI elements to the JApplet
        screen02.add(foodScrollPane);
        screen02.add(middleSectionContainer);
        screen02.add(orderScrollPane);
        screen02.add(addToOrderButton);
        screen02.add(checkoutButton);
        screen02.add(removeFromOrderButton);
    }
    private void constructScreen03()
    {
        screen03 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        
        //Button group to choose store locations
        storeLocationGroup = new ButtonGroup();
        storeOneButton = new JRadioButton("North Broadway Store Location");
        storeTwoButton = new JRadioButton("East Main Store Location");
        
        //Add radio buttons to button group
        storeLocationGroup.add(storeOneButton);
        storeLocationGroup.add(storeTwoButton);
        
        //delivery checkbox
        deliveryCheckBox = new JCheckBox("Delivery?");
        
        //Text field for special notes to the restaurant
        specialNotesField = new JTextArea("Enter special instructions here.");
        
        //Button for going back and editing the order
        editOrderButton = new JButton("Edit Order");
        editOrderButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e)
            {
                //Switch to Screen02 from Screen03
                SwitchScreens("Screen02", "Screen03");
            }
        });
        
        //Button to calculate the total with tax and delivery charge
        calculateTotalButton = new JButton("Calculate Total");
        calculateTotalButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e)
            {
                calculateTotal();
            }
        });
        
        //Label to display the total
        totalDisplayLabel = new JLabel("Total: Not Yet Calculated");
        
        //Add UI Elements to the screen
        screen03.add(storeOneButton);
        screen03.add(storeTwoButton);
        screen03.add(deliveryCheckBox);
        screen03.add(specialNotesField);
        screen03.add(editOrderButton);
        screen03.add(calculateTotalButton);
        screen03.add(totalDisplayLabel);
    }
    
    private void calculateTotal()
    {
        //Reset to 0.0
        totalWithTax = 0.0f;
        
        //Loop through order adding up each item
        for (int i = 0; i < orderListModel.size(); i++)
            totalWithTax += ((Food)orderListModel.getElementAt(i)).getPrice();
        
        //Add delivery charge if checkbox is checked
        if (deliveryCheckBox.isSelected())
            totalWithTax += 4.0;
        
        //Add 7.5% tax
        totalWithTax += totalWithTax * 0.075;
        
        //Update JLabel with corrent info
        totalDisplayLabel.setText(String.format("Total: $%.2f", totalWithTax));
    }
    
    //Screen 02 Events
    private void addToOrder(int index, JLabel subtotalLabel)
    {
        if (index >= 0 && index < currentMenu.length)
        {
           subTotal += ((Food)foodMenuListModel.getElementAt(index)).getPrice();
           orderListModel.addElement(currentMenu[index]);
            
           subtotalLabel.setText(String.format("Subtotal - $%.2f", subTotal));
        }
    }
    private void removeFromOrder(int index, JLabel subtotalLabel)
    {
        subTotal -= ((Food)orderListModel.getElementAt(index)).getPrice();
        orderListModel.remove(index);
        
        subtotalLabel.setText(String.format("Subtotal - $%.2f", subTotal));
    }
    private void switchMenu(String menuName)
    {
        //Update the currentMenu and repopulate the food menu list
        if (menuName == "Entree")
        {
            currentMenu = entreeMenu;
        }
        else if (menuName == "Main")
        {
            currentMenu = mainMenu;
        }
        else if (menuName == "Dessert")
        {
            currentMenu = dessertMenu;
        }
        else if (menuName == "Drinks")
        {
            currentMenu = drinksMenu;
        }
        
        foodMenuListModel.clear();
        for (int i = 0; i < currentMenu.length; i++)
        {
            foodMenuListModel.addElement(currentMenu[i]);
        }
    }
}