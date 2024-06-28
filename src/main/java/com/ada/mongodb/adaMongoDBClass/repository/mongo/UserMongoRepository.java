package com.ada.mongodb.adaMongoDBClass.repository.mongo;

import com.ada.mongodb.adaMongoDBClass.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<User, String> {
}
