import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class AdminGUI extends JFrame {

    DefaultTableModel model;
    JTable table;

    AdminGUI() {

        setTitle("Admin Dashboard");
        setSize(800, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("ADMIN DASHBOARD");
        title.setBounds(300, 10, 300, 30);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(title);

        model = new DefaultTableModel(new String[]{"User", "Equipment", "Status"}, 0);
        table = new JTable(model);
        table.setRowHeight(25);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(100, 60, 600, 250);
        add(sp);

        loadRequests();

        JButton approve = btn("Approve", 100, 330, new Color(46, 204, 113));
        JButton reject = btn("Reject", 220, 330, new Color(231, 76, 60));
        JButton refresh = btn("Refresh", 340, 330, new Color(52, 152, 219));
        JButton history = btn("History", 460, 330, new Color(155, 89, 182));
        JButton manage = btn("Manage", 580, 330, new Color(52, 73, 94));
        JButton logout = btn("Logout", 700, 330, new Color(127, 140, 141));

        add(approve);
        add(reject);
        add(refresh);
        add(history);
        add(manage);
        add(logout);

        approve.addActionListener(e -> approve());
        reject.addActionListener(e -> reject());
        refresh.addActionListener(e -> loadRequests());
        history.addActionListener(e -> new HistoryGUI());
        manage.addActionListener(e -> new ManageEquipmentGUI());
        logout.addActionListener(e -> { new LoginGUI(); dispose(); });

        setVisible(true);
    }

    JButton btn(String text, int x, int y, Color c) {
        JButton b = new JButton(text);
        b.setBounds(x, y, 110, 35);
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        b.setFocusPainted(false);
        return b;
    }

    void loadRequests() {
        model.setRowCount(0);
        for (Request r : DataStore.requests) {
            model.addRow(new Object[]{r.userId, r.equipmentName, r.status});
        }
    }

    void approve() {
        int i = table.getSelectedRow();
        if (i == -1) return;

        Request r = DataStore.requests.get(i);
        r.status = "Approved";

        for (Equipment e : DataStore.equipmentList)
            if (e.name.equals(r.equipmentName))
                e.available = false;

        DataStore.history.add(new BorrowRecord(r.userId, r.equipmentName, "Approved"));
        loadRequests();
    }

    void reject() {
        int i = table.getSelectedRow();
        if (i == -1) return;

        Request r = DataStore.requests.get(i);
        r.status = "Rejected";

        DataStore.history.add(new BorrowRecord(r.userId, r.equipmentName, "Rejected"));
        loadRequests();
    }
}