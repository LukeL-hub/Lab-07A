import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private Customer customer;
    private List<Item> items;

    public Invoice(Customer customer) {
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(Item::getTotal).sum();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice for: ").append(customer.getName()).append("\n");
        sb.append("Address: ").append(customer.getAddress()).append("\n");
        sb.append("---------------------------------\n");
        for (Item item : items) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("---------------------------------\n");
        sb.append("Total Amount Due: $").append(calculateTotal()).append("\n");
        return sb.toString();
    }
}
