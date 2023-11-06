package com.unifacisa.tasklist.repositories;

import com.unifacisa.tasklist.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {
    Optional<UserModel> findUserByEmail(String userEmail);
    boolean existsByEmail(String userEmail);


}
