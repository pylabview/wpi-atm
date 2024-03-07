# ATM System by WPI



### About this project

For this task, you are going to implement an ATM system that allows users to manage
their accounts. There are two types of users: Customers and Administrators. Both are
presented with their own menus (after login). Customers can use the system to
withdraw cash, deposit cash, and get their current balance. For simplicity, assume a
customer can have only 1 account (so they can’t have a savings AND checking account
or a Joint account with a spouse, for example). Administrators can view, create,
delete, and update accounts of customers. All data should be stored in a MySQL
database and the program is a console application (not a web app).



<video src="./demo.mp4"></video>





### ATM System Use Case Diagrams

![image-20240306194704161](./user_case.png)

#### ATM ERD

![](./atm_eer.png)

#### Dump file

`db_stuff/atm.sql`

#### Screen Shoots - Admin Menu

- Failed login

  ![](./screen1.png)

- Log in as admin1, 12345

  ![image-20240306200549815](./screen2.png)

  

- Add new Account 

  ![image-20240306203613058](./screen1.png)

* Update, here we use the user id we just create 19 (not the account number)

  ![image-20240306203948046](./screen4png)



- Delete account, use again 19

![image-20240306204118418](./screen6.png)

- Exit Admin menu

![](./screen7.png)



- Search User Id 2

  ![image-20240306205341720](./screen13.png)



#### Customer Menu (User 2)

- Withdraw

- ![](./screen8.png)

- Deposit

  ![](./screen10.png)



- Get Balance

  ![image-20240306205016421](./screen11.png)

- Exit Customer Menu

  ![image-20240306205152457](./screen12.png)