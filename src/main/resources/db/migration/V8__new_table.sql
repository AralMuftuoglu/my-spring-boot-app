CREATE TABLE products (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          price DECIMAL(10,2) NOT NULL,
                          category_id TINYINT NULL,
                          CONSTRAINT products_categories_fk FOREIGN KEY (category_id) REFERENCES categories(id)
);
