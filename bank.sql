drop table if exists expenses;
drop table if exists users;

create table users (
	username varchar(100),
    name varchar(100),
    password varchar(100),
    total_balance double,
    primary key (username)
);

create table expenses (
	username varchar(100),
	expense_id char(5),
	planned boolean,
    amount double,
    information varchar(5000),
    due_date date,
	foreign key (username) references users(username),
    primary key (expense_id)
);

insert into users values ('nickw3', 'Nicholas Whorton', 'password', 1000);
insert into users values ('bretts4', 'Brett Smith', 'password', 2000);
insert into users values ('carterp5', 'Carter Putnam', 'password', 3000);
insert into users values ('rileyl6', 'Riley Lowe', 'password', 4000);
insert into users values ('joshb7', 'Josh Brown', 'password', 5000);

insert into expenses values ('nickw3', '12345', true, 400, 'rent', '2023-02-01');
insert into expenses values ('bretts4', '12346', true, 15, 'netflix', '2023-02-05');
insert into expenses values ('carterp5', '12347', true, 100, 'groceries', '2023-02-12');
insert into expenses values ('rileyl6', '12348', true, 200, 'car payment', '2023-02-09');
insert into expenses values ('joshb7', '12349', true, 5, 'spotify', '2023-02-01');


