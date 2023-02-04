package com.credit.limit.service;

import com.credit.limit.models.Account;
import com.credit.limit.pojos.request.AccountCreationRequest;
import com.credit.limit.pojos.request.AccountUpdateRequest;

public interface AccountService {
    void createAccount(AccountCreationRequest accountCreationRequest) throws Exception;

    void updateAccount(AccountUpdateRequest updateAccountRequest) throws Exception;

    Account getAccount(String accountId) throws Exception;
}
