package com.ada.mongodb.adaMongoDBClass.repository.mongo;

import com.ada.mongodb.adaMongoDBClass.model.User;
import com.ada.mongodb.adaMongoDBClass.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserMongoImpl implements UserRepository {

    private final UserMongoRepository userMongoRepository;


    public UserMongoImpl(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userMongoRepository.findAll();
    }

    @Override
    public User findUserById(String idUser) {
        return userMongoRepository.findById(idUser).get();
    }

    @Override
    public User createUser(User user) {
        return userMongoRepository.save(user);
    }

    @Override
    public Boolean updateUser(String idUser, User user) {
        User userFound = findUserById(idUser);
        if (userFound != null){
            userFound.updateUser(user);
            userMongoRepository.save(userFound);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteUser(String idUser) {
        User userFound = findUserById(idUser);
        if (userFound != null){
            userMongoRepository.delete(userFound);
            return true;
        }
        return false;
    }
}
