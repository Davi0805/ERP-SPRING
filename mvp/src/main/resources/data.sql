CREATE TABLE IF NOT EXISTS permissions (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           code VARCHAR(255) NOT NULL UNIQUE,
                                            description VARCHAR(255)
                                     );

-- Insert Core permissions
INSERT INTO permissions (code, description) VALUES
                                                ('MANAGE_USERS', 'Permission to manage users'),
                                                ('VIEW_DASHBOARD', 'Permission to view the dashboard'),
                                                ('MANAGE_COMPANY_CONFIG', 'Permission to manage company configurations');

-- Insert Importação permissions
INSERT INTO permissions (code, description) VALUES
                                                ('CREATE_CONTAINER', 'Permission to create containers'),
                                                ('UPDATE_CONTAINER', 'Permission to update containers'),
                                                ('VIEW_CONTAINER', 'Permission to view containers'),
                                                ('DELETE_CONTAINER', 'Permission to delete containers');

-- Insert Financeiro permissions
INSERT INTO permissions (code, description) VALUES
                                                ('VIEW_INVOICES', 'Permission to view invoices'),
                                                ('APPROVE_PAYMENTS', 'Permission to approve payments'),
                                                ('EXPORT_FINANCIAL_REPORTS', 'Permission to export financial reports');