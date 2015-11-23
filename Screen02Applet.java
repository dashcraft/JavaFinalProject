import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class Screen02Applet extends JApplet {
	// Array holding our food items
	private Food[] entreeMenu, mainMenu, dessertMenu, drinksMenu;
	private Food[] currentMenu; // Changes based on current menu
	private double subTotal = 0.0f, finalSubTotal = 0.0f;

	// The main screens for our application
	private JPanel screen01, screen02, screen03, screen04;

	/* SCREEN 01 VARIABLES */
	/* END SCREEN 01 VARIABLES */

	/* SCREEN 02 VARIABLES */
	private JList foodMenuList; // List for selecting food
	private JList orderList; // List for displaying the customer's cart
	private DefaultListModel foodMenuListModel;
	private DefaultListModel orderListModel;
	private JScrollPane foodScrollPane, orderScrollPane, orderScrollPane2; // For
																			// scrolling
																			// lists

	// Middle Section variables
	private JPanel middleSectionContainer; // Container for everything
	private JPanel logoPlaceholder; // Section placeholder
	private JPanel subtotalContainer, finalSubTotalContainer,
			finalOrderTotalContainer; // Subtotal wrapper.
	// Placeholder
	private JComboBox menuSelector; // Combo Box for switching between menus
	private String[] menuSelectorOptions = { "Entree", "Main", "Dessert",
			"Drinks" };

	// Buttons for the final row for ordering and checkout
	private JButton addToOrderButton, checkoutButton, removeFromOrderButton;
	private JLabel lblNewLabel;
	private JButton btnOrder;
	private Label label, tax;
	private JScrollPane scrollPane;
	private ScrollPane scrollPane_1;
	private Button button;
	private Button button_1;
	private JLabel subtotalCheckoutMenu;
	private JLabel finalOrderTotalLabel, finalSubTotalLabel;
	private JLabel lblThankYouFor;

	/* END SCREEN 02 VARIABLES */

	/* SCREEN 03 VARIABLES */
	/* END SCREEN 03 VARIABLES */

	public Screen02Applet() {
		// Create our food item lists
		this.initializeFoodItems();

		// Initialize our three screens
		this.constructScreen01(); // The splash screen
		this.constructScreen02(); // The ordering screen
		this.constructScreen03(); // The review and checkout screen
		this.constructScreen04(); // Thank you for Ordering Screen

		getContentPane().setLayout(new CardLayout(0, 0));

		getContentPane().add(screen01, "name_739714584441390");

		screen01.setVisible(true);
		getContentPane().add(screen02, "name_739714638275609");
		screen02.setVisible(false);
		getContentPane().add(screen03, "name_739714706543784");
		screen03.setVisible(false);

		getContentPane().add(screen04, "name_739714706543784");
		screen04.setLayout(null);

		screen04.setVisible(false);

	}

	// Create our food items and add them to the list
	private void initializeFoodItems() {
		// Initialize Entree Menu
		entreeMenu = new Food[4];
		entreeMenu[0] = new Food("Entree #1", 1.99);
		entreeMenu[1] = new Food("Entree #2", 7.99);
		entreeMenu[2] = new Food("Entree #3", 12.99);
		entreeMenu[3] = new Food("Entree #4", 8.99);

		// Initialize Main Menu
		mainMenu = new Food[7];
		mainMenu[0] = new Food("Main Item #1", 14.99);
		mainMenu[1] = new Food("Main Item #2", 12.90);
		mainMenu[2] = new Food("Main Item #3", 17.90);
		mainMenu[3] = new Food("Main Item #4", 12.92);
		mainMenu[4] = new Food("Main Item #5", 14.96);
		mainMenu[5] = new Food("Main Item #6", 14.97);
		mainMenu[6] = new Food("Main Item #7", 13.98);

		// Initialize Dessert Menu
		dessertMenu = new Food[7];
		dessertMenu[0] = new Food("Dessert #1", 14.99);
		dessertMenu[1] = new Food("Dessert #2", 12.90);
		dessertMenu[2] = new Food("Dessert #3", 17.90);
		dessertMenu[3] = new Food("Dessert #4", 12.92);
		dessertMenu[4] = new Food("Dessert #5", 14.96);

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
	private void constructScreen01() {
		screen01 = new JPanel();

		screen01.setLayout(new BorderLayout(0, 0));

		lblNewLabel = new JLabel("Daniel and Trevors Mexican Restaurant");
		screen01.add(lblNewLabel, BorderLayout.NORTH);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				screen01.setVisible(false);
				screen02.setVisible(true);
			}
		});
		screen01.add(btnOrder, BorderLayout.SOUTH);
	}

	private void constructScreen02() {
		// Screen 2 Prototype
		// Set the screen to have a GridLayout with 1 row and 3 columns.
		screen02 = new JPanel(new GridLayout(2, 3, 2, 2));

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
				screen02.setVisible(false);
				screen03.setVisible(true);
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
		screen02.add(foodScrollPane);
		screen02.add(middleSectionContainer);
		screen02.add(orderScrollPane);
		screen02.add(addToOrderButton);
		screen02.add(checkoutButton);
		screen02.add(removeFromOrderButton);
	}

	private void constructScreen03() {
		screen03 = new JPanel();

		button_1 = new Button("Go Back to Order Menu");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				screen03.setVisible(false);
				screen02.setVisible(true);

			}
		});

		JList jList = new JList(orderListModel);
		JList orderListFinal = jList;

		orderScrollPane2 = new JScrollPane(orderListFinal);
		screen03.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		label = new Label("Your Order Total is as Follows: ");

		tax = new Label(String.format("Tax Rate: %.02f", Food.getTax()));

		JPanel finalOrderTotalContainer = new JPanel(
				new FlowLayout(FlowLayout.CENTER, 10, 5));

		JPanel finalSubTotalContainer = new JPanel(
				new FlowLayout(FlowLayout.CENTER, 10, 5));

		finalOrderTotalLabel = new JLabel("Final Total:  $0.00");
		finalOrderTotalContainer.add(finalOrderTotalLabel);

		finalSubTotalLabel = new JLabel("Subtotal:  $0.00");
		finalSubTotalContainer.add(finalSubTotalLabel);

		screen03.add(label);

		screen03.add(orderScrollPane2);

		screen03.add(finalSubTotalContainer);

		screen03.add(tax);

		screen03.add(finalOrderTotalContainer);

		button = new Button("Purchase");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				screen03.setVisible(false);
				screen04.setVisible(true);
			}
		});
		screen03.add(button);

		screen03.add(button_1);

	}

	private void constructScreen04() {

		screen04 = new JPanel();

		lblThankYouFor = new JLabel("Thank You for Your order!!!");
		lblThankYouFor.setFont(new Font("Traditional Arabic", Font.PLAIN, 35));
		lblThankYouFor.setBounds(24, 99, 416, 60);
		screen04.add(lblThankYouFor);

		Button button_2 = new Button("Main Menu");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				screen04.setVisible(false);
				screen01.setVisible(true);
			}
		});
		button_2.setBounds(181, 204, 70, 22);
		screen04.add(button_2);

	}

	// Screen 02 Events
	private void addToOrder(int index, JLabel subtotalLabel) {
		if (index >= 0 && index < currentMenu.length) {
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