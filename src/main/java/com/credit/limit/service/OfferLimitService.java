package com.credit.limit.service;

import com.credit.limit.models.LimitOfferDetails;
import com.credit.limit.pojos.request.CreateOfferLimitRequest;
import com.credit.limit.pojos.request.GetLimitOfferRequest;

import java.util.List;

public interface OfferLimitService {
    String createOffer(CreateOfferLimitRequest createOfferLimitRequest) throws Exception;

    List<LimitOfferDetails> fetchLimitOffers(GetLimitOfferRequest getLimitOfferRequest) throws Exception;

    void updateLimitOffer(String offerId, String status) throws Exception;
}
