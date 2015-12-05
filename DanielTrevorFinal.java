import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;

public class DanielTrevorFinal extends JApplet {
	// Array holding our food items
	private Food[] entreeMenu, mainMenu, dessertMenu, drinksMenu;
	private Food[] currentMenu; // Changes based on current menu
	public double subTotal = 0.0f, finalSubTotal = 0.0f;

	// The main screens for our application
	private JPanel introScreen, orderScreen, checkoutScreen, screen04;

	/* INTRO SCREEN VARIABLES */
	private JLabel restrauntNameLabel;
	private JButton btnOrder;

	/* CHECKOUT SCREEN VARIABLES */
	private JList foodMenuList; // List for selecting food
	private JList orderList; // List for displaying the customer's cart
	private DefaultListModel foodMenuListModel;
	private DefaultListModel orderListModel;
	private JScrollPane foodScrollPane, orderScrollPane, orderScrollPane2; 
	
	private JPanel middleSectionContainer; // Container for everything
	private JPanel logoPlaceholder; // Section placeholder
	private JPanel subtotalContainer, finalSubTotalContainer,
			finalOrderTotalContainer; // Subtotal wrapper.

	private JComboBox menuSelector; // Combo Box for switching between menus
	private String[] menuSelectorOptions = { "Entree", "Main", "Dessert",
			"Drinks" };

	// Buttons for the final row for ordering and checkout
	private JButton addToOrderButton, checkoutButton, removeFromOrderButton;
	
	/* SPECIAL INSTRUCTIONS VARIABLES */
    private JPanel specialInstructions;
    private Label label_2;
    private JPanel panel_1;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private Label label_1;
    private JCheckBox checkBox;
	
    /* CHECKOUT SCREEN*/
	private Label label, tax;
	private JScrollPane scrollPane;
	private ScrollPane scrollPane_1;
	private Button button;
	private Button button_1;
	private JLabel subtotalCheckoutMenu;
	private JLabel finalOrderTotalLabel, finalSubTotalLabel;
	private JLabel lblThankYouFor;
	private JPanel panel_2;

	public DanielTrevorFinal() {
		setMinimumSize(new Dimension(500, 600));
		// Create our food item lists
		this.initializeFoodItems();

		// Initialize our three screens
		this.constructIntroScreen(); // The splash screen
		this.constructOrderScreen(); // The ordering screen
		this.constructSpecialInstructions();
		this.constructCheckoutScreen(); // The review and checkout screen
		this.constructScreen04(); // Thank you for Ordering Screen

		getContentPane().setLayout(new CardLayout(0, 0));

		getContentPane().add(introScreen, "name_739714584441390");
		introScreen.setVisible(true);
		getContentPane().add(orderScreen, "name_739714638275609");
		orderScreen.setVisible(false);

		specialInstructions.setVisible(false);

		getContentPane().add(specialInstructions, "name_437377097576802");

		getContentPane().add(checkoutScreen, "name_739714706543784");
		checkoutScreen.setVisible(false);

		getContentPane().add(screen04, "name_739714706543784");
		screen04.setLayout(null);

		screen04.setVisible(false);

	}

	// Create our food items and add them to the list
	private void initializeFoodItems() {
		// Initialize Entree Menu
		entreeMenu = new Food[4];
		entreeMenu[0] = new Food("Chips & Dip", 1.99);
		entreeMenu[1] = new Food("Stuffed Jalapenos", 7.99);
		entreeMenu[2] = new Food("Mini Flautas", 12.99);
		entreeMenu[3] = new Food("Salad", 2.99);

		// Initialize Main Menu
		mainMenu = new Food[7];
		mainMenu[0] = new Food("Bisteca con Juevos", 14.99);
		mainMenu[1] = new Food("Barbacoa", 12.90);
		mainMenu[2] = new Food("Carne Asada Tacos", 17.90);
		mainMenu[3] = new Food("Pollo de Marinada", 12.92);
		mainMenu[4] = new Food("Enchlidas", 14.96);
		mainMenu[5] = new Food("Pollo con Elote", 14.97);
		mainMenu[6] = new Food("Flautas", 13.98);

		// Initialize Dessert Menu
		dessertMenu = new Food[7];
		dessertMenu[0] = new Food("Tres Leches Cake", 14.99);
		dessertMenu[1] = new Food("Nieve", 12.90);
		dessertMenu[2] = new Food("Sopa's", 17.90);
		dessertMenu[3] = new Food("Ice Cream Sundae", 12.92);
		dessertMenu[4] = new Food("Chocolate Cake", 7.96);

		// Initialize Drinks Menu
		drinksMenu = new Food[4];
		drinksMenu[0] = new Food("Water", 0.00);
		drinksMenu[1] = new Food("Dr. Pepper", 1.50);
		drinksMenu[2] = new Food("Coca-Cola", 1.50);
		drinksMenu[3] = new Food("Iced Tea", 1.25);

		currentMenu = entreeMenu;

		foodMenuListModel = new DefaultListModel();
		orderListModel = new DefaultListModel();

		// Initialize Food List
		for (int i = 0; i < currentMenu.length; i++) {
			foodMenuListModel.addElement(currentMenu[i]);
		}
	}

