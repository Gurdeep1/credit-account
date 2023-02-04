package com.credit.limit.controller;

import com.credit.limit.models.Account;
import com.credit.limit.models.LimitOfferDetails;
import com.credit.limit.pojos.request.AccountCreationRequest;
import com.credit.limit.pojos.request.CreateOfferLimitRequest;
import com.credit.limit.pojos.request.GetLimitOfferRequest;
import com.credit.limit.pojos.request.response.BaseResponse;
import com.credit.limit.service.OfferLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/offer")
public class LimitOfferController {

    @Autowired
    private OfferLimitService offerLimitService;

    @PostMapping
    public ResponseEntity<BaseResponse<String>> createOffer(
            @Valid @RequestBody CreateOfferLimitRequest createOfferLimitRequest) throws Exception {
        String offerId = offerLimitService.createOffer(createOfferLimitRequest);
        return new ResponseEntity<>(new BaseResponse<>(offerId,"Success"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateOffer(
            @RequestHeader("offer_id") String offerId,
            @RequestHeader("status")String status) throws Exception {
        offerLimitService.updateLimitOffer(offerId, status);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<LimitOfferDetails>>> getOffer(
            @Valid @RequestBody GetLimitOfferRequest getLimitOfferRequest) throws Exception {
        List<LimitOfferDetails> offerDetails = offerLimitService.fetchLimitOffers(getLimitOfferRequest);
        return new ResponseEntity<>(new BaseResponse<>(offerDetails,"Success"), HttpStatus.OK);
    }
}
