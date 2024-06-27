-- liquibase formatted sql
-- changeset aevsyukov_1@edu.hse.ru:1 logicalFilePath:01.000.00/user.sql
create table user_table
(
    id       uuid default gen_random_uuid()
        primary key,
    username varchar(50)         not null,
    email    varchar(100) unique not null,
    password varchar(255)        not null,
    created  timestamp
)
-- rollback delete user_table;
