import java.util.List;

public class Transaction {
    private String customerName;
    private String date;
    private double discount;
    private double cash;
    private List<Product> items;
    private List<Integer> quantities;

    public Transaction(String customerName, String date, double discount, double cash, List<Product> items, List<Integer> quantities) {
        this.customerName = customerName;
        this.date = date;

        if (discount < 5.0) {
            this.discount = 5.0;
        } else if (discount > 50.0) {
            this.discount = 50.0;
        } else {
            this.discount = discount;
        }
        this.cash = cash;
        this.items = items;
        this.quantities = quantities;
    }

    public double calculateTotal() {
        double subtotal = 0.0;
        for (int i = 0; i < items.size(); i++) {
            subtotal += items.get(i).getPrice() * quantities.get(i);
        }
        double discountAmount = subtotal * (discount / 100);
        return subtotal - discountAmount;
    }

    public String generateReceipt() {
        double total = calculateTotal();
        double changeDue = cash - total;
        
        StringBuilder receipt = new StringBuilder();
        receipt.append("=================================================\n");
        receipt.append("        J Brother's Chocolate Factory            \n");
        receipt.append("=================================================\n");
        receipt.append("Customer: ").append(customerName).append("\n");
        receipt.append("Date: ").append(date).append("\n");
        receipt.append("-------------------------------------------------\n");
        receipt.append(String.format("%-20s %5s %10s\n", "Item", "Qty", "Price"));
        receipt.append("-------------------------------------------------\n");

        double subtotal = 0.0;
        for (int i = 0; i < items.size(); i++) {
            Product item = items.get(i);
            int quantity = quantities.get(i);
            double productTotal = item.getPrice() * quantity;
            subtotal += productTotal;
            receipt.append(String.format("%-20s %5d %10.2f\n", item.getName(), quantity, productTotal));
        }

        double discountAmount = subtotal * (discount / 100);
        double amountAfterDiscount = subtotal - discountAmount;

        receipt.append("-------------------------------------------------\n");
        receipt.append(String.format("%-25s %10.2f\n", "Subtotal:", subtotal));
        receipt.append(String.format("%-25s %10.2f\n", "Discount (" + discount + "%):", -discountAmount));
        receipt.append(String.format("%-25s %10.2f\n", "Total:", amountAfterDiscount));
        receipt.append(String.format("%-25s %10.2f\n", "Cash:", cash));
        receipt.append(String.format("%-25s %10.2f\n", "Change Due:", changeDue));
        receipt.append("=================================================\n");
        receipt.append("          Thank you for your order!              \n");
        receipt.append("=================================================\n");
        return receipt.toString();
    }
}
