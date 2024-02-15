package org.aelion.Users.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")

public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<?> fetchAll(){
        return service.fetchUsers();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchById(@PathVariable String id) {
        return service.fetchById(id);
    }
}
