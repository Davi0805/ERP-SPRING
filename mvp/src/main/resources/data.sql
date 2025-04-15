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
    ('UPDATE_CONTAINER', 'Permission to update containers'),
    ('VIEW_CONTAINER', 'Permission to view containers'),
    ('DELETE_CONTAINER', 'Permission to delete containers');

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
