CREATE OR REPLACE FUNCTION category_audit_trigger()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        INSERT INTO category_audit(operation_type, operation_timestamp, old_data)
        VALUES ('D', now(), row_to_json(OLD));
        RETURN OLD;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO category_audit(operation_type, operation_timestamp, old_data, new_data)
        VALUES ('U', now(), row_to_json(OLD), row_to_json(NEW));
        RETURN NEW;
    ELSIF (TG_OP = 'INSERT') THEN
        INSERT INTO category_audit(operation_type, operation_timestamp, new_data)
        VALUES ('I', now(), row_to_json(NEW));
        RETURN NEW;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER category_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON category_audit
FOR EACH ROW EXECUTE FUNCTION category_audit_trigger();

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION customer_audit_trigger()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        INSERT INTO customer_audit(operation_type, operation_timestamp, old_data)
        VALUES ('D', now(), row_to_json(OLD));
        RETURN OLD;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO customer_audit(operation_type, operation_timestamp, old_data, new_data)
        VALUES ('U', now(), row_to_json(OLD), row_to_json(NEW));
        RETURN NEW;
    ELSIF (TG_OP = 'INSERT') THEN
        INSERT INTO customer_audit(operation_type, operation_timestamp, new_data)
        VALUES ('I', now(), row_to_json(NEW));
        RETURN NEW;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER customer_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON customer_audit
FOR EACH ROW EXECUTE FUNCTION customer_audit_trigger();

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION persistedorder_audit_trigger()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        INSERT INTO persistedorder_audit(operation_type, operation_timestamp, old_data)
        VALUES ('D', now(), row_to_json(OLD));
        RETURN OLD;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO persistedorder_audit(operation_type, operation_timestamp, old_data, new_data)
        VALUES ('U', now(), row_to_json(OLD), row_to_json(NEW));
        RETURN NEW;
    ELSIF (TG_OP = 'INSERT') THEN
        INSERT INTO persistedorder_audit(operation_type, operation_timestamp, new_data)
        VALUES ('I', now(), row_to_json(NEW));
        RETURN NEW;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER persistedorder_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON persistedorder_audit
FOR EACH ROW EXECUTE FUNCTION persistedorder_audit_trigger();

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION persistedorderitem_audit_trigger()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        INSERT INTO persistedorderitem_audit(operation_type, operation_timestamp, old_data)
        VALUES ('D', now(), row_to_json(OLD));
        RETURN OLD;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO persistedorderitem_audit(operation_type, operation_timestamp, old_data, new_data)
        VALUES ('U', now(), row_to_json(OLD), row_to_json(NEW));
        RETURN NEW;
    ELSIF (TG_OP = 'INSERT') THEN
        INSERT INTO persistedorderitem_audit(operation_type, operation_timestamp, new_data)
        VALUES ('I', now(), row_to_json(NEW));
        RETURN NEW;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER persistedorderitem_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON persistedorderitem_audit
FOR EACH ROW EXECUTE FUNCTION persistedorderitem_audit_trigger();

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION product_audit_trigger()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        INSERT INTO product_audit(operation_type, operation_timestamp, old_data)
        VALUES ('D', now(), row_to_json(OLD));
        RETURN OLD;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO product_audit(operation_type, operation_timestamp, old_data, new_data)
        VALUES ('U', now(), row_to_json(OLD), row_to_json(NEW));
        RETURN NEW;
    ELSIF (TG_OP = 'INSERT') THEN
        INSERT INTO product_audit(operation_type, operation_timestamp, new_data)
        VALUES ('I', now(), row_to_json(NEW));
        RETURN NEW;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER product_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON product_audit
FOR EACH ROW EXECUTE FUNCTION product_audit_trigger();

------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION supplier_audit_trigger()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        INSERT INTO supplier_audit(operation_type, operation_timestamp, old_data)
        VALUES ('D', now(), row_to_json(OLD));
        RETURN OLD;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO supplier_audit(operation_type, operation_timestamp, old_data, new_data)
        VALUES ('U', now(), row_to_json(OLD), row_to_json(NEW));
        RETURN NEW;
    ELSIF (TG_OP = 'INSERT') THEN
        INSERT INTO supplier_audit(operation_type, operation_timestamp, new_data)
        VALUES ('I', now(), row_to_json(NEW));
        RETURN NEW;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER supplier_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON supplier_audit
FOR EACH ROW EXECUTE FUNCTION supplier_audit_trigger();