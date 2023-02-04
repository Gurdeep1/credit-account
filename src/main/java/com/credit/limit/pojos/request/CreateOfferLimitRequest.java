package com.credit.limit.pojos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CreateOfferLimitRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("account_id")
    @NotBlank(message = "account id can not be blank")
    private String accountId;

    @JsonProperty("limit_type")
    @NotBlank(message = "limit type can not be blank")
    private String limitType;

    @JsonProperty("new_limit")
    private Integer newLimit;

    @JsonProperty("offer_activation_time")
    private Date offerActivationTime;

    @JsonProperty("offer_expiration_time")
    @NotNull(message = "offer expiration time can not be blank")
    private Date offerExpirationTime;
}
