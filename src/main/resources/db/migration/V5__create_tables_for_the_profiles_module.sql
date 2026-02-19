CREATE TABLE tb_profiles (
    id UUID PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255),
    professional_summary TEXT NOT NULL,
    user_id UUID NOT NULL,

    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES tb_users(id)
            ON DELETE CASCADE
);

CREATE TABLE tb_experiences (
    id UUID PRIMARY KEY,
    job_title VARCHAR(255) NOT NULL,
    company_name VARCHAR(100) NOT NULL,
    work_place VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    description TEXT,
    profile_id UUID NOT NULL,

    CONSTRAINT fk_profile
        FOREIGN KEY (profile_id)
            REFERENCES tb_profiles(id)
            ON DELETE CASCADE
);

