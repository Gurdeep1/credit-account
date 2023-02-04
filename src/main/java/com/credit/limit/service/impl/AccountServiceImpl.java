package com.credit.limit.service.impl;

import com.credit.limit.models.Account;
import com.credit.limit.pojos.request.AccountCreationRequest;
import com.credit.limit.pojos.request.AccountUpdateRequest;
import com.credit.limit.repository.AccountRepository;
import com.credit.limit.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void createAccount(AccountCreationRequest accountCreationRequest) throws Exception {
        Account existingAccount = accountRepository.findByAccountId(accountCreationRequest.getAccountId());
        if(!ObjectUtils.isEmpty(existingAccount)) {
            throw new Exception("account details already exist");
        }
        Account newAccount = Account.builder()
                .accountId(accountCreationRequest.getAccountId())
                .customerId(accountCreationRequest.getCustomerId())
                .status("ACTIVE")
                .build();
        if(!ObjectUtils.isEmpty(accountCreationRequest.getAccountLimit())){
            newAccount.setAccountLimit(accountCreationRequest.getAccountLimit());
        }
        if(!ObjectUtils.isEmpty(accountCreationRequest.getPerTransactionLimit())){
            newAccount.setAccountLimit(accountCreationRequest.getPerTransactionLimit());
        }
        accountRepository.save(newAccount);
    }

    @Override
    public void updateAccount(AccountUpdateRequest updateAccountRequest) throws Exception {
        Account existingAccount = accountRepository.findByAccountId(updateAccountRequest.getAccountId());
        if(ObjectUtils.isEmpty(existingAccount)) {
            throw new Exception("account details does not exist");
        }
        if(!ObjectUtils.isEmpty(updateAccountRequest.getAccountLimit())){
            existingAccount.setLastAccountLimit(existingAccount.getAccountLimit());
            existingAccount.setAccountLimitUpdateTime(new Date());
            existingAccount.setAccountLimit(updateAccountRequest.getAccountLimit());
        }
        if(!ObjectUtils.isEmpty(updateAccountRequest.getPerTransactionLimit())){
            existingAccount.setLastPerTransactionLimit(existingAccount.getPerTransactionLimit());
            existingAccount.setPerTransactionLimitUpdateTime(new Date());
            existingAccount.setPerTransactionLimit(updateAccountRequest.getPerTransactionLimit());
        }
        accountRepository.save(existingAccount);
    }

    @Override
    public Account getAccount(String accountId) throws Exception {
        Account account = accountRepository.findByAccountId(accountId);
        if(ObjectUtils.isEmpty(account)){
            throw new Exception("account does not exist");
        }
        return account;
    }
}
