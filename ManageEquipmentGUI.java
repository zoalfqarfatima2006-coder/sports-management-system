import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class ManageEquipmentGUI extends JFrame {

    DefaultTableModel model;
    JTable table;
    JTextField nameField;

    ManageEquipmentGUI() {

        setTitle("Manage Equipment");
        setSize(650, 450);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // ---------------- TITLE ----------------
        JLabel title = new JLabel("MANAGE EQUIPMENT");
        title.setBounds(220, 10, 300, 30);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(title);

        // ---------------- TABLE ----------------
        model = new DefaultTableModel(new String[]{"Equipment Name", "Available"}, 0);
        table = new JTable(model);
        table.setRowHeight(25);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(80, 50, 480, 200);
        add(sp);

        // ---------------- INPUT FIELD ----------------
        nameField = new JTextField();
        nameField.setBounds(80, 270, 200, 30);
        add(nameField);

        // ---------------- BUTTONS ----------------
        JButton addBtn = new JButton("Add");
        JButton deleteBtn = new JButton("Delete");
        JButton updateBtn = new JButton("Update");
        JButton refreshBtn = new JButton("Refresh");

        addBtn.setBounds(300, 270, 90, 30);
        deleteBtn.setBounds(400, 270, 90, 30);
        updateBtn.setBounds(80, 320, 120, 35);
        refreshBtn.setBounds(220, 320, 120, 35);

        add(addBtn);
        add(deleteBtn);
        add(updateBtn);
        add(refreshBtn);

        // ---------------- LOAD DATA ----------------
        load();

        // ---------------- ACTIONS ----------------

        // ADD
        addBtn.addActionListener(e -> {

            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter equipment name!");
                return;
            }

            DataStore.equipmentList.add(new Equipment(name));
            nameField.setText("");
            load();
        });

        // DELETE
        deleteBtn.addActionListener(e -> {

            int i = table.getSelectedRow();

            if (i == -1) {
                JOptionPane.showMessageDialog(this, "Select equipment to delete!");
                return;
            }

            DataStore.equipmentList.remove(i);
            load();
        });

        // UPDATE
        updateBtn.addActionListener(e -> {

            int i = table.getSelectedRow();

            if (i == -1) {
                JOptionPane.showMessageDialog(this, "Select equipment to update!");
                return;
            }

            String newName = nameField.getText().trim();

            if (newName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter new name!");
                return;
            }

            DataStore.equipmentList.get(i).name = newName;
            nameField.setText("");
            load();
        });

        // REFRESH
        refreshBtn.addActionListener(e -> load());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // ---------------- LOAD TABLE ----------------
    void load() {

        model.setRowCount(0);

        for (Equipment e : DataStore.equipmentList) {
            model.addRow(new Object[]{e.name, e.available});
        }
    }
}