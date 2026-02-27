CREATE TABLE tb_resumes(
    id UUID PRIMARY KEY,
    job_description TEXT,
    optimized_json JSONB,
    is_paid_single BOOLEAN,
    created_at TIMESTAMP NOT NULL,
    update_at TIMESTAMP NOT NULL,
    user_id UUID,

    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES tb_users(id) ON DELETE CASCADE

)