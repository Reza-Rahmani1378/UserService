package ir.bontec.programmingchallenge.repository;

import ir.bontec.programmingchallenge.entities.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NormalUserRepository extends JpaRepository<NormalUser, Long>, JpaSpecificationExecutor<NormalUser> {
}
