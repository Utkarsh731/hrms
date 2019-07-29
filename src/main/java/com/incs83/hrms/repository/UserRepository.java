package com.incs83.hrms.repository;


import com.incs83.hrms.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {
    User findByEmailAndPassword(String email,String password);
}
