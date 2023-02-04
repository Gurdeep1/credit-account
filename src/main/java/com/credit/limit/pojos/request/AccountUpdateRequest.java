package com.credit.limit.pojos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateRequest implements Serializable {

    @JsonProperty("account_id")
    @NotBlank(message = "account id cannot be blank")
    private String accountId;

    @JsonProperty( "account_limit")
    private Integer accountLimit;

    @JsonProperty("per_transaction_limit")
    private  Integer perTransactionLimit;
}
