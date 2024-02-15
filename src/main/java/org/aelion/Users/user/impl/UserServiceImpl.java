package org.aelion.Users.user.impl;

import org.aelion.Users.user.User;
import org.aelion.Users.user.UserRepository;
import org.aelion.Users.user.UserService;
import org.aelion.Users.user.dto.Community;
import org.aelion.Users.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final static String COMMUNITY_API ="http://COMMUNITY-SERVICE/api/v1/";

    @Autowired
    private UserRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<?> fetchById(String id) {
        Optional<User> oUser = repository.findById(id);
        if (oUser.isPresent()){
            String endpoint = COMMUNITY_API+ "communities/" + oUser.get().getCommunity_id();
            Community community  = restTemplate.getForObject(
                    endpoint,
                    Community.class
            );

            // Build a UserResponse
            UserResponse response = new UserResponse();
            response.setId(oUser.get().getId());
            response.setName(oUser.get().getPseudo());
            response.setCommunity(community);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>("No community was found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> createUser(User user) {
        try {
            return new ResponseEntity<User>(
                    repository.save(user),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>("Unable to save User", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
