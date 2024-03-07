set @now = CURRENT_TIMESTAMP(); 
select accounts.balance, accounts.id as accout_number, users.holder, @now as time_stamp
from users
join accounts on accounts.user_id = users.id
where users.id =2;