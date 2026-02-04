CREATE TABLE tb_projects (
    id UUID PRIMARY KEY,
    name_work VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    bdi NUMERIC(5, 2) NOT NULL DEFAULT 0.0,

    user_id UUID NOT NULL,

    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES tb_user(id)
);