import javax.swing.*;
import java.awt.*;

class StudentGUI extends JFrame {

    String userId;
    DefaultListModel<String> model;
    JList<String> list;

    StudentGUI(String id) {

        userId = id;

        setTitle("Student Dashboard");
        setSize(650, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("STUDENT DASHBOARD");
        title.setBounds(220, 10, 300, 30);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(title);

        model = new DefaultListModel<>();
        list = new JList<>(model);

        JScrollPane sp = new JScrollPane(list);
        sp.setBounds(100, 60, 450, 220);
        add(sp);

        load();

        JButton req = btn("Request", 100, 310, new Color(52, 152, 219));
        JButton ret = btn("Return", 230, 310, new Color(46, 204, 113));
        JButton refresh = btn("Refresh", 360, 310, new Color(155, 89, 182));
        JButton logout = btn("Logout", 250, 380, new Color(231, 76, 60));

        add(req); add(ret); add(refresh); add(logout);

        req.addActionListener(e -> request());
        ret.addActionListener(e -> returnItem());
        refresh.addActionListener(e -> load());
        logout.addActionListener(e -> { new LoginGUI(); dispose(); });

        setVisible(true);
    }

    JButton btn(String t, int x, int y, Color c) {
        JButton b = new JButton(t);
        b.setBounds(x, y, 120, 35);
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        return b;
    }

    void load() {
        model.clear();
        for (Equipment e : DataStore.equipmentList)
            model.addElement(e.name + (e.available ? " ✔" : " ❌"));
    }

    void request() {
        int i = list.getSelectedIndex();
        if (i == -1) return;

        Equipment e = DataStore.equipmentList.get(i);
        if (!e.available) return;

        DataStore.requests.add(new Request(userId, e.name));
        JOptionPane.showMessageDialog(this, "Request Sent");
    }

    void returnItem() {
        int i = list.getSelectedIndex();
        if (i == -1) return;

        Equipment e = DataStore.equipmentList.get(i);
        e.available = true;

        DataStore.history.add(new BorrowRecord(userId, e.name, "Returned"));
        load();
    }
}