package org.aelion.Users.user;

import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> fetchUsers();
    ResponseEntity<?> createUser(User user);
    ResponseEntity<?> fetchById(String id);
}
