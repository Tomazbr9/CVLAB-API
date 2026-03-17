CREATE TABLE tb_subscription (
    id UUID PRIMARY KEY,
    plan_type VARCHAR(10) NOT NULL,
    status VARCHAR(20) NOT NULL,
    google_subscription_id VARCHAR(255),
    purchase_token VARCHAR(1000),
    expires_at TIMESTAMP,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    user_id UUID UNIQUE,

    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES tb_users(id) ON DELETE CASCADE
);