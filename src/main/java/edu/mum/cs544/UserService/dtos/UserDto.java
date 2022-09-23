package edu.mum.cs544.UserService.dtos;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String username;
    private String firstname;
    private String lastname;
    private boolean isActive;
}
