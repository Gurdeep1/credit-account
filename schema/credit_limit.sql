
CREATE TABLE account (
                        account_id VARCHAR(50) NOT NULL,
                        customer_id VARCHAR(50) NOT NULL,
                        account_limit  INTEGER,
                        per_transaction_limit INTEGER ,
                        last_account_limit INTEGER,
                        last_per_transaction_limit INTEGER,
                        account_limit_update_time timestamp ,
                        per_transaction_limit_update_time timestamp  ,
                        status  VARCHAR(20) DEFAULT 'ACTIVE' NOT NULL,
                        created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                        modified_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                        CONSTRAINT account_pk PRIMARY KEY (account_id)
);

CREATE INDEX account_id_status_idx ON account (account_id, status);
CREATE INDEX created_at_idx ON account (created_at);

CREATE TABLE limit_offer_details (
                        offer_id VARCHAR(50) NOT NULL,
                        account_id VARCHAR(50) NOT NULL,
                        limit_type  VARCHAR(50) NOT NULL,
                        limit_value INTEGER NOT NULL ,
                        offer_status  VARCHAR(20) DEFAULT 'ACTIVE' NOT NULL,
                        offer_activation_time timestamp default CURRENT_TIMESTAMP NOT NULL,
                        offer_expiration_time timestamp  NOT NULL,
                        created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                        modified_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                        CONSTRAINT limit_offer_details_pk PRIMARY KEY (offer_id)
);

CREATE INDEX limit_account_id_status_idx ON limit_offer_details (account_id, offer_status);
CREATE INDEX limit_offer_created_at_idx ON limit_offer_details (created_at);
