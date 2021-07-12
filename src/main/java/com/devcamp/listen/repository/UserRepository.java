package com.devcamp.listen.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.devcamp.listen.model.Customer;
import com.devcamp.listen.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM users WHERE username = :username AND password = :password", nativeQuery = true)
	User findUserbyUserName(@Param("username") String username, @Param("password") String password);
	
	@Query(value = "SELECT * FROM users WHERE phone_number = :phoneNumber", nativeQuery = true)
	User findUserbyPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
