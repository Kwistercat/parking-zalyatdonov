CREATE TABLE IF NOT EXISTS users
(
    id       int AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled  boolean      NOT NULL
);

CREATE TABLE IF NOT EXISTS user_role
(
    id   int AUTO_INCREMENT PRIMARY KEY,
    ROLES varchar(50) not null,
    user_id int,
    FOREIGN KEY (user_id) REFERENCES users (id)
);



