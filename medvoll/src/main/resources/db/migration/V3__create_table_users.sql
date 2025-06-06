create table person.usersapi(
   id bigint primary key,
   login varchar(100) not null unique,
   senha varchar(255) not null
);