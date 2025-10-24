package service.interf;

import java.util.List;

import entity.Users;

public interface UserService {

    boolean insert(Users user);

    Users login(String email, String password);

   // boolean existsByEmail(String email);

    List<Users> findAll();

    Users findById(String id);

    boolean update(Users user);

    boolean delete(String id);
    
    boolean updatePassword(String userId, String newPassword);
    
    List<Users> getAllUsers();
}