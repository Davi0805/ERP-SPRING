CREATE TABLE IF NOT EXISTS permissions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS role_permission (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES permissions(id) ON DELETE CASCADE
);

INSERT INTO permissions (code, description) VALUES
    ('MANAGE_USERS', 'Permission to manage users'),
    ('VIEW_DASHBOARD', 'Permission to view the dashboard'),
    ('MANAGE_COMPANY_CONFIG', 'Permission to manage company configurations');

INSERT INTO permissions (code, description) VALUES
    ('CREATE_CONTAINER', 'Permission to create containers'),
    ('CREATE_CONTAINER_MOVEMENT', 'Permission to create containers movements'),
    ('UPDATE_CONTAINER', 'Permission to update containers'),
    ('VIEW_CONTAINER', 'Permission to view containers'),
    ('VIEW_CONTAINER_MOVEMENTS', 'Permission to view containers movements'),
    ('DELETE_CONTAINER', 'Permission to delete containers');

INSERT INTO permissions (code, description) VALUES
    ('CREATE_IMPORT_ORDER', 'Permission to create import orders'),
    ('UPDATE_IMPORT_ORDER', 'Permission to update import orders'),
    ('VIEW_IMPORT_ORDER', 'Permission to view import orders'),
    ('DELETE_IMPORT_ORDER', 'Permission to delete import orders'),
    ('CREATE_SHIPMENT', 'Permission to create shipments'),
    ('UPDATE_SHIPMENT', 'Permission to update shipments'),
    ('VIEW_SHIPMENT', 'Permission to view shipments'),
    ('DELETE_SHIPMENT', 'Permission to delete shipments');

INSERT INTO permissions (code, description) VALUES
    ('VIEW_PORT', 'Permission to view ports'),
    ('CREATE_PORT', 'Permission to create ports'),
    ('UPDATE_PORT', 'Permission to update ports'),
    ('DELETE_PORT', 'Permission to delete ports'),
    ('VIEW_SHIPS', 'Permission to view ships'),
    ('CREATE_SHIPS', 'Permission to create ships'),
    ('UPDATE_SHIPS', 'Permission to update ships'),
    ('DELETE_SHIPS', 'Permission to delete ships');

INSERT INTO permissions (code, description) VALUES
    ('UPLOAD_DOCUMENT', 'Permission to upload documents'),
    ('DOWNLOAD_DOCUMENT', 'Permission to download documents'),
    ('VIEW_LIST_OF_DOCUMENT', 'Permission to view list of documents'),
    ('DELETE_DOCUMENT', 'Permission to delete documents');


INSERT INTO permissions (code, description) VALUES
    ('VIEW_INVOICES', 'Permission to view invoices'),
    ('APPROVE_PAYMENTS', 'Permission to approve payments'),
    ('EXPORT_FINANCIAL_REPORTS', 'Permission to export financial reports');

INSERT INTO roles (name) VALUES ('admin');

INSERT INTO role_permission (role_id, permission_id)
SELECT r.id, p.id
FROM roles r
JOIN permissions p ON 1=1
WHERE r.name = 'admin';
