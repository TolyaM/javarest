package rnd.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import rnd.authentication.domain.User;
import rnd.authentication.repository.UserRepository;
import rnd.authentication.service.UserService;

import java.security.Principal;
import java.util.Collection;

@CrossOrigin
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

    @RequestMapping(value = "/user", params = {"username", "password"}, method = RequestMethod.GET)
    @ResponseBody
    public User user(@RequestParam("username") String username, @RequestParam("password") String password){
        return userService.loadUserByUsernamePassword(username, password);
    }

    @RequestMapping(value = "/loggeduser", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<String> findMessagesForUser(Principal principal) {
        return new ResponseEntity<String>(principal.getName(), HttpStatus.OK);
    }


    @RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable long id, Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName());

        if (currentUser.getId() == id) {
            userRepository.delete((int) id);
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
        User person = userRepository.findOne((int) id);

        if (person != null) {
            return new ResponseEntity<>(userRepository.findOne((int) id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>((MultiValueMap<String, String>) null, HttpStatus.NOT_FOUND);
        }
    }
}