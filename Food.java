public class Food {
	private String name;
	private double price;
	private double total;
	
	
	private static final double TAX = 0.075;

	/**
	 * @param name
	 * @param price
	 */
	public Food(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString() {
	    return String.format("%s - %.2f", this.name, this.price);
	}
}
