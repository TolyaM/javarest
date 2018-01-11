package rnd.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import rnd.auth.model.User;
import rnd.auth.repository.UserRepository;
import rnd.auth.service.UserService;

import java.security.Principal;
import java.util.Collection;

@RestController
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private UserService userService;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/user")
    public Principal user(Principal user){
        return user;
    }

   @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable long id, Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName());

        if (currentUser.getId() == id) {
            userRepository.delete(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<User>> getUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable long id) {
        User person = userRepository.findOne(id);

        if (person != null) {
            return new ResponseEntity<>(userRepository.findOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>((MultiValueMap<String, String>) null, HttpStatus.NOT_FOUND);
        }
    }

 /*   @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User person) {
        return new ResponseEntity<>(userRepository.save(person), HttpStatus.CREATED);
    }*/
}
