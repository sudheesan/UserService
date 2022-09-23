package edu.mum.cs544.UserService.repositories;

import edu.mum.cs544.UserService.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    public User getUserByUsername(String username);
}
