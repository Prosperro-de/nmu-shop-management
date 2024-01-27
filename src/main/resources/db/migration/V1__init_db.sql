-- Створення послідовностей
CREATE SEQUENCE IF NOT EXISTS seq_category_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE IF NOT EXISTS seq_supplier_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE IF NOT EXISTS seq_product_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE IF NOT EXISTS seq_customer_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE IF NOT EXISTS seq_persistedorder_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE IF NOT EXISTS seq_persistedorderitem_id INCREMENT BY 1 START WITH 1;

CREATE TABLE IF NOT EXISTS category (
    id BIGINT DEFAULT nextval('seq_category_id'),
    category_name text NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS supplier (
    id BIGINT DEFAULT nextval('seq_supplier_id'),
    supplier_name text NOT NULL,
    contact_name text,
    street_name text,
    city text,
    postal_code text,
    country text,
    phone text,
    CONSTRAINT pk_supplier PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product (
    id BIGINT DEFAULT nextval('seq_product_id'),
    product_name text NOT NULL,
    supplier_id BIGINT,
    category_id BIGINT,
    unit_price DECIMAL(10, 2),
    quantity_in_stock INT,

    CONSTRAINT pk_product PRIMARY KEY (id),
    FOREIGN KEY (supplier_id) REFERENCES supplier(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE IF NOT EXISTS customer (
    id BIGINT DEFAULT nextval('seq_customer_id'),
    customer_name text NOT NULL,
    contact_name text,
    customer_address text,
    city text,
    postal_code text,
    country text,
    phone text,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS persistedorder (
    id BIGINT DEFAULT nextval('seq_persistedorder_id'),
    customer_id BIGINT,
    ordered_at DATE DEFAULT now() - INTERVAL '1 year',
    delivered_at DATE,
    persistedorder_status text,
    CONSTRAINT pk_persistedorder PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE IF NOT EXISTS persistedorderitem (
    id BIGINT DEFAULT nextval('seq_persistedorderitem_id'),
    persistedorder_id BIGINT,
    product_id BIGINT,
    quantity INT,
    CONSTRAINT pk_persistedorderitem PRIMARY KEY (id),
    FOREIGN KEY (persistedorder_id) REFERENCES persistedorder(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE INDEX IF NOT EXISTS idx_order_delivered_at_not_null ON persistedorder(delivered_at);

ALTER SEQUENCE seq_category_id OWNED BY category.id;
ALTER SEQUENCE seq_supplier_id OWNED BY supplier.id;
ALTER SEQUENCE seq_product_id OWNED BY product.id;
ALTER SEQUENCE seq_customer_id OWNED BY customer.id;
ALTER SEQUENCE seq_persistedorder_id OWNED BY persistedorder.id;
ALTER SEQUENCE seq_persistedorderitem_id OWNED BY persistedorderitem.id;
