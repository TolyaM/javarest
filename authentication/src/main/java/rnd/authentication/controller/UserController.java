package rnd.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import rnd.authentication.domain.User;
import rnd.authentication.repository.UserRepository;
import rnd.authentication.service.UserService;

import java.security.Principal;
import java.util.Collection;

@RestController
@CrossOrigin
public class UserController {


    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping(value = "/user")
    @ResponseBody
    public Principal user(Principal user){
        return user;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @CrossOrigin
    @RequestMapping(value = "/user",params = {"login", "password"},method = RequestMethod.GET)
    @ResponseBody
    public User user(@RequestParam("login") String login, @RequestParam("password") String password){
        return userService.loadUserByUsernameAndPassword(login,password);
    }


 /*   @RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable long id, Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName());

        if (currentUser.getId() == id) {
            userRepository.delete((int) id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
    }*/

   /* @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<User>> getUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }*/

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