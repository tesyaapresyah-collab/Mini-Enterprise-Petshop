package service;

import entity.User;
import repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User login(String username, String password) {
        // Validasi Input: Pastikan username dan password tidak kosong
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return null; 
        }
        
        // Teruskan ke repository jika validasi lolos
        return userRepository.findByUsernameAndPassword(username, password);
    }
}