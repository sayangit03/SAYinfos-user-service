package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.bean.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
	
	@Query(value="select u from UserDetails u where u.login.uniqueName=?1")
	List<UserDetails> findByUniqueName(String uniqueName);

}
