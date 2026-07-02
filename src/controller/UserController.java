package controller;

import entity.User;
import service.UserService;

public class UserController {
    private UserService userService;
    
    // Variabel statis untuk menyimpan data user yang sedang aktif/login (Sesi)
    public static User currentUser; 

    public UserController() {
        this.userService = new UserService();
    }

    public boolean handleLogin(String username, String password) {
        User user = userService.login(username, password);
        
        if (user != null) {
            currentUser = user; // Menyimpan sesi agar peran (Admin/Kasir) bisa dicek nanti
            return true;
        }
        return false;
    }
    
    public void logout() {
        currentUser = null;
    }
}