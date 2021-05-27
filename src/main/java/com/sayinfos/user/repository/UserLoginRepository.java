package com.sayinfos.user.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sayinfos.user.bean.UserLogin;

public interface UserLoginRepository extends MongoRepository<UserLogin, Integer> {

	List<UserLogin> findByUniqueName(String uniqueName);
}
