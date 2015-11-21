import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

<<<<<<< HEAD
public class Screen02Applet extends JApplet
{
    //Array holding our food items
    private Food[] entreeMenu, mainMenu, dessertMenu, drinksMenu;
    private Food[] currentMenu; //Changes based on current menu
    private double subTotal = 0.0f;
    
    //The main screens for our application
    private JPanel screen01, screen02, screen03;
    
    /* SCREEN 01 VARIABLES */
    /* END SCREEN 01 VARIABLES */
    
    /* SCREEN 02 VARIABLES */ 
    private JList foodMenuList; //List for selecting food
    private JList orderList; //List for displaying the customer's cart
    private DefaultListModel foodMenuListModel;
    private DefaultListModel orderListModel;
    private JScrollPane foodScrollPane, orderScrollPane; //For scrolling lists
    
    //Middle Section variables
    private JPanel middleSectionContainer; //Container for everything
    private JPanel logoPlaceholder; //Section placeholder
    private JPanel subtotalContainer; //Subtotal wrapper.  Placeholder
    private JComboBox menuSelector; //Combo Box for switching between menus
    private String[] menuSelectorOptions = 
        { "Entree", "Main", "Dessert", "Drinks" };
    
    //Buttons for the final row for ordering and checkout
    private JButton addToOrderButton, checkoutButton, removeFromOrderButton;
    /* END SCREEN 02 VARIABLES */
    
    /* SCREEN 03 VARIABLES */
    /* END SCREEN 03 VARIABLES */
    
    public Screen02Applet()
    {
        //Create our food item lists
        this.initializeFoodItems();
        
        //Initialize our three screens
        this.constructScreen01(); //The splash screen
        this.constructScreen02(); //The ordering screen
        this.constructScreen03(); //The review and checkout screen
        
        this.add(screen02);
    }
    
    //Switch the active screen
    public void SwitchScreens()
    {
        
    }
    
    //Create our food items and add them to the list
    private void initializeFoodItems()
    {
        //Initialize Entree Menu
        entreeMenu = new Food[4];
        entreeMenu[0] = new Food("Entree #1",  1.99);
        entreeMenu[1] = new Food("Entree #2",  7.99);
        entreeMenu[2] = new Food("Entree #3", 12.99);
        entreeMenu[3] = new Food("Entree #4",  8.99);
        
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
        screen01 = new JPanel();
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
        
        logoPlaceholder = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        logoPlaceholder.add(new JLabel("Add Logo Here?"));
        
        menuSelector = new JComboBox(menuSelectorOptions);
        menuSelector.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                switchMenu((String)menuSelector.getSelectedItem());
            }
        });
        subtotalContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JLabel subtotalLabel = new JLabel("Subtotal: $0.00");
        subtotalContainer.add(subtotalLabel);
        
        middleSectionContainer.add(logoPlaceholder);
        middleSectionContainer.add(subtotalContainer);
        middleSectionContainer.add(menuSelector);
        /* Middle Section */
        
        
        addToOrderButton = new JButton("Add to Order");
        addToOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int selectedIndex = foodMenuList.getSelectedIndex();
                addToOrder(selectedIndex, subtotalLabel);
            }
        });
        
        checkoutButton = new JButton("Checkout");
        //Add ActionListener to Switch Scenes
        
        removeFromOrderButton = new JButton("Remove from Order");
        removeFromOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
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
        screen03 = new JPanel();
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
=======
public class Screen02Applet extends JApplet {
	// Temp array for testing JLists
	private String[] foodChoices = { "Cheese Enchildas", "Chorizo con Juevos",
			"Carne Asada Tacos", "Bisteca", "Chicken Enchiladas",
			"Beef Enchiladas", "Chimichangas", "Banderas Mexicano",
			"Oaxaca Quesadillas", "Juevos Rancheros", "Chorizo con Papas",
			"Nachos", "Barbacoa (goat)", "Bison Fajitas", "Cabacitas con Elote",
			"Carnitas frescas", "Pollo Marinada", "Nachos de Chile Picoso",
			"Cerdo Tirada", "Costillas a la Barbacoa", "Floutas",
			"Tamales de Pollo", "Tamales con Chiles Verde", "Mole de Cascadas",
			"Sopa de Norte"};
	
	private double[] foodPrices = { 14.99, 13.99, 14.99, 17.99, 11.99, 11.99,
			12.99, 10.99, 9.99, 12.99, 10.99, 14.99, 21.99, 21.99, 11.99, 8.99,
			10.99, 14.99, 17.99, 21.99, 10.99, 14.99, 14.99, 11.99, 8.99 };
	
	private String[] testMenuItems = { "Menu #1", "Menu #2", "Menu #3",
			"Menu #4" };

	// Two lists for selecting food and for listing the current order
	private JList foodMenuList, orderList;
	private JScrollPane foodScrollPane, orderScrollPane;

	// Panel for formatting the first row's middle section
	private JPanel middleSectionContainer;
	private JPanel logoPlaceholder;
	private JPanel subtotalContainer;
	private JComboBox menuSelector;

	// Buttons for the final row for ordering and checkout
	private JButton addToOrderButton, checkoutButton, removeFromOrderButton;


	
	
	public Screen02Applet() {
		// Screen 2 Prototype

		// Set the screen to have a GridLayout with 1 row and 3 columns.
		this.setLayout(new GridLayout(2, 3, 2, 2));

		foodMenuList = new JList(foodChoices);
		orderList = new JList(foodChoices);

		foodScrollPane = new JScrollPane(foodMenuList);
		orderScrollPane = new JScrollPane(orderList);

		// Limit the user from selecting more than one item per list
		foodMenuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		/* Middle Section */
		middleSectionContainer = new JPanel(new GridLayout(3, 1, 0, 2));

		logoPlaceholder = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		logoPlaceholder.add(new JLabel("Add Logo Here?"));

		menuSelector = new JComboBox(testMenuItems);
		subtotalContainer = new JPanel(
				new FlowLayout(FlowLayout.CENTER, 10, 5));

		JLabel testLabel = new JLabel("Subtotal: $0.00");
		subtotalContainer.add(testLabel);

		middleSectionContainer.add(logoPlaceholder);
		middleSectionContainer.add(subtotalContainer);
		middleSectionContainer.add(menuSelector);
		/* Middle Section */

		addToOrderButton = new JButton("Add to Order");
		checkoutButton = new JButton("Checkout");
		removeFromOrderButton = new JButton("Remove from Order");

		// Add the UI elements to the JApplet
		this.add(foodScrollPane);
		this.add(middleSectionContainer);
		this.add(orderScrollPane);
		this.add(addToOrderButton);
		this.add(checkoutButton);
		this.add(removeFromOrderButton);
	}
}
>>>>>>> refs/remotes/origin/master
