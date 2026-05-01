package main;

import controller.LoginController;
import view.LoginView;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            // Giao diện giống hệ điều hành
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        new LoginController(new LoginView());
    }
}