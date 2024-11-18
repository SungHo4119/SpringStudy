Create Table users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(10) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT unique_user_name UNIQUE (user_name)
);

Create Table boards (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    title VARCHAR(20) NOT NULL,
    content VARCHAR(500) NOT NULL,
    create_at DATETIME NOT NULL,
    update_at DATETIME,
    PRIMARY KEY (ID),
    FOREIGN KEY (user_id) REFERENCES users(ID)
);