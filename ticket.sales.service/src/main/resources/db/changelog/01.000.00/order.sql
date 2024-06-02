-- liquibase formatted sql
-- changeset aevsyukov_1@edu.hse.ru:1 logicalFilePath:01.000.00/order.sql
create table order_table
(
    id              uuid default gen_random_uuid()
        primary key,
    user_id         uuid not null,
    from_station_id uuid not null,
    to_station_id   uuid not null,
    status          int  not null,
    created         timestamp
--     user_id         uuid references user (id),
--     from_station_id uuid references station (id),
--     to_station_id   uuid references station (id)
)
-- rollback delete order;
