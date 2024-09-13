package com.stella.alephart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stella.alephart.models.User;
import com.stella.alephart.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getId_user(@PathVariable("id") Long id) {
		return userService.findUserById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	//@PutMapping("/id")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        return userService.findUserById(id)
            .map(user -> {
                user.setPassword(updatedUser.getPassword());
                userService.saveUser(user); 
                return ResponseEntity.ok(user);
            })
            .orElse(ResponseEntity.notFound().build());
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
		return userService.findUserById(id)
				.map(user -> {
					userService.deleteUser(id);
					return ResponseEntity.ok().<Void>build();
				})
				.orElse(ResponseEntity.notFound().build());
	}

}
