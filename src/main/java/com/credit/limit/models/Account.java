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
@Table(name = "account")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Column(name = "customer_id")
    private  String customerId;

    @Column(name = "account_limit")
    private Integer accountLimit;

    @Column(name = "per_transaction_limit")
    private  Integer perTransactionLimit;

    @Column(name = "last_account_limit")
    private Integer lastAccountLimit;

    @Column(name = "last_per_transaction_limit")
    private Integer lastPerTransactionLimit;

    @Column(name = "account_limit_update_time")
    private Date accountLimitUpdateTime;

    @Column(name = "per_transaction_limit_update_time")
    private Date perTransactionLimitUpdateTime;

    @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private Date modifiedAt;

}