	// Create the UI elements here to prevent cluttering the main constructor
	private void constructIntroScreen() {
		introScreen = new JPanel();

		introScreen.setLayout(new BorderLayout(0, 0));

		restrauntNameLabel = new JLabel(
				"Daniel Ashcraft and Trevor Fishers Mexican Restaurant");
		restrauntNameLabel.setForeground(new Color(255, 0, 0));
		restrauntNameLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		restrauntNameLabel.setFont(new Font("Script MT Bold", Font.PLAIN, 18));
		restrauntNameLabel.setPreferredSize(new Dimension(265, 200));
		introScreen.add(restrauntNameLabel, BorderLayout.NORTH);
		restrauntNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

		btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				introScreen.setVisible(false);
				orderScreen.setVisible(true);
			}
		});
		introScreen.add(btnOrder, BorderLayout.SOUTH);
	}

	private void constructOrderScreen() {
		// Screen 2 Prototype
		// Set the screen to have a GridLayout with 1 row and 3 columns.
		orderScreen = new JPanel(new GridLayout(2, 3, 2, 2));

		foodMenuList = new JList(foodMenuListModel);
		orderList = new JList(orderListModel);

		foodScrollPane = new JScrollPane(foodMenuList);
		orderScrollPane = new JScrollPane(orderList);

		// Limit the user from selecting more than one item per list
		foodMenuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		/* Middle Section */
		middleSectionContainer = new JPanel(new GridLayout(3, 1, 0, 2));

		logoPlaceholder = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		logoPlaceholder.add(new JLabel("Add Logo Here?"));

		menuSelector = new JComboBox(menuSelectorOptions);
		menuSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchMenu((String) menuSelector.getSelectedItem());
			}
		});

		subtotalContainer = new JPanel(
				new FlowLayout(FlowLayout.CENTER, 10, 5));

		JLabel subtotalLabel = new JLabel("Subtotal: $0.00");
		subtotalContainer.add(subtotalLabel);

		middleSectionContainer.add(logoPlaceholder);
		middleSectionContainer.add(subtotalContainer);
		middleSectionContainer.add(menuSelector);
		/* Middle Section */

		addToOrderButton = new JButton("Add to Order");
		addToOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = foodMenuList.getSelectedIndex();
				addToOrder(selectedIndex, subtotalLabel);
			}
		});

		checkoutButton = new JButton("Checkout");
		checkoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderScreen.setVisible(false);
				specialInstructions.setVisible(true);
			}
		});
		// Add ActionListener to Switch Scenes

		removeFromOrderButton = new JButton("Remove from Order");
		removeFromOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = orderList.getSelectedIndex();
				removeFromOrder(selectedIndex, subtotalLabel);
			}
		});

		// Add the UI elements to the JApplet
		orderScreen.add(foodScrollPane);
		orderScreen.add(middleSectionContainer);
		orderScreen.add(orderScrollPane);
		orderScreen.add(addToOrderButton);
		orderScreen.add(checkoutButton);
		orderScreen.add(removeFromOrderButton);
	}

	private void constructSpecialInstructions() {

		specialInstructions = new JPanel();

		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(450, 85));
		panel_1.setBorder(new CompoundBorder(
				new BevelBorder(BevelBorder.RAISED, null, null, null, null),
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",
						TitledBorder.LEADING, TitledBorder.TOP, null,
						new Color(0, 0, 0))));
		specialInstructions.add(panel_1);

		label_2 = new Label("Special Instructions Menu");
		label_2.setPreferredSize(new Dimension(300, 60));
		label_2.setAlignment(Label.CENTER);
		label_2.setFont(new Font("Verdana", Font.ITALIC, 18));
		panel_1.add(label_2);

		// delivery checkbox
		checkBox = new JCheckBox("Delivery?");

		specialInstructions.add(checkBox);
		JRadioButton storeOneButton = new JRadioButton(
				"North Broadway Store Location");
		specialInstructions.add(storeOneButton);
		JRadioButton storeTwoButton = new JRadioButton(
				"East Main Store Location");
		specialInstructions.add(storeTwoButton);

		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(400, 100));
		specialInstructions.add(panel_2);
		panel_2.setBorder(new TitledBorder(null, "JPanel title",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		// Text field for special notes to the restaurant
		JTextArea specialNotesField = new JTextArea(
				"Enter special instructions here.");
		specialNotesField.setPreferredSize(new Dimension(260, 44));
		panel_2.add(specialNotesField);

		btnNewButton = new JButton("Back to Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				orderScreen.setVisible(true);
				specialInstructions.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Simplified Arabic", Font.PLAIN, 13));
		specialInstructions.add(btnNewButton);

		btnNewButton_1 = new JButton("Continue to Checkout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkoutScreen.setVisible(true);
				specialInstructions.setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Simplified Arabic", Font.PLAIN, 13));
		specialInstructions.add(btnNewButton_1);
	}

	private void constructCheckoutScreen() {
		checkoutScreen = new JPanel();

		// Button group to choose store locations
		ButtonGroup storeLocationGroup = new ButtonGroup();
		JList jList = new JList(orderListModel);
		JList orderListFinal = jList;

		orderScrollPane2 = new JScrollPane(orderListFinal);
		orderScrollPane2.setPreferredSize(new Dimension(400, 130));
		checkoutScreen.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		label = new Label("Your Order Total is as Follows: ");

		tax = new Label(String.format("Tax Rate: %.02f", Food.getTax()));
		tax.setPreferredSize(new Dimension(85, 25));

		JPanel finalOrderTotalContainer = new JPanel(
				new FlowLayout(FlowLayout.CENTER, 10, 5));
		finalOrderTotalContainer.setPreferredSize(new Dimension(175, 25));

		JPanel finalSubTotalContainer = new JPanel(
				new FlowLayout(FlowLayout.CENTER, 10, 5));

		finalOrderTotalLabel = new JLabel("Final Total:  $0.00");
		finalOrderTotalContainer.add(finalOrderTotalLabel);

		finalSubTotalLabel = new JLabel("Subtotal:  $0.00");
		finalSubTotalContainer.add(finalSubTotalLabel);

		label_1 = new Label("Order Checkout Screen");
		label_1.setPreferredSize(new Dimension(400, 70));
		label_1.setFont(new Font("Verdana", Font.ITALIC, 23));
		label_1.setAlignment(Label.CENTER);
		checkoutScreen.add(label_1);

		checkoutScreen.add(label);

		checkoutScreen.add(orderScrollPane2);

		checkoutScreen.add(finalSubTotalContainer);

		checkoutScreen.add(tax);
		checkoutScreen.add(finalOrderTotalContainer);

		button = new Button("Purchase");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				checkoutScreen.setVisible(false);
				screen04.setVisible(true);
			}
		});

		button_1 = new Button("Go Back to Order Menu");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				checkoutScreen.setVisible(false);
				orderScreen.setVisible(true);

			}
		});

		checkoutScreen.add(button_1);
		checkoutScreen.add(button);

	}

	private void constructScreen04() {

		screen04 = new JPanel();

		lblThankYouFor = new JLabel("Thank You for Your order!!!");
		lblThankYouFor.setPreferredSize(new Dimension(300, 150));
		lblThankYouFor.setFont(new Font("Traditional Arabic", Font.PLAIN, 35));
		lblThankYouFor.setBounds(24, 99, 416, 60);
		screen04.add(lblThankYouFor);

		Button button_2 = new Button("Main Menu");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				screen04.setVisible(false);
				introScreen.setVisible(true);
			}
		});
		button_2.setBounds(181, 204, 70, 22);
		screen04.add(button_2);

	}

	// Screen 02 Events
	private void addToOrder(int index, JLabel subtotalLabel) {
		if (index >= 0 && index < currentMenu.length) {
			if (checkBox.isSelected() == true) {
				subTotal += 4.50;
			}

			subTotal += ((Food) foodMenuListModel.getElementAt(index))
					.getPrice();

			orderListModel.addElement(currentMenu[index]);

			subtotalLabel.setText(String.format("Subtotal: $%.2f", subTotal));

			finalOrderTotalLabel
					.setText(String.format("Final Order Total:  $%.2f",
							subTotal + (subTotal * Food.getTax())));
			finalSubTotalLabel
					.setText(String.format("SubTotal:  $%.2f", subTotal));

		}
	}

	private void removeFromOrder(int index, JLabel subtotalLabel) {
		subTotal -= ((Food) orderListModel.getElementAt(index)).getPrice();
		orderListModel.remove(index);

		subtotalLabel.setText(String.format("Subtotal: $%.2f", subTotal));

		finalOrderTotalLabel.setText(String.format("Final Order Total:  $%.2f",
				subTotal + (subTotal * Food.getTax())));

		finalSubTotalLabel.setText(String.format("SubTotal:  $%.2f", subTotal));

	}

	private void switchMenu(String menuName) {
		// Update the currentMenu and repopulate the food menu list
		if (menuName == "Entree") {
			currentMenu = entreeMenu;
		} else if (menuName == "Main") {
			currentMenu = mainMenu;
		} else if (menuName == "Dessert") {
			currentMenu = dessertMenu;
		} else if (menuName == "Drinks") {
			currentMenu = drinksMenu;
		}

		foodMenuListModel.clear();
		for (int i = 0; i < currentMenu.length; i++) {
			foodMenuListModel.addElement(currentMenu[i]);
		}
	}
}