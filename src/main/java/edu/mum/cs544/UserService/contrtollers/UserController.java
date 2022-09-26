package edu.mum.cs544.UserService.contrtollers;

import edu.mum.cs544.UserService.dtos.ResponseDto;
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
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDto<String>> signUp(@RequestBody User user) {
        userService.save(user);
        ResponseDto<String> responseDto = new ResponseDto<>("Signup Successful", false, "Signup Successful", null);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<UserDto>> update(@RequestBody UpdateUserDto user, @PathVariable int id) {
        UserDto updatedUser = userService.update(user, id);
        ResponseDto<UserDto> responseDto = new ResponseDto<>("Update Successful", false, updatedUser, null);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseDto<List<UserDto>> getAll() {
        List<UserDto> users = userService.getAll();
        return new ResponseDto<>("User List", false, users, null);
    }

    @GetMapping("/filterUsersByUsername")
    public ResponseDto<List<UserDto>> filterUsersByUserName(@RequestParam String username, @RequestParam String firstname) {
        List<UserDto> users = userService.filterUsersByUserName(username, firstname);
        return new ResponseDto<>("User List", false, users, null);
    }

    @GetMapping("/getUserByUsername")
    public ResponseDto<User> getUserByUserName(@RequestParam String username) {
        User user = userService.getUserByUserName(username);
        return new ResponseDto<>("User", false, user, null);
    }

    @GetMapping("/{id}")
    public ResponseDto<UserDto> getUserById(@PathVariable int id) {
        UserDto user = userService.get(id);
        return new ResponseDto<>("User", false, user, null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<UserDto>> delete(@PathVariable int id) {
        UserDto user = userService.delete(id);
        if (user != null) {
            ResponseDto<UserDto> userDtoResponseDto = new ResponseDto<>("User Soft Deleted", false, user, null);
            return new ResponseEntity<>(userDtoResponseDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.RESET_CONTENT);
    }
}
