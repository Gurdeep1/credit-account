package com.credit.limit.pojos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreationRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("account_id")
    @NotBlank(message = "account id cannot be blank")
    private String accountId;

    @JsonProperty("customer_id")
    @NotBlank(message = "customer id can not be blank")
    private String customerId;

    @JsonProperty( "account_limit")
    private Integer accountLimit;

    @JsonProperty("per_transaction_limit")
    private  Integer perTransactionLimit;
}
