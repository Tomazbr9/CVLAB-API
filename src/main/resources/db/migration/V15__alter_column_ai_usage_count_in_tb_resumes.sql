UPDATE tb_resumes
SET ai_usage_count = 0
WHERE ai_usage_count IS NULL;

ALTER TABLE tb_resumes
ALTER COLUMN ai_usage_count SET DEFAULT 0;

ALTER TABLE tb_resumes
ALTER COLUMN ai_usage_count SET NOT NULL;