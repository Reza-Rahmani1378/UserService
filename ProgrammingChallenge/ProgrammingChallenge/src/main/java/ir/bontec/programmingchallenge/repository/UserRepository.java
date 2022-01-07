package ir.bontec.programmingchallenge.repository;

import ir.bontec.programmingchallenge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User getUserByUsername(String username);

    User getUserByUserId(String id);


}
