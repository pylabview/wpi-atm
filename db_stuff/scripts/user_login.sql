-- Log in
select user_login_pin, users.user_login, roles.role_description
from users
join roles on users.id = roles.user_id
where users.id = 4;

