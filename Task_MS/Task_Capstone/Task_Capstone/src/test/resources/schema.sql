DROP TABLE IF EXISTS task;

CREATE TABLE task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255), 
    description TEXT,
    status VARCHAR(50)
);
