package com.userservice.userservice.repo;


import com.userservice.userservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Long> {

}
