CREATE TABLE tb_templates (
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    thumbnail_url VARCHAR(255) NOT NULL ,
    is_premium BOOLEAN NOT NULL
);