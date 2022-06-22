package springhero.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springhero.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
