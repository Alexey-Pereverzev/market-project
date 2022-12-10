SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS products;

CREATE TABLE products (
                       id                    INT(11) NOT NULL AUTO_INCREMENT,
                       title                 VARCHAR(255) NOT NULL,
                       cost                  DECIMAL(8,2) NOT NULL,
                       category_id           INT(11) NOT NULL,
                       created_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       PRIMARY KEY (id),
                       CONSTRAINT FK_CATEGORY_ID FOREIGN KEY (category_id)
                           REFERENCES categories (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO products (title, cost, category_id) VALUES ('Набор пробников для кошек', '1249.90', 1);
INSERT INTO products (title, cost, category_id) VALUES ('Печенье для собак вкус яблока', '549.90', 2);
INSERT INTO products (title, cost, category_id) VALUES ('Гуляш для собак биг пак', '6099.90', 1);
INSERT INTO products (title, cost, category_id) VALUES ('Пробник для кошек New', '315.50', 1);
INSERT INTO products (title, cost, category_id) VALUES ('Набор кормов для собак', '1769.90', 1);
INSERT INTO products (title, cost, category_id) VALUES ('Жвачка для собак и кошек клюква', '1179.90', 2);
INSERT INTO products (title, cost, category_id) VALUES ('Чудо фермент для кошек и собак', '2499.90', 2);
INSERT INTO products (title, cost, category_id) VALUES ('Влажный корм для собак и кошек органик картофель', '4899.90', 1);
INSERT INTO products (title, cost, category_id) VALUES ('Жевательные добавки для щенков', '2599.90', 2);
INSERT INTO products (title, cost, category_id) VALUES ('Жевательные добавки для взрослых собак', '1622.50', 2);
INSERT INTO products (title, cost, category_id) VALUES ('Дралка для котов №1', '750.00', 3);
INSERT INTO products (title, cost, category_id) VALUES ('Дралка для котов №2', '1000.00', 3);
INSERT INTO products (title, cost, category_id) VALUES ('Дралка для котов №3', '1250.00', 3);
INSERT INTO products (title, cost, category_id) VALUES ('Дралка для котов №4', '1500.00', 3);
INSERT INTO products (title, cost, category_id) VALUES ('Дралка для котов №5', '1750.00', 3);


DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
                            id	                INT(11) NOT NULL AUTO_INCREMENT,
                            title               VARCHAR(255) NOT NULL,
                            created_at          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_at          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;


INSERT INTO categories (title)
VALUES
    ('Pet_Food'), ('Pet_Supplements'), ('Pet_Accessories');