package edu.mum.cs544.UserService.contrtollers;

import edu.mum.cs544.UserService.dtos.UpdateUserDto;
import edu.mum.cs544.UserService.dtos.UserDto;
import edu.mum.cs544.UserService.models.User;
import edu.mum.cs544.UserService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity signUp(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>("Signup Successful", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UpdateUserDto user, @PathVariable int id) {
        UserDto updatedUser = userService.update(user, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping()
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getAll(@PathVariable int id) {
        return userService.get(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable int id) {
        UserDto user = userService.delete(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.RESET_CONTENT);
    }
}
