import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class HistoryGUI extends JFrame {

    DefaultTableModel model;
    JTable table;

    HistoryGUI() {

        setTitle("Borrowing History");
        setSize(500, 400);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel title = new JLabel("BORROWING HISTORY");
        title.setBounds(150, 10, 250, 30);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title);

        model = new DefaultTableModel(
            new String[]{"User ID", "Equipment", "Status"}, 0
        );

        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50, 50, 380, 250);
        add(scroll);

        loadHistory();

        setVisible(true);
    }

    void loadHistory() {

        model.setRowCount(0);

        for (BorrowRecord b : DataStore.history) {
            model.addRow(new Object[]{
                b.userId,
                b.equipmentName,
                b.status
            });
        }
    }
}