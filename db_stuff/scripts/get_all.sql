select users.id, users.holder, users.user_login, users.user_login_pin, roles.role_description, accounts.active, accounts.balance from users
join accounts on  users.id = accounts.user_id
join roles on users.id = roles.user_id;
