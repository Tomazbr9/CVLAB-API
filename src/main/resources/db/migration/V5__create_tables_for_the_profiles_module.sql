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

CREATE TABLE tb_formations (
    id UUID PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL ,
    institution_name VARCHAR(100) NOT NULL,
    course_location VARCHAR(255),
    start_date DATE NOT NULL,
    end_date DATE,
    description TEXT,
    formation_status VARCHAR(10) NOT NULL,
    profile_id UUID NOT NULL,

    CONSTRAINT fk_profile
        FOREIGN KEY (profile_id)
            REFERENCES tb_profiles(id)
            ON DELETE CASCADE

);

CREATE TABLE tb_projects (
    id UUID PRIMARY KEY,
    project_name VARCHAR(255) NOT NULL,
    technologies_used VARCHAR(255),
    project_location VARCHAR(255),
    start_date DATE,
    end_date DATE,
    description TEXT,
    link TEXT,
    profile_id UUID NOT NULL,

    CONSTRAINT fk_profile
        FOREIGN KEY (profile_id)
            REFERENCES tb_profiles(id)
            ON DELETE CASCADE

);

CREATE TABLE tb_skills (
    id UUID PRIMARY KEY,
    skill_name VARCHAR(255) NOT NULL,
    description TEXT,
    level VARCHAR(10),
    profile_id UUID NOT NULL,

    CONSTRAINT fk_profile
        FOREIGN KEY (profile_id)
            REFERENCES tb_profiles(id)
            ON DELETE CASCADE
);

CREATE TABLE tb_links (
    id UUID PRIMARY KEY,
    url TEXT NOT NULL,
    site_name VARCHAR(50) NOT NULL,
    profile_id UUID NOT NULL,

    CONSTRAINT fk_profile
        FOREIGN KEY (profile_id)
            REFERENCES tb_profiles(id)
            ON DELETE CASCADE
);

CREATE TABLE tb_courses(
    id UUID PRIMARY KEY,
    course_name VARCHAR(50) NOT NULL,
    institution_name VARCHAR(50) NOT NULL,
    course_location VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    work_load INTEGER NOT NULL,
    description TEXT,
    profile_id UUID NOT NULL,

    CONSTRAINT fk_profile
        FOREIGN KEY (profile_id)
            REFERENCES tb_profiles(id)
            ON DELETE CASCADE

);

