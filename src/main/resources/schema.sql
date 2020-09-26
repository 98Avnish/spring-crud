DROP TABLE IF EXISTS product;

CREATE TABLE products (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  quantity INT NOT NULL,
  version INT NOT NULL
);

INSERT INTO products (id, name, quantity, version) VALUES
  (1, 'P1', 10, 1),
  (2, 'P2', 100, 1),
  (3, 'P3', 1000, 1);