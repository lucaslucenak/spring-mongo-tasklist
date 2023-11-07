package com.unifacisa.tasklist.repositories;

import com.unifacisa.tasklist.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {
    boolean existsByEmail(String userEmail);
    boolean existsByUsername(String username);
    Optional<UserModel> findUserModelByUsername(String username);
    UserDetails findUserDetailsByUsername(String username);


}
