package edu.mum.cs544.UserService.repositories;

import edu.mum.cs544.UserService.models.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.function.Predicate;

public interface UserDaoSpecification {
    Predicate toPredicate(Root<User> root, CriteriaQuery query, CriteriaBuilder cb);
}
