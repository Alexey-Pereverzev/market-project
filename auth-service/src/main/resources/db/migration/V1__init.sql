SET FOREIGN_KEY_CHECKS = 0;

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

SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO roles (name)
VALUES
    ('ROLE_USER'), ('ROLE_ADMIN');


INSERT INTO users (username,password,email)
VALUES
    ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
    ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');


INSERT INTO users_roles (user_id, role_id)
VALUES
    (1, 1),
    (2, 2);













