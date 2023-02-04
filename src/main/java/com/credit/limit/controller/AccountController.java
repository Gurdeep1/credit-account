package com.credit.limit.controller;

import com.credit.limit.models.Account;
import com.credit.limit.pojos.request.AccountCreationRequest;
import com.credit.limit.pojos.request.response.BaseResponse;
import com.credit.limit.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/account")
public class AccountController {

    @Autowired
    private  AccountService accountService;

    @GetMapping
    public ResponseEntity<BaseResponse<Account>> getAccount(
            @RequestHeader(value = "account_id") String accountId) throws Exception {
        Account account = accountService.getAccount(accountId);
        return new ResponseEntity<>(new BaseResponse<>(account, "Success"), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<String>> createAccount(
            @Valid @RequestBody AccountCreationRequest accountCreationRequest) throws Exception {
        accountService.createAccount(accountCreationRequest);
        return new ResponseEntity<>(new
                BaseResponse("created", "Success"), HttpStatus.CREATED);
    }
}
