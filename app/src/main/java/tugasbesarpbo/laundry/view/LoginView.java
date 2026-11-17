
package tugasbesarpbo.laundry.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import tugasbesarpbo.laundry.model.Role;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<Role> roleComboBox;
    private JLabel statusLabel;
    private JButton loginButton;

    public LoginView() {
        setTitle("Login - Sistem Manajemen Laundry");
        setSize(400, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 10, 20));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        roleComboBox = new JComboBox<>(Role.values());
        loginButton = new JButton("Login");
        statusLabel = new JLabel("Silakan login.", SwingConstants.CENTER);

        formPanel.add(new JLabel("Username:"));
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);

        formPanel.add(new JLabel("Role:"));
        formPanel.add(roleComboBox);

        formPanel.add(new JLabel(""));
        formPanel.add(loginButton);

        add(formPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public Role getSelectedRole() {
        return (Role) roleComboBox.getSelectedItem();
    }

    public void setStatus(String message) {
        statusLabel.setText(message);
    }

    public void setLoginAction(Runnable action) {
        loginButton.addActionListener(e -> action.run());
    }

    public void showWindow() {
        setVisible(true);
    }
}
