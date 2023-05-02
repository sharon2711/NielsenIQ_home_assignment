DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS shopper;
DROP TABLE IF EXISTS shelf;

CREATE TABLE product (
    id bigint auto_increment,
    product_id VARCHAR(50) not null,
    category VARCHAR(50) not null,
    brand VARCHAR(50) not null,
    PRIMARY KEY(id),
    UNIQUE(product_id)
);

CREATE index product_id
ON product(product_id);

CREATE TABLE shopper (
    id bigint auto_increment,
    shopper_id VARCHAR(50) not null,
    PRIMARY KEY(id),
    UNIQUE(shopper_id)
);

CREATE index shopper_id
ON shopper(shopper_id);


CREATE TABLE shelf (
    id bigint auto_increment,
    shopper_id VARCHAR(50) not null,
    product_id VARCHAR(50) not null,
    relevancy_score DECIMAL(15, 2),
    PRIMARY KEY(id),
    FOREIGN KEY(shopper_id) REFERENCES shopper(shopper_id),
    FOREIGN KEY(product_id) REFERENCES product(product_id),
);