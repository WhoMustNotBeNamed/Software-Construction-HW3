-- liquibase formatted sql
-- changeset aevsyukov_1@edu.hse.ru:1 logicalFilePath:02.000.00/order.sql
create table order_table
(
    id      uuid default gen_random_uuid()
        primary key,
    user_id         uuid not null,
    from_station_id uuid not null,
    to_station_id   uuid not null,
    status          int  not null,
    created         timestamp,
    foreign key (user_id) references user_table (id),
    foreign key (from_station_id) references station(id),
    foreign key (to_station_id) references station(id)
)
-- rollback delete order_table;
