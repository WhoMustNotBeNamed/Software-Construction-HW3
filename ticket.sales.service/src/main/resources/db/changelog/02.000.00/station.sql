-- liquibase formatted sql
-- changeset aevsyukov_1@edu.hse.ru:1 logicalFilePath:02.000.00/station.sql
create table station
(
    id      uuid default gen_random_uuid()
        primary key,
    station varchar(50) not null
)
    -- rollback delete station;

-- changeset aevsyukov_1@edu.hse.ru:2 logicalFilePath:02.000.00/station.sql
insert Into station (id, station) values (gen_random_uuid(), 'Moscow');
insert Into station (id, station) values (gen_random_uuid(), 'Saint-Petersburg');
insert Into station (id, station) values (gen_random_uuid(), 'Novosibirsk');
insert Into station (id, station) values (gen_random_uuid(), 'Yekaterinburg');
insert Into station (id, station) values (gen_random_uuid(), 'Kazan');
insert Into station (id, station) values (gen_random_uuid(), 'Nizhny Novgorod');
insert Into station (id, station) values (gen_random_uuid(), 'Ufa');
insert Into station (id, station) values (gen_random_uuid(), 'Volgograd');
insert Into station (id, station) values (gen_random_uuid(), 'Perm');
insert Into station (id, station) values (gen_random_uuid(), 'Krasnoyarsk');
insert Into station (id, station) values (gen_random_uuid(), 'Voronezh');
-- rollback delete from station where station in ('Moscow', 'Saint Petersburg', 'Novosibirsk', 'Yekaterinburg', 'Kazan', 'Nizhny Novgorod', 'Ufa', 'Volgograd', 'Perm', 'Krasnoyarsk', 'Voronezh');
