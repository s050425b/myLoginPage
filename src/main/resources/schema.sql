CREATE TABLE orders (
    order_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (order_id, item_id)
);

CREATE TABLE users (
    id INT AUTO_INCREMENT NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    PRIMARY KEY (id, username)
);


INSERT INTO users (username,password)
VALUES ('jasondemo', 'password');