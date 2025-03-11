import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceAppGUI extends JFrame {
    private JTextField customerNameField, customerAddressField;
    private JTextField productNameField, unitPriceField, quantityField;
    private JTextArea invoiceDisplay;
    private Invoice invoice;

    public InvoiceAppGUI() {
        setTitle("Invoice Generator");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());  // Use GridBagLayout for better alignment

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Adds spacing between components
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        addComponent(new JLabel("Customer Name:"), gbc);
        customerNameField = new JTextField(20);
        addComponent(customerNameField, gbc);

        addComponent(new JLabel("Customer Address:"), gbc);
        customerAddressField = new JTextField(20);
        addComponent(customerAddressField, gbc);

        addComponent(new JLabel("Product Name:"), gbc);
        productNameField = new JTextField(20);
        addComponent(productNameField, gbc);

        addComponent(new JLabel("Unit Price:"), gbc);
        unitPriceField = new JTextField(20);
        addComponent(unitPriceField, gbc);

        addComponent(new JLabel("Quantity:"), gbc);
        quantityField = new JTextField(20);
        addComponent(quantityField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addItemButton = new JButton("Add Item");
        JButton generateInvoiceButton = new JButton("Generate Invoice");
        buttonPanel.add(addItemButton);
        buttonPanel.add(generateInvoiceButton);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy++;
        add(buttonPanel, gbc);

        invoiceDisplay = new JTextArea(8, 30);
        invoiceDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(invoiceDisplay);

        gbc.gridy++;
        add(scrollPane, gbc);

        addItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (invoice == null) {
                    invoice = new Invoice(new Customer(customerNameField.getText(), customerAddressField.getText()));
                }
                String productName = productNameField.getText();
                double unitPrice = Double.parseDouble(unitPriceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                invoice.addItem(new Item(new Product(productName, unitPrice), quantity));

                invoiceDisplay.setText("Item Added: " + productName);
            }
        });

        generateInvoiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (invoice != null) {
                    invoiceDisplay.setText(invoice.toString());
                }
            }
        });

        setVisible(true);
    }

    private void addComponent(Component comp, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        add(comp, gbc);
        gbc.gridx = 1;
        add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(comp), gbc);
    }

    public static void main(String[] args) {
        new InvoiceAppGUI();
    }
}
