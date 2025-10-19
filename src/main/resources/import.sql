-- Seed data for H2 (dev) to ease local testing
-- Categories
INSERT INTO categories (id, name, description) VALUES (1, 'Eletrônicos', 'Equipamentos e gadgets');
INSERT INTO categories (id, name, description) VALUES (2, 'Hardware', 'Peças e componentes');
INSERT INTO categories (id, name, description) VALUES (3, 'Acessórios', 'Periféricos e acessórios');

-- Products
INSERT INTO products (id, name, price, category_id) VALUES (1, 'Teclado Mecânico', 350.00, 2);
INSERT INTO products (id, name, price, category_id) VALUES (2, 'Mouse Gamer', 199.90, 3);
INSERT INTO products (id, name, price, category_id) VALUES (3, 'Smartphone X', 2999.00, 1);
INSERT INTO products (id, name, price, category_id) VALUES (4, 'Headset USB', 289.50, 3);
INSERT INTO products (id, name, price, category_id) VALUES (5, 'Placa-mãe ATX', 1459.00, 2);

