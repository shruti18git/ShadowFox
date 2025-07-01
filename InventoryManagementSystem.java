import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class InventoryItem {
    String name;
    int quantity;
    double price;

    public InventoryItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

public class InventoryManagementSystem extends JFrame {
    private JTextField txtName, txtQuantity, txtPrice;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<InventoryItem> items;

    public InventoryManagementSystem() {
        setTitle("Inventory Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);

        items = new ArrayList<>();

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        inputPanel.add(new JLabel("Item Name:"));
        txtName = new JTextField();
        inputPanel.add(txtName);

        inputPanel.add(new JLabel("Quantity:"));
        txtQuantity = new JTextField();
        inputPanel.add(txtQuantity);

        inputPanel.add(new JLabel("Price:"));
        txtPrice = new JTextField();
        inputPanel.add(txtPrice);

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        inputPanel.add(btnAdd);
        inputPanel.add(btnUpdate);

        model = new DefaultTableModel(new String[]{"Name", "Quantity", "Price"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel bottomPanel = new JPanel();
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        bottomPanel.add(btnDelete);
        bottomPanel.add(btnClear);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> addItem());
        btnUpdate.addActionListener(e -> updateItem());
        btnDelete.addActionListener(e -> deleteItem());
        btnClear.addActionListener(e -> clearFields());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                txtName.setText(model.getValueAt(row, 0).toString());
                txtQuantity.setText(model.getValueAt(row, 1).toString());
                txtPrice.setText(model.getValueAt(row, 2).toString());
            }
        });

        setVisible(true);
    }

    private void addItem() {
        try {
            String name = txtName.getText().trim();
            int quantity = Integer.parseInt(txtQuantity.getText().trim());
            double price = Double.parseDouble(txtPrice.getText().trim());

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Item name cannot be empty.");
                return;
            }

            items.add(new InventoryItem(name, quantity, price));
            model.addRow(new Object[]{name, quantity, price});
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantity and Price must be numeric.");
        }
    }

    private void updateItem() {
        int selected = table.getSelectedRow();
        if (selected >= 0) {
            try {
                String name = txtName.getText().trim();
                int quantity = Integer.parseInt(txtQuantity.getText().trim());
                double price = Double.parseDouble(txtPrice.getText().trim());

                items.set(selected, new InventoryItem(name, quantity, price));
                model.setValueAt(name, selected, 0);
                model.setValueAt(quantity, selected, 1);
                model.setValueAt(price, selected, 2);
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantity and Price must be numeric.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select an item to update.");
        }
    }

    private void deleteItem() {
        int selected = table.getSelectedRow();
        if (selected >= 0) {
            items.remove(selected);
            model.removeRow(selected);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Select an item to delete.");
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtQuantity.setText("");
        txtPrice.setText("");
        table.clearSelection();
    }

    public static void main(String[] args) {
        new InventoryManagementSystem();
    }
}
