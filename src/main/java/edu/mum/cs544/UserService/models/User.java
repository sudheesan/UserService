package edu.mum.cs544.UserService.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bloguser")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String firstname;
    private String lastname;
    private boolean isActive = true;
}
