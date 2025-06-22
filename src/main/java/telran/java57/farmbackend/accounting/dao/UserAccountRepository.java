package telran.java57.farmbackend.accounting.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.java57.farmbackend.accounting.model.User;

public interface UserAccountRepository extends MongoRepository<User, String> {

}
