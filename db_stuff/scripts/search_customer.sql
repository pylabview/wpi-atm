select users.holder, accounts.id as Account_Number, roles.role_description
from users
join accounts  on users.id = accounts.user_id
join roles on users.id = roles.user_id
where users.id = 2;