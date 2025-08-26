ALTER TABLE products DROP FOREIGN KEY products_categories_fk;

ALTER TABLE products
    ADD CONSTRAINT products_categories_fk
        FOREIGN KEY (category_id)
            REFERENCES categories(id)
            ON DELETE RESTRICT;
