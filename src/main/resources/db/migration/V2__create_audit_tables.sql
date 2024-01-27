CREATE TABLE IF NOT EXISTS category_audit (
    id BIGSERIAL PRIMARY KEY,
    operation_type CHAR(1),
    operation_timestamp TIMESTAMP DEFAULT now(),
    old_data JSONB,
    new_data JSONB
);

CREATE TABLE IF NOT EXISTS customer_audit (
    id BIGSERIAL PRIMARY KEY,
    operation_type CHAR(1),
    operation_timestamp TIMESTAMP DEFAULT now(),
    old_data JSONB,
    new_data JSONB
);

CREATE TABLE IF NOT EXISTS persistedorder_audit (
    id BIGSERIAL PRIMARY KEY,
    operation_type CHAR(1),
    operation_timestamp TIMESTAMP DEFAULT now(),
    old_data JSONB,
    new_data JSONB
);

CREATE TABLE IF NOT EXISTS persistedorderitem_audit (
    id BIGSERIAL PRIMARY KEY,
    operation_type CHAR(1),
    operation_timestamp TIMESTAMP DEFAULT now(),
    old_data JSONB,
    new_data JSONB
);

CREATE TABLE IF NOT EXISTS product_audit (
    id BIGSERIAL PRIMARY KEY,
    operation_type CHAR(1),
    operation_timestamp TIMESTAMP DEFAULT now(),
    old_data JSONB,
    new_data JSONB
);

CREATE TABLE IF NOT EXISTS supplier_audit (
    id BIGSERIAL PRIMARY KEY,
    operation_type CHAR(1),
    operation_timestamp TIMESTAMP DEFAULT now(),
    old_data JSONB,
    new_data JSONB
);