package com.masai.SpringBootApp.repository;


import com.masai.SpringBootApp.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);


    @Query("SELECT COUNT(u) FROM User u")
    Long getLiveUserInDataBase();


    @Modifying
    @Query("delete from User u where u.userId=:id")
    void deleteUser(@Param("id") Integer userId);
}
