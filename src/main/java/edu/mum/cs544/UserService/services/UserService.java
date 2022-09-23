package edu.mum.cs544.UserService.services;

import edu.mum.cs544.UserService.dtos.UpdateUserDto;
import edu.mum.cs544.UserService.dtos.UserDto;
import edu.mum.cs544.UserService.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    public List<UserDto> getAll();
    public void save(User user);
    public UserDto get(int id);
    public UserDto update(UpdateUserDto user, int id);
    public UserDto delete(int id);
    public User getUserByUserName(String name);
}
