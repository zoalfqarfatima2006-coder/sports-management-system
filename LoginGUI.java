import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginGUI extends JFrame {

    JTextField idField;
    JPasswordField passField;

    LoginGUI() {

        setTitle("Sports Management System");
        setSize(520, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel bg = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(41, 128, 185),
                        0, getHeight(), new Color(109, 213, 237)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        bg.setBounds(0, 0, 520, 420);
        bg.setLayout(null);
        add(bg);

        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(110, 80, 300, 260);
        card.setBackground(Color.WHITE);
        bg.add(card);

        JLabel title = new JLabel("LOGIN");
        title.setBounds(110, 20, 100, 30);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        card.add(title);

        JLabel idLabel = new JLabel("User ID");
        idLabel.setBounds(30, 60, 100, 20);
        card.add(idLabel);

        idField = new JTextField();
        idField.setBounds(30, 80, 240, 30);
        card.add(idField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(30, 120, 100, 20);
        card.add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(30, 140, 240, 30);
        card.add(passField);

        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setBounds(30, 190, 240, 35);
        loginBtn.setBackground(new Color(52, 152, 219));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginBtn.setFocusPainted(false);
        card.add(loginBtn);

        loginBtn.addActionListener(e -> login());

        setVisible(true);
    }

    void login() {

        String id = idField.getText();
        String pass = new String(passField.getPassword());

        if (id.equals("admin") && pass.equals("123")) {
            new AdminGUI();
            dispose();
        }
        else if (id.equals("student") && pass.equals("123")) {
            new StudentGUI(id);
            dispose();
        }
        else if (id.equals("teacher") && pass.equals("123")) {
            new StudentGUI(id);
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials!");
        }
    }
}