create table categories
(
    id              bigserial primary key,
    title           varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table products
(
    id              bigserial primary key,
    title           varchar(255),
    price           numeric(8, 2) not null,
    category_id     bigint references categories (id),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into categories (title) values ('Pet_Food'), ('Pet_Supplements'), ('Pet_Accessories');

insert into products (title, price, category_id)
values
('Набор пробников для кошек', 1249.90, 1),
('Печенье для собак вкус яблока', 549.90, 2),
('Гуляш для собак биг пак', 6099.90, 1),
('Пробник для кошек New', 315.50, 1),
('Набор кормов для собак', 1769.90, 1),
('Жвачка для собак и кошек клюква', 1179.90, 2),
('Чудо фермент для кошек и собак', 2499.90, 2),
('Влажный корм для собак и кошек органик картофель', 4899.90, 1),
('Жевательные добавки для щенков', 2599.90, 2),
('Жевательные добавки для взрослых собак', 1622.50, 2),
('Дралка для котов №1', 750.00, 3),
('Дралка для котов №2', 1000.00, 3),
('Дралка для котов №3', 1250.00, 3),
('Дралка для котов №4', 1500.00, 3),
('Дралка для котов №5', 1750.00, 3);

create table orders
(
    id              bigserial primary key,
    username        varchar(255),
    total_price     numeric(8, 2),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table orders_items
(
    id                      bigserial primary key,
    order_id                bigint references orders (id),
    product_id              bigint references products (id),
    price_per_product       numeric(8, 2),
    quantity                int,
    price                   numeric(8, 2),
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);