package com.credit.limit.service.impl;

import com.credit.limit.constants.CreditAccountConstants;
import com.credit.limit.models.Account;
import com.credit.limit.models.LimitOfferDetails;
import com.credit.limit.pojos.request.AccountUpdateRequest;
import com.credit.limit.pojos.request.CreateOfferLimitRequest;
import com.credit.limit.pojos.request.GetLimitOfferRequest;
import com.credit.limit.repository.OfferLimitRepository;
import com.credit.limit.service.AccountService;
import com.credit.limit.service.OfferLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.credit.limit.constants.CreditAccountConstants.ACCEPTED;
import static com.credit.limit.constants.CreditAccountConstants.REJECTED;

@Service
public class OfferLimitServiceImpl implements OfferLimitService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private OfferLimitRepository offerLimitRepository;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public String createOffer(CreateOfferLimitRequest createOfferLimitRequest) throws Exception {
        validateOfferLimitRequest(createOfferLimitRequest);
        Account account = accountService.getAccount(createOfferLimitRequest.getAccountId());
        if(ObjectUtils.isEmpty(account)){
            throw new Exception("account does not exist with account id");
        }
        LimitOfferDetails limitOfferDetails = LimitOfferDetails.builder()
                .offerId(UUID.randomUUID().toString())
                .limitType(createOfferLimitRequest.getLimitType())
                .accountId(createOfferLimitRequest.getAccountId())
                .limitValue(createOfferLimitRequest.getNewLimit())
                .offerExpirationTime(createOfferLimitRequest.getOfferExpirationTime())
                .offerActivationTime(createOfferLimitRequest.getOfferActivationTime())
                .status("ACTIVE")
                .build();

        AccountUpdateRequest accountUpdateRequest = AccountUpdateRequest.builder()
                .accountId(createOfferLimitRequest.getAccountId())
                .build();
        if(limitOfferDetails.getLimitType().equals(CreditAccountConstants.LIMIT_TYPE.ACCOUNT_LIMIT.name())){
            if(!ObjectUtils.isEmpty(account.getAccountLimit()) && createOfferLimitRequest.getNewLimit()<=account.getAccountLimit()){
                throw new Exception("new account limit is less than old limit");
            }
            accountUpdateRequest.setAccountLimit(limitOfferDetails.getLimitValue());
        }
        if(limitOfferDetails.getLimitType().equals(CreditAccountConstants.LIMIT_TYPE.PER_TRANSACTION_LIMIT.name())){
            if(!ObjectUtils.isEmpty(account.getPerTransactionLimit()) && createOfferLimitRequest.getNewLimit()<=account.getPerTransactionLimit()){
                throw new Exception("new account limit is less than old limit");
            }
            accountUpdateRequest.setPerTransactionLimit(limitOfferDetails.getLimitValue());
        }

        accountService.updateAccount(accountUpdateRequest);

        offerLimitRepository.save(limitOfferDetails);

        return limitOfferDetails.getOfferId();

    }

    private void validateOfferLimitRequest(CreateOfferLimitRequest createOfferLimitRequest) throws Exception {
        if(ObjectUtils.isEmpty(createOfferLimitRequest.getOfferActivationTime())){
            createOfferLimitRequest.setOfferActivationTime(new Date());
        }
        if(!ObjectUtils.isEmpty(createOfferLimitRequest.getOfferActivationTime())) {
            if (createOfferLimitRequest.getOfferExpirationTime().before(createOfferLimitRequest.getOfferActivationTime())) {
                throw new Exception("expiration date before activation date");
            }
        }
        if(!(createOfferLimitRequest.getLimitType().equals(CreditAccountConstants.LIMIT_TYPE.ACCOUNT_LIMIT.name())) && !(createOfferLimitRequest.getLimitType().equals(CreditAccountConstants.LIMIT_TYPE.PER_TRANSACTION_LIMIT.name()))){
            throw new Exception("invalid limit type");
        }
    }

    @Override
    public List<LimitOfferDetails> fetchLimitOffers(GetLimitOfferRequest getLimitOfferRequest) throws Exception {
        Account account = accountService.getAccount(getLimitOfferRequest.getAccountId());
        if(ObjectUtils.isEmpty(account)){
            throw new Exception("Account details does not exist");
        }
        List<LimitOfferDetails> limitOfferDetails;
        if(ObjectUtils.isEmpty(getLimitOfferRequest.getActivationDate())) {
            limitOfferDetails = offerLimitRepository.findByAccountId(getLimitOfferRequest.getAccountId(), "ACTIVE");
        }else {
            limitOfferDetails = offerLimitRepository.findByAccountIdAndActivationDate(getLimitOfferRequest.getAccountId(), getLimitOfferRequest.getActivationDate(), "ACTIVE");
        }
        if(ObjectUtils.isEmpty(limitOfferDetails)){
            throw new Exception("offer details not present for the given account");
        }
        return limitOfferDetails;
    }

    @Override
    public void updateLimitOffer(String offerId, String status) throws Exception {
        validateOfferStatus(status);
        LimitOfferDetails limitOfferDetails = offerLimitRepository.findByOfferId(offerId);
        if(ObjectUtils.isEmpty(limitOfferDetails)){
            throw new Exception("offer details does not exist");
        }
        if(limitOfferDetails.getStatus().equals(REJECTED) || limitOfferDetails.getStatus().equals(ACCEPTED)){
            throw new Exception("offer already acknowledge");
        }
        limitOfferDetails.setStatus(status);
        offerLimitRepository.save(limitOfferDetails);
    }

    private void validateOfferStatus(String status) throws Exception {
        if(!status.equals(ACCEPTED) && !status.equals(REJECTED)){
            throw new Exception("invalid offer status");
        }
    }
}
