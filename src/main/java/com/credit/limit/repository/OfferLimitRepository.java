package com.credit.limit.repository;

import com.credit.limit.models.Account;
import com.credit.limit.models.LimitOfferDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OfferLimitRepository extends JpaRepository<LimitOfferDetails, String> {

    @Query(value = "select * from limit_offer_details where offer_id = :offer_id", nativeQuery = true)
    LimitOfferDetails findByOfferId(@Param("offer_id")String offer_id);
    @Query(value = "select * from limit_offer_details where account_id = :account_id and offer_status= :status", nativeQuery = true)
    List<LimitOfferDetails> findByAccountId(@Param("account_id")String accountId,@Param("status")  String status);

    @Query(value = "select * from limit_offer_details where account_id = :account_id and offer_activation_time=:offer_activation_time and offer_status= :status", nativeQuery = true)
    List<LimitOfferDetails> findByAccountIdAndActivationDate(@Param("account_id") String accountId,@Param("offer_activation_time") Date activationDate,@Param("status") String status);
}
