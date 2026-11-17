
package tugasbesarpbo.laundry;

import javax.swing.SwingUtilities;

import tugasbesarpbo.laundry.controller.LoginController;

public class App {

    public String getGreeting() {
        return "Laundry Management System";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginController controller = new LoginController();
            controller.start();
        });
    }
}
