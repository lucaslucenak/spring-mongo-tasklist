package com.unifacisa.tasklist.services;

import com.unifacisa.tasklist.exceptions.EmailAlreadyRegisteredException;
import com.unifacisa.tasklist.exceptions.IncompatibleIdsException;
import com.unifacisa.tasklist.exceptions.ResourceNotFoundException;
import com.unifacisa.tasklist.models.TaskModel;
import com.unifacisa.tasklist.models.UserModel;
import com.unifacisa.tasklist.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public UserModel findUserById(String userId) {
        Optional<UserModel> userModelOptional = userRepository.findById(userId);

        if (userModelOptional.isPresent()) {
            UserModel userModel = userModelOptional.get();

            List<TaskModel> tasks = taskService.findTasksByUserId(userId);
            userModel.setTasks(tasks);
            return userModelOptional.get();
        } else {
            throw new ResourceNotFoundException("Resource: User. Not found with id: " + userId);
        }
    }

    @Transactional
    public UserModel findUserByUsername(String username) {
        Optional<UserModel> userModelOptional = userRepository.findUserModelByUsername(username);

        if (userModelOptional.isPresent()) {
            UserModel userModel = userModelOptional.get();

            return userModelOptional.get();
        } else {
            throw new ResourceNotFoundException("Resource: User. Not found with username: " + username);
        }
    }

    @Transactional
    public List<UserModel> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public UserModel saveUser(UserModel userModel) {

        if (userRepository.existsByEmail(userModel.getEmail())) throw new EmailAlreadyRegisteredException("Email already registered");

        String encryptedPassword = bCryptPasswordEncoder.encode(userModel.getPassword());
        userModel.setPassword(encryptedPassword);

        return userRepository.save(userModel);
    }

    @Transactional
    public UserModel updateUser(String userId, UserModel updatedUserModel) {
        if (!userId.equals(updatedUserModel.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + userId + ", Body Id: " + updatedUserModel.getId());
        }

        UserModel existingUserModel = this.findUserById(userId);

        if (userRepository.existsByEmail(updatedUserModel.getEmail()) && !Objects.equals(updatedUserModel.getEmail(), existingUserModel.getEmail())) {
            throw new EmailAlreadyRegisteredException("Email already registered");
        }


        updatedUserModel.setCreatedAt(existingUserModel.getCreatedAt());
        BeanUtils.copyProperties(updatedUserModel, existingUserModel);
        return userRepository.save(existingUserModel);
    }

    @Transactional
    public boolean existsByEmail(String userEmail) {
        return userRepository.existsByEmail(userEmail);
    }

    @Transactional
    public void deleteUserById(String userId) {
        if (userRepository.existsById(userId)) {
            taskService.deleteByUserId(userId);
            userRepository.deleteById(userId);
        } else {
            throw new ResourceNotFoundException("Resource: User. Not found with id: " + userId);
        }
    }
}
