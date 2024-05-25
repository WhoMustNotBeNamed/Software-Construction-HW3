-- liquibase formatted sql
-- changeset aevsyukov_1@edu.hse.ru:1 logicalFilePath:01.000.00/order.sql
create table order
(
    id              uuid      default gen_random_uuid() primary key,
    user_id         uuid not null,
    from_station_id uuid not null,
    to_station_id   uuid not null,
    status          int  not null,
    created         timestamp default current_timestamp(),
    foreign key (user_id) references user (id),
    foreign key (from_station_id) references station (id),
    foreign key (to_station_id) references station (id)
)
-- rollback delete order;
