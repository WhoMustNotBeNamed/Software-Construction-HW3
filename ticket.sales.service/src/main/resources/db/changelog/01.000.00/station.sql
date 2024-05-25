-- liquibase formatted sql
-- changeset aevsyukov_1@edu.hse.ru:1 logicalFilePath:01.000.00/station.sql
create table station
(
    id      uuid default gen_random_uuid() primary key,
    station varchar(50) not null
)
-- rollback delete station;