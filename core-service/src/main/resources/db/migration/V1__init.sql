SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS products;

CREATE TABLE products (
                          id                    INT(11) NOT NULL AUTO_INCREMENT,
                          title                 VARCHAR(255) NOT NULL,
                          price                 DECIMAL(8,2) NOT NULL,
                          category_id           INT(11) NOT NULL,
                          created_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (id),
                          CONSTRAINT FK_CATEGORY_ID FOREIGN KEY (category_id)
                              REFERENCES categories (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
                            id	                INT(11) NOT NULL AUTO_INCREMENT,
                            title               VARCHAR(255) NOT NULL,
                            created_at          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_at          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id                    INT(11) NOT NULL AUTO_INCREMENT,
                       username              VARCHAR(36) NOT NULL,
                       password              VARCHAR(80) NOT NULL,
                       email                 VARCHAR(50) NOT NULL UNIQUE,
                       created_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
                       id                    INT(11) NOT NULL AUTO_INCREMENT,
                       name                  VARCHAR(50) NOT NULL,
                       created_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles (
                             user_id               INT(11) NOT NULL,
                             role_id               INT(11) NOT NULL,
                             created_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             updated_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                             PRIMARY KEY (user_id, role_id),

                             CONSTRAINT FK_USER_ID FOREIGN KEY (user_id)
                                 REFERENCES users (id)
                                 ON DELETE NO ACTION ON UPDATE NO ACTION,

                             CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id)
                                 REFERENCES roles (id)
                                 ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
                        id	                  INT(11) NOT NULL AUTO_INCREMENT,
                        user_id               INT(11) NOT NULL,
                        total_price           DECIMAL(8,2) NOT NULL,
                        created_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (id),
                        CONSTRAINT FK_USER_ID_2 FOREIGN KEY (user_id)
                            REFERENCES users (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS orders_items;

CREATE TABLE orders_items (
                              id	                INT(11) NOT NULL AUTO_INCREMENT,
                              product_id            INT(11) NOT NULL,
                              order_id              INT(11),
                              quantity              INT NOT NULL,
                              price                 DECIMAL(8,2) NOT NULL,
                              price_per_product     DECIMAL(8,2) NOT NULL,
                              created_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              PRIMARY KEY (id),
                              CONSTRAINT FK_ORDER_ID FOREIGN KEY (order_id)
                                  REFERENCES orders (id),
                              CONSTRAINT FK_PRODUCT_ID_ORD_IT FOREIGN KEY (product_id)
                                  REFERENCES products (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;


SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO categories (title)
VALUES
    ('Pet_Food'), ('Pet_Supplements'), ('Pet_Accessories');


INSERT INTO products (title, price, category_id) VALUES ('Набор пробников для кошек', '1249.90', 1);
INSERT INTO products (title, price, category_id) VALUES ('Печенье для собак вкус яблока', '549.90', 2);
INSERT INTO products (title, price, category_id) VALUES ('Гуляш для собак биг пак', '6099.90', 1);
INSERT INTO products (title, price, category_id) VALUES ('Пробник для кошек New', '315.50', 1);
INSERT INTO products (title, price, category_id) VALUES ('Набор кормов для собак', '1769.90', 1);
INSERT INTO products (title, price, category_id) VALUES ('Жвачка для собак и кошек клюква', '1179.90', 2);
INSERT INTO products (title, price, category_id) VALUES ('Чудо фермент для кошек и собак', '2499.90', 2);
INSERT INTO products (title, price, category_id) VALUES ('Влажный корм для собак и кошек органик картофель', '4899.90', 1);
INSERT INTO products (title, price, category_id) VALUES ('Жевательные добавки для щенков', '2599.90', 2);
INSERT INTO products (title, price, category_id) VALUES ('Жевательные добавки для взрослых собак', '1622.50', 2);
INSERT INTO products (title, price, category_id) VALUES ('Дралка для котов №1', '750.00', 3);
INSERT INTO products (title, price, category_id) VALUES ('Дралка для котов №2', '1000.00', 3);
INSERT INTO products (title, price, category_id) VALUES ('Дралка для котов №3', '1250.00', 3);
INSERT INTO products (title, price, category_id) VALUES ('Дралка для котов №4', '1500.00', 3);
INSERT INTO products (title, price, category_id) VALUES ('Дралка для котов №5', '1750.00', 3);




INSERT INTO roles (name)
VALUES
    ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_MANAGER'), ('ROLE_SUPER_ADMIN');


INSERT INTO users (username,password,email)
VALUES
    ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
    ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');


INSERT INTO users_roles (user_id, role_id)
VALUES
    (1, 1),
    (2, 2);
