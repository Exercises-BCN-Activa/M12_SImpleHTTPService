
DROP table IF EXISTS employee;

create table employee(
	id int auto_increment,
	name varchar(250),
	job varchar(250),
	salary decimal(10,2)
);

insert into employee (name,job,salary)values('Jose Marin', 'Master', 3500);
insert into employee (name,job,salary)values('Pedro Guillem','Error', 0);
insert into employee (name,job,salary)values('Juan Lopez','Full', 1700);
insert into employee (name,job,salary)values('Jordi Martin','Junior', 1000);
insert into employee (name,job,salary)values('Jonatan Vicente','Senior', 2500);