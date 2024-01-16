create table if not exists insurance_product
(
    id              varchar(36)    not null primary key unique,
    name            varchar(120)   not null,
    category        varchar(40)    not null,
    priceBase       decimal(13, 2) not null,
    priceWithTariff decimal(13, 2) not null
);
