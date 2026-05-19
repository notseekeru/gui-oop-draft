
-- USERS TABLE
CREATE TABLE IF NOT EXISTS users (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(63) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL -- Plain text for now
);

-- PRODUCTS TABLE
CREATE TABLE IF NOT EXISTS products (
    product_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price INTEGER NOT NULL,
    stock_quantity INTEGER NOT NULL DEFAULT 10,
    image_url VARCHAR(255)
);

-- CART_ITEMS TABLE
CREATE TABLE IF NOT EXISTS cart_items (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    product_id INTEGER,
    quantity INTEGER DEFAULT 1,
    UNIQUE(user_id, product_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id),
    FOREIGN KEY(product_id) REFERENCES products(product_id)
    FOREIGN KEY(product_id) REFERENCES products(product_id)
);

-- Sample user for testing;
INSERT INTO users (username, password) VALUES ('123', '123');