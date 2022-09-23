package edu.mum.cs544.UserService.dtos;

import lombok.Data;

@Data
public class UpdateUserDto {
    private String username;
    private String firstname;
    private String lastname;
}
