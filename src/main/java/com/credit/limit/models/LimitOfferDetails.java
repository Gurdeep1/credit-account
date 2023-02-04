package com.credit.limit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "limit_offer_details")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class LimitOfferDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "offer_id", nullable = false)
    private  String offerId;

    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Column(name = "limit_type")
    private  String limitType;

    @Column(name = "limit_value")
    private Integer limitValue;

    @Column(name = "offer_status")
    private String status;

    @Column(name = "offer_activation_time")
    private Date offerActivationTime;

    @Column(name = "offer_expiration_time")
    private Date offerExpirationTime;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private Date modifiedAt;
}
