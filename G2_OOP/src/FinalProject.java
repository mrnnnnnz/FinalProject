import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.SystemColor;

public class FinalProject {

    private JFrame frame;
    private JTextField customerNameField;
    private JTextField dateField;
    private JTextField discountField;
    private JTextField cashField;
    private JTextField[] quantityTxtFields;
    
    private Product[] products = {
        new Chocolate("Toblerone", 55.00),
        new Chocolate("Snickers", 25.00),
        new Chocolate("Hershey", 45.00),
        new Chocolate("Baby Ruth", 68.00),
        new Chocolate("Kit Kat", 34.00)
    };

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FinalProject window = new FinalProject();
                    window.frame.setLocationRelativeTo(null);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FinalProject() {
        initialize();
        frame.setLocationRelativeTo(null);
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
        frame.setAutoRequestFocus(false);
        frame.getContentPane().setForeground(new Color(0, 0, 0));
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(47, 79, 79));
        frame.setBounds(100, 100, 1155, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("J Brother's Chocolate Factory");
        lblTitle.setForeground(new Color(255, 255, 255));
        lblTitle.setFont(new Font("Black Han Sans", Font.BOLD | Font.ITALIC, 40));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(10, 21, 764, 51);
        frame.getContentPane().add(lblTitle);

        JButton btnCheckOut = new JButton("Place Order");
        btnCheckOut.setForeground(new Color(255, 255, 255));
        btnCheckOut.setBackground(new Color(34, 139, 34));
        btnCheckOut.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        btnCheckOut.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        btnCheckOut.setBounds(786, 658, 345, 45);
        btnCheckOut.setFocusPainted(false);
        frame.getContentPane().add(btnCheckOut);

        JPanel panel1 = new JPanel();
        panel1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Customer's Information", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel1.setBackground(new Color(255, 255, 255));
        panel1.setBounds(786, 10, 345, 115);
        frame.getContentPane().add(panel1);
        panel1.setLayout(null);

        JLabel lblCustomerName = new JLabel("Customer Name:");
        lblCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCustomerName.setBounds(10, 20, 120, 14);
        panel1.add(lblCustomerName);

        customerNameField = new JTextField();
        customerNameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        customerNameField.setBounds(10, 40, 325, 20);
        panel1.add(customerNameField);
        customerNameField.setColumns(10);

        JLabel lblDate = new JLabel("Date:");
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblDate.setBounds(10, 65, 120, 14);
        panel1.add(lblDate);

        java.util.Date currentDate = new java.util.Date();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MMMM dd, yyyy");
        String currentDateFormatted = dateFormat.format(currentDate);

        dateField = new JTextField(currentDateFormatted);
        dateField.setHorizontalAlignment(SwingConstants.CENTER);
        dateField.setBounds(10, 85, 325, 20);
        dateField.setBackground(Color.WHITE);
        panel1.add(dateField);
        dateField.setFont(new Font("Tahoma", Font.BOLD, 12));
        dateField.setEditable(false);

        JPanel panel2 = new JPanel();
        panel2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Order Summary", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel2.setBackground(new Color(255, 255, 255));
        panel2.setBounds(786, 135, 345, 513);
        frame.getContentPane().add(panel2);
        panel2.setLayout(null);

        JLabel lblDiscount = new JLabel("Discount (%):");
        lblDiscount.setFont(new Font("Dialog", Font.BOLD, 12));
        lblDiscount.setBounds(10, 82, 80, 14);
        panel2.add(lblDiscount);

        discountField = new JTextField();
        discountField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        discountField.setBounds(10, 100, 150, 20);
        panel2.add(discountField);
        discountField.setColumns(10);

        JLabel lblCash = new JLabel("Cash:");
        lblCash.setFont(new Font("Dialog", Font.BOLD, 12));
        lblCash.setBounds(185, 82, 80, 14);
        panel2.add(lblCash);

        cashField = new JTextField();
        cashField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        cashField.setBounds(185, 100, 150, 20);
        panel2.add(cashField);
        cashField.setColumns(10);

        JTextArea outputArea = new JTextArea();
        outputArea.setForeground(new Color(0, 0, 0));
        outputArea.setFont(new Font("Courier New", Font.BOLD, 12));
        outputArea.setBounds(10, 130, 325, 373);
        outputArea.setEditable(false); 
        outputArea.setEnabled(false);
        panel2.add(outputArea);

        JPanel panel3 = new JPanel();
        panel3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel3.setBackground(Color.WHITE);
        panel3.setBounds(10, 82, 250, 300);
        frame.getContentPane().add(panel3);

        JLabel lblImage = new JLabel(new ImageIcon("toblerone.png"));
        panel3.add(lblImage);

        JPanel panel4 = new JPanel();
        panel4.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel4.setBackground(Color.WHITE);
        panel4.setBounds(266, 82, 250, 300);
        frame.getContentPane().add(panel4);

        JLabel lblImage2 = new JLabel(new ImageIcon("snickers.png"));
        panel4.add(lblImage2);

        JPanel panel5 = new JPanel();
        panel5.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel5.setBackground(Color.WHITE);
        panel5.setBounds(522, 82, 250, 300);
        frame.getContentPane().add(panel5);

        JLabel lblImage3 = new JLabel(new ImageIcon("hershey.png"));
        panel5.add(lblImage3);

        JPanel panel6 = new JPanel();
        panel6.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel6.setBackground(Color.WHITE);
        panel6.setBounds(10, 403, 250, 300);
        frame.getContentPane().add(panel6);

        JLabel lblImage4 = new JLabel(new ImageIcon("baby_ruth.png"));
        panel6.add(lblImage4);

        JPanel panel7 = new JPanel();
        panel7.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel7.setBackground(Color.WHITE);
        panel7.setBounds(270, 403, 250, 300);
        frame.getContentPane().add(panel7);

        JLabel lblImage5 = new JLabel(new ImageIcon("kitkat.png"));
        panel7.add(lblImage5);

        JPanel panel8 = new JPanel();
        panel8.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel8.setBackground(Color.WHITE);
        panel8.setBounds(526, 403, 250, 300);
        frame.getContentPane().add(panel8);

        JLabel lblImage6 = new JLabel(new ImageIcon("coming_soon.png"));
        panel8.add(lblImage6);

        JCheckBox chckbxToblerone = new JCheckBox("Toblerone -  ₱55.00");
        chckbxToblerone.setForeground(SystemColor.desktop);
        chckbxToblerone.setBackground(SystemColor.window);
        chckbxToblerone.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        panel3.add(chckbxToblerone);

        JCheckBox chckbxSnickers = new JCheckBox("Snickers - ₱25.00");
        chckbxSnickers.setForeground(SystemColor.desktop);
        chckbxSnickers.setBackground(SystemColor.window);
        chckbxSnickers.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        panel4.add(chckbxSnickers);

        JCheckBox chckbxHershey = new JCheckBox("Hershey - ₱45.00");
        chckbxHershey.setForeground(SystemColor.desktop);
        chckbxHershey.setBackground(SystemColor.window);
        chckbxHershey.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        panel5.add(chckbxHershey);

        JCheckBox chckbxBabyRuth = new JCheckBox("Baby Ruth - ₱68.00");
        chckbxBabyRuth.setForeground(SystemColor.desktop);
        chckbxBabyRuth.setBackground(SystemColor.window);
        chckbxBabyRuth.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        panel6.add(chckbxBabyRuth);

        JCheckBox chckbxKitKat = new JCheckBox("Kit-Kat - ₱34.00");
        chckbxKitKat.setForeground(SystemColor.desktop);
        chckbxKitKat.setBackground(SystemColor.window);
        chckbxKitKat.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        panel7.add(chckbxKitKat);

        JLabel lblComingSoon = new JLabel("Coming Soon!");
        lblComingSoon.setForeground(new Color(165, 42, 42));
        lblComingSoon.setFont(new Font("Black Han Sans", Font.BOLD, 28));
        panel8.add(lblComingSoon);

        JLabel lblSelectQuan = new JLabel("Quantity:");
        lblSelectQuan.setFont(new Font("Dialog", Font.BOLD, 12));
        lblSelectQuan.setBounds(10, 15, 80, 20);
        panel2.add(lblSelectQuan);
        
        JLabel lblQuantityToblerone = new JLabel("Toblerone");
        lblQuantityToblerone.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblQuantityToblerone.setBounds(10, 37, 60, 14); 
        panel2.add(lblQuantityToblerone);

        JLabel lblQuantitySnickers = new JLabel("Snickers");
        lblQuantitySnickers.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblQuantitySnickers.setBounds(82, 37, 50, 14); 
        panel2.add(lblQuantitySnickers);

        JLabel lblQuantityHershey = new JLabel("Hershey");
        lblQuantityHershey.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblQuantityHershey.setBounds(150, 37, 50, 14); 
        panel2.add(lblQuantityHershey);

        JLabel lblBabyRuthQuantity = new JLabel("Baby Ruth");
        lblBabyRuthQuantity.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblBabyRuthQuantity.setBounds(215, 37, 60, 14); 
        panel2.add(lblBabyRuthQuantity);

        JLabel lblQuantityKitkat = new JLabel("Kit-Kat");
        lblQuantityKitkat.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblQuantityKitkat.setBounds(286, 37, 50, 14); 
        panel2.add(lblQuantityKitkat);
        
        quantityTxtFields = new JTextField[products.length];
        
        JTextField txtToblerone = new JTextField();
        txtToblerone.setBackground(SystemColor.text);
        txtToblerone.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtToblerone.setBounds(10, 55, 44, 20);
        txtToblerone.setColumns(10);
        txtToblerone.setEnabled(false);
        panel2.add(txtToblerone);
        quantityTxtFields[0] = txtToblerone;

        JTextField txtSnickers = new JTextField();
        txtSnickers.setBackground(SystemColor.text);
        txtSnickers.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtSnickers.setBounds(82, 55, 44, 20);
        txtSnickers.setColumns(10);
        txtSnickers.setEnabled(false);
        panel2.add(txtSnickers);
        quantityTxtFields[1] = txtSnickers;

        JTextField txtHershey = new JTextField();
        txtHershey.setBackground(SystemColor.text);
        txtHershey.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtHershey.setBounds(150, 55, 44, 20);
        txtHershey.setColumns(10);
        txtHershey.setEnabled(false);
        panel2.add(txtHershey);
        quantityTxtFields[2] = txtHershey;

        JTextField txtBabyRuth = new JTextField();
        txtBabyRuth.setBackground(SystemColor.text);
        txtBabyRuth.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtBabyRuth.setBounds(215, 55, 44, 20);
        txtBabyRuth.setColumns(10);
        txtBabyRuth.setEnabled(false);
        panel2.add(txtBabyRuth);
        quantityTxtFields[3] = txtBabyRuth;

        JTextField txtKitKat = new JTextField();
        txtKitKat.setBackground(SystemColor.text);
        txtKitKat.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtKitKat.setBounds(286, 55, 44, 20);
        txtKitKat.setColumns(10);
        txtKitKat.setEnabled(false);
        panel2.add(txtKitKat);
        quantityTxtFields[4] = txtKitKat;
        
        chckbxToblerone.setFocusable(false);
        chckbxSnickers.setFocusable(false);
        chckbxHershey.setFocusable(false);
        chckbxBabyRuth.setFocusable(false);
        chckbxKitKat.setFocusable(false);

        chckbxToblerone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chckbxToblerone.isSelected()) {
                    quantityTxtFields[0].setEnabled(true); 
                } else {
                    quantityTxtFields[0].setEnabled(false);
                }
            }
        });

        chckbxSnickers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chckbxSnickers.isSelected()) {
                    quantityTxtFields[1].setEnabled(true); 
                } else {
                    quantityTxtFields[1].setEnabled(false);
                }
            }
        });

        chckbxHershey.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chckbxHershey.isSelected()) {
                    quantityTxtFields[2].setEnabled(true); 
                } else {
                    quantityTxtFields[2].setEnabled(false);
                }
            }
        });

        chckbxBabyRuth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chckbxBabyRuth.isSelected()) {
                    quantityTxtFields[3].setEnabled(true); 
                } else {
                    quantityTxtFields[3].setEnabled(false);
                }
            }
        });

        chckbxKitKat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chckbxKitKat.isSelected()) {
                    quantityTxtFields[4].setEnabled(true);
                } else {
                    quantityTxtFields[4].setEnabled(false);
                }
            }
        });

        btnCheckOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String customerName = customerNameField.getText();
                    String date = dateField.getText();
                    double discount = Double.parseDouble(discountField.getText());
                    double cash = 0; // Modified here
                    int quantity = 0;

                    if (customerName.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please enter a name.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (discountField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please enter a discount.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (cashField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please enter a cash amount.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        try {
                            cash = Double.parseDouble(cashField.getText()); // Moved inside try block
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Please enter a valid cash amount.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (cash < 0) {
                            JOptionPane.showMessageDialog(frame, "Cash amount cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                    if (discount < 5 || discount > 50) {
                        JOptionPane.showMessageDialog(frame, "Discount should be between 5% and 50%.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double totalAmount = 0; // Calculate total amount before applying discount
                    for (int i = 0; i < products.length; i++) {
                        if (quantityTxtFields[i].isEnabled()) {
                            try {
                                quantity = Integer.parseInt(quantityTxtFields[i].getText());
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(frame, "Please enter a valid input for quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            if (quantity <= 0) {
                                JOptionPane.showMessageDialog(frame, "Enter quantities greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            totalAmount += products[i].getPrice() * quantity;
                        }
                    }

                    totalAmount *= (1 - discount / 100); // Apply discount

                    if (totalAmount > cash) { // Check if total amount exceeds cash
                        JOptionPane.showMessageDialog(frame, "Insufficient cash provided.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    List<Product> productsList = new ArrayList<>();
                    List<Integer> quantitiesList = new ArrayList<>();

                    for (int i = 0; i < products.length; i++) {
                        if (quantityTxtFields[i].isEnabled()) {
                            quantity = Integer.parseInt(quantityTxtFields[i].getText());
                            productsList.add(products[i]);
                            quantitiesList.add(quantity);
                        }
                    }

                    Transaction transaction = new Transaction(customerName, date, discount, cash, productsList, quantitiesList);
                    String receipt = transaction.generateReceipt();

                    outputArea.setText(receipt);

                    try (FileWriter writer = new FileWriter("Customer.txt")) {
                        writer.write(receipt);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error writing to file!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid input.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}