-- select accounts.balance from accounts
-- where accounts.user_id = 2;

-- select roles.role_description
-- from roles
-- right join users on users.id = roles.uer_id;

select roles.role_description, users.id
from users
join roles on users.id = roles.user_id
where users.user_login = 'admin1' and users.user_login_pin = '12345';