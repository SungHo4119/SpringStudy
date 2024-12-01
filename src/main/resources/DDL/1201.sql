

-- Users 테이블에 Enum role 추가
ALTER TABLE Users ADD COLUMN role ENUM('ADMIN', 'USER') NOT NULL DEFAULT 'USER';
