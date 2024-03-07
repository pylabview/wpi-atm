SELECT * FROM users;
SELECT * from roles;
Select * from accounts;

-- INSERT INTO users (user_type, holder, user_login_pin, user_login)
-- VALUES (0, 'Mark', '67896',  'mark89');
-- SET @one_id = LAST_INSERT_ID();
-- INSERT INTO roles (role_description,user_id)
-- VALUES('Customner',@one_id);
-- insert into accounts(user_id, balance, active)
-- values (@one_id, 500,1);

-- INSERT INTO users (user_type, holder, user_login_pin, user_login)
-- VALUES (0, 'Rodrigo Arguwello', '12345',  'rod86');
-- SET @one_id = LAST_INSERT_ID();
-- INSERT INTO roles (role_description,user_id)
-- VALUES('Customner',@one_id);
-- insert into accounts(user_id, balance, active)
-- values (@one_id, 10000.45,1);

-- INSERT INTO users (user_type, holder, user_login_pin, user_login)
-- VALUES (0, 'Carlos Martinez', '12345',  'carlo1997');
-- SET @one_id = LAST_INSERT_ID();
-- INSERT INTO roles (role_description,user_id)
-- VALUES('Customner',@one_id);
-- insert into accounts(user_id, balance, active)
-- values (@one_id, 700.45,1);

-- INSERT INTO users (user_type, holder, user_login_pin, user_login)
-- VALUES (0, 'John Smith', '12345',  'john86');
-- SET @one_id = LAST_INSERT_ID();
-- INSERT INTO roles (role_description,user_id)
-- VALUES('Customner',@one_id);
-- insert into accounts(user_id, balance, active)
-- values (@one_id, 987.45,1);

-- INSERT INTO users (user_type, holder, user_login_pin, user_login)
-- VALUES (0, 'Dimitri Flink', '12345',  'dimitri98');
-- SET @one_id = LAST_INSERT_ID();
-- INSERT INTO roles (role_description,user_id)
-- VALUES('Customner',@one_id);
-- insert into accounts(user_id, balance, active)
-- values (@one_id, 879.45,1);

-- Admin -------------------------
-- INSERT INTO users (user_type, holder, user_login_pin, user_login)
-- VALUES (1, 'Admin', '12345',  'admin1');
-- SET @one_id = LAST_INSERT_ID();
-- INSERT INTO roles (role_description,user_id)
-- VALUES('Admin',@one_id);
-- insert into accounts(user_id, balance, active)
-- values (@one_id, 10,1);