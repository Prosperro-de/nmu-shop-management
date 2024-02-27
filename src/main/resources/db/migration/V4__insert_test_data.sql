-- Insert Categories
INSERT INTO category (category_name) VALUES 
('Electronics'),
('Clothing'),
('Groceries'),
('Furniture'),
('Books');

-- Insert Suppliers
INSERT INTO supplier (supplier_name, contact_name, street_name, city, postal_code, country, phone) VALUES 
('ElectroZavod', 'Ivan Ivanov', 'Electronna St', 'Kyiv', '01001', 'Ukraine', '+380 44 123 4567'),
('FashionHouse', 'Olga Petrenko', 'Modna St', 'Lviv', '79000', 'Ukraine', '+380 32 765 4321'),
('FoodDistributor', 'Sergiy Pavlenko', 'Yizhna St', 'Odessa', '65000', 'Ukraine', '+380 48 654 3210');

-- Insert Products
INSERT INTO product (product_name, supplier_id, category_id, unit_price, quantity_in_stock) VALUES 
('Laptop Model X', 1, 1, 15000.00, 30),
('Smartphone Model Y', 1, 1, 8000.00, 50),
('Jeans Brand A', 2, 2, 1200.00, 100),
('T-Shirt Cool', 2, 2, 500.00, 150),
('Organic Milk 1L', 3, 3, 30.00, 200),
('Bread Loaf', 3, 3, 20.00, 300),
('Wooden Chair', 1, 4, 2500.00, 40),
('E-Reader Gadget', 1, 5, 4000.00, 60),
('Novel "The Kyiv Story"', 1, 5, 300.00, 120),
('Modern Sofa', 1, 4, 10000.00, 20);

-- Insert Customers
INSERT INTO customer (customer_name, contact_name, customer_address, city, postal_code, country, phone) VALUES 
('Mykhailo Kovalenko', 'Mykhailo Kovalenko', 'Pechersk Ln', 'Kyiv', '01001', 'Ukraine', '+380 44 987 6543'),
('Yulia Tymoshenko', 'Yulia Tymoshenko', 'Sichovykh Striltsiv St', 'Lviv', '79000', 'Ukraine', '+380 32 321 6543'),
('Andriy Shevchenko', 'Andriy Shevchenko', 'Deribasivska St', 'Odessa', '65000', 'Ukraine', '+380 48 765 4321');

-- Insert Orders
DO $$
DECLARE
    i INT;
BEGIN
    FOR i IN 1..100 LOOP
        INSERT INTO persistedorder (customer_id, persistedorder_status) VALUES 
        ((SELECT id FROM customer ORDER BY RANDOM() LIMIT 1), 
        'Delivered');
    END LOOP;
END $$;

-- Insert Order Items
DO $$
DECLARE
    i INT;
    order_id INT;
BEGIN
    FOR i IN 1..300 LOOP
        order_id := (SELECT id FROM persistedorder ORDER BY RANDOM() LIMIT 1);
        INSERT INTO persistedorderitem (persistedorder_id, product_id, quantity) VALUES
        (order_id, 
        (SELECT id FROM product ORDER BY RANDOM() LIMIT 1),
        (SELECT TRUNC(RANDOM() * 5 + 1)::INT));
    END LOOP;
END $$;

-- Update Orders delivery time
DO $$
DECLARE
    order_id BIGINT;
BEGIN
    FOR order_id IN SELECT id FROM persistedorder LOOP
        UPDATE persistedorder SET delivered_at =
            TIMESTAMP '2023-01-01 00:00:00' +
            (RANDOM() * (EXTRACT(EPOCH FROM TIMESTAMP '2024-12-31 23:59:59' - TIMESTAMP '2023-01-01 00:00:00'))) * INTERVAL '1 second'
        WHERE id = order_id;
    END LOOP;
END $$;
