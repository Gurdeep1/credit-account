package com.credit.limit.repository;

import com.credit.limit.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query(value = "select * from account where account_id = :account_id", nativeQuery = true)
    Account findByAccountId(@Param("account_id") String accountId);
}
