-- Customer Table
CREATE TABLE customer (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255)
);

-- Orders Table
CREATE TABLE orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT,
    total_amount DECIMAL(19, 2),
    status VARCHAR(50),
    created_at TIMESTAMP,
    CONSTRAINT fk_orders_customer FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- OrderItem Table
CREATE TABLE order_item (
    item_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_id VARCHAR(255),
    quantity INT,
    price DECIMAL(19, 2),
    order_id BIGINT NOT NULL,
    CONSTRAINT fk_order_item_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);

