package service.impl;

import java.security.SecureRandom;
import java.util.List;

import DAO.UsersDAO;
import entity.Users;
import service.interf.UserService;

public class UserServiceImpl implements UserService {

    private UsersDAO dao = new UsersDAO();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    // Hàm tạo ID ngẫu nhiên
    private String generateId(int length) {
        StringBuilder sb = new StringBuilder(length);
        for(int i = 0; i < length; i++) {
            int idx = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(idx));
        }
        return sb.toString();
    }

    @Override
    public boolean insert(Users user) {
        try {
            Users existingUser = dao.selectByEmail(user.getEmail());
            if (existingUser != null) {
                // Email đã tồn tại
                return false;
            }
            user.setId(generateId(20)); // tạo id ngẫu nhiên 20 ký tự
            dao.insert(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Users login(String email, String password) {
        Users user = dao.selectByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public List<Users> findAll() {
        return dao.selectAll();
    }

    @Override
    public Users findById(String id) {
        return dao.selectByID(id);
    }

    @Override
    public boolean update(Users user) {
        try {
            dao.update(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            dao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePassword(String userId, String newPassword) {
        try {
            Users user = dao.selectByID(userId);
            if (user == null) {
                return false;
            }
            user.setPassword(newPassword);
            dao.update(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Users> getAllUsers() {
        return dao.selectAll();
    }
}
