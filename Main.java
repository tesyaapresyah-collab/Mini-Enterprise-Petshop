import view.LoginView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Praktik terbaik Java Swing: Menjalankan GUI di thread yang aman
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginView loginForm = new LoginView();
                loginForm.setVisible(true);
            }
        });
    }
}