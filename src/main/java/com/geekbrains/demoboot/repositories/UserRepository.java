package com.geekbrains.demoboot.repositories;

import com.geekbrains.demoboot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
     User findByName(String name);
}

