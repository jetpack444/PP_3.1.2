-- Вставка данных в таблицу roles
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

-- Вставка данных в таблицу users
INSERT INTO users (username, password, name, surname, age)
        VALUES ('admin@mail.com',
                '$2a$12$SD.dJAARIsR3BQgEoUHl8eTURKvIzB.QeCD6.k.piDxO.P3H.Zz0C',
                'admin',
                'admin',
                35
                );
INSERT INTO users (username, password, name, surname, age)
        VALUES ('alex@mail.com',
                '$2a$12$SD.dJAARIsR3BQgEoUHl8eTURKvIzB.QeCD6.k.piDxO.P3H.Zz0C',
                'Alex',
                'John',
                '50'
                );
INSERT INTO users (username, password, name, surname, age)
        VALUES ('asidorov@mail.com',
                '$2a$12$SD.dJAARIsR3BQgEoUHl8eTURKvIzB.QeCD6.k.piDxO.P3H.Zz0C',
                'Алексей',
                'Сидоров',
                '50'
                );

-- Вставка данных в таблицу user_roles
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 2);
