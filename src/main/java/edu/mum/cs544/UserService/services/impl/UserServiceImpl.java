package edu.mum.cs544.UserService.services.impl;

import edu.mum.cs544.UserService.dtos.UpdateUserDto;
import edu.mum.cs544.UserService.dtos.UserDto;
import edu.mum.cs544.UserService.exceptions.UsernameAlreadyExistsException;
import edu.mum.cs544.UserService.models.User;
import edu.mum.cs544.UserService.repositories.UserDao;
import edu.mum.cs544.UserService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final ModelMapper modelMapper;

    @Override
    public List<UserDto> getAll() {
        return userDao.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void save(User user) {
        User existingUser = userDao.getUserByUsername(user.getUsername());
        if (existingUser == null) {
            user.setActive(true);
            userDao.save(user);
        } else {
            throw new UsernameAlreadyExistsException("User Already Exists");
        }
    }

    @Override
    public UserDto get(int id) {
        var user = userDao.findById(id);
        if(user.isPresent()){
            return modelMapper.map(user, UserDto.class);
        }else{
            return null;
        }

    }

    @Override
    public UserDto update(UpdateUserDto user, int id) {
        User existingUser = userDao.findById(id).get();
        String updatedFirstName = user.getFirstname();
        String updatedLastName = user.getLastname();
        String updatedUserName = user.getUsername();


        if(user == null){
            return null;
        }
        if (updatedFirstName != null) {
            existingUser.setFirstname(updatedFirstName);
        }
        if (updatedLastName != null) {
            existingUser.setLastname(updatedLastName);
        }
        if (updatedUserName != null) {
            User anotherUser = userDao.getUserByUsername(updatedUserName);
            if (anotherUser == null || anotherUser.equals(existingUser)) {
                existingUser.setUsername(updatedUserName);
            } else {
                throw new UsernameAlreadyExistsException("Username Already Exists");
            }

        }
        userDao.save(existingUser);
        return modelMapper.map(existingUser, UserDto.class);
    }

    static Specification usernameLike(String username) {
        return (user, cq, cb) -> cb.like(user.get("username"), "%" + username + "%");
    }
    public List<UserDto> filterUsersByUserName(String username){
        List<User> filteredUsers = userDao.findAll(usernameLike(username));
        return filteredUsers.stream().map(u -> modelMapper.map(u, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto delete(int id) {
        User user = userDao.findById(id).get();
        if (user != null) {
            user.setActive(false);
            userDao.save(user);
            return modelMapper.map(user, UserDto.class);
        }
        return null;
    }

    @Override
    public User getUserByUserName(String name) {
        return  userDao.getUserByUsername(name);
    }
}
