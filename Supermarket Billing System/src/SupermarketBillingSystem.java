import java.util.*;

class Item {
    int id;
    String name;
    double price;

    public Item(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

class Cart {
    Map<Item, Integer> cartItems = new HashMap<>();

    public void addItem(Item item, int quantity) {
        cartItems.put(item, cartItems.getOrDefault(item, 0) + quantity);
    }

    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<Item, Integer> entry : cartItems.entrySet()) {
            total += entry.getKey().price * entry.getValue();
        }
        return total;
    }

    public void printBill() {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("\n---- Final Bill ----");
        for (Map.Entry<Item, Integer> entry : cartItems.entrySet()) {
            Item item = entry.getKey();
            int qty = entry.getValue();
            System.out.println(item.name + " x " + qty + " = ₹" + (item.price * qty));
        }
        double total = calculateTotal();
        double gst = total * 0.18;
        System.out.println("Subtotal: ₹" + total);
        System.out.println("GST (18%): ₹" + gst);
        System.out.println("Total: ₹" + (total + gst));
    }
}

class Supermarket {
    ArrayList<Item> stock = new ArrayList<>();

    public void displayItems() {
        if (stock.isEmpty()) {
            System.out.println("No items in stock.");
            return;
        }
        System.out.println("\nAvailable Items:");
        for (Item item : stock) {
            System.out.println(item.id + ". " + item.name + " - ₹" + item.price);
        }
    }

    public Item getItemById(int id) {
        for (Item item : stock) {
            if (item.id == id) return item;
        }
        return null;
    }

    public void addItem(String name, double price) {
        int newId = stock.size() + 1;
        stock.add(new Item(newId, name, price));
        System.out.println("Item added successfully.");
    }

    public void updateItem(int id, String newName, double newPrice) {
        Item item = getItemById(id);
        if (item != null) {
            item.name = newName;
            item.price = newPrice;
            System.out.println("Item updated successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    public void deleteItem(int id) {
        Item item = getItemById(id);
        if (item != null) {
            stock.remove(item);
            System.out.println("Item deleted successfully.");
            for (int i = 0; i < stock.size(); i++) {
                stock.get(i).id = i + 1;
            }
        } else {
            System.out.println("Item not found.");
        }
    }
}

public class SupermarketBillingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Supermarket market = new Supermarket();
        Cart cart = new Cart();
        int choice;

        do {
            System.out.println("\n=== Supermarket Billing System ===");
            System.out.println("1. View Items");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Bill");
            System.out.println("4. Exit");
            System.out.println("5. Admin Panel");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    market.displayItems();
                    break;
                case 2:
                    if (market.stock.isEmpty()) {
                        System.out.println("No items available. Please ask admin to add items.");
                        break;
                    }
                    market.displayItems();
                    System.out.print("Enter Item ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    Item item = market.getItemById(id);
                    if (item != null) {
                        cart.addItem(item, qty);
                        System.out.println("Item added to cart!");
                    } else {
                        System.out.println("Invalid item ID!");
                    }
                    break;
                case 3:
                    cart.printBill();
                    break;
                case 4:
                    System.out.println("Thank you for visiting!");
                    break;
                case 5:
                    int adminChoice;
                    do {
                        System.out.println("\n--- Admin Panel ---");
                        System.out.println("1. Add Item");
                        System.out.println("2. View Items");
                        System.out.println("3. Update Item");
                        System.out.println("4. Delete Item");
                        System.out.println("5. Back to Main Menu");
                        System.out.print("Enter your choice: ");
                        adminChoice = sc.nextInt();

                        switch (adminChoice) {
                            case 1:
                                sc.nextLine(); // flush newline
                                System.out.print("Enter item name: ");
                                String name = sc.nextLine();
                                System.out.print("Enter price: ");
                                double price = sc.nextDouble();
                                market.addItem(name, price);
                                break;
                            case 2:
                                market.displayItems();
                                break;
                            case 3:
                                market.displayItems();
                                System.out.print("Enter item ID to update: ");
                                int updateId = sc.nextInt();
                                sc.nextLine(); // flush newline
                                System.out.print("Enter new name: ");
                                String newName = sc.nextLine();
                                System.out.print("Enter new price: ");
                                double newPrice = sc.nextDouble();
                                market.updateItem(updateId, newName, newPrice);
                                break;
                            case 4:
                                market.displayItems();
                                System.out.print("Enter item ID to delete: ");
                                int deleteId = sc.nextInt();
                                market.deleteItem(deleteId);
                                break;
                            case 5:
                                System.out.println("Returning to main menu...");
                                break;
                            default:
                                System.out.println("Invalid admin choice!");
                        }
                    } while (adminChoice != 5);
                    break;
                default:
                    System.out.println("Invalid main menu choice!");
            }
        } while (choice != 4);

        sc.close();
    }
}
