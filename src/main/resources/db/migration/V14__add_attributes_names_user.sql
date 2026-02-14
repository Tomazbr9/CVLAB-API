ALTER TABLE tb_user
ADD COLUMN first_name VARCHAR(50),
ADD COLUMN last_name VARCHAR(50),
ADD CONSTRAINT name_min_characters CHECK (CHAR_LENGTH(first_name) >= 3);