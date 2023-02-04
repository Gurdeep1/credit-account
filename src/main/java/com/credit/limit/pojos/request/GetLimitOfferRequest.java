package com.credit.limit.pojos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class GetLimitOfferRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("account_id")
    @NotBlank(message = "account id can not be blank")
    private String accountId;

    @JsonProperty("activation_date")
    private Date activationDate;
}
