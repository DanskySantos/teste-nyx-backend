create table tb_user
(
    id                  bigint generated by default as identity,
    creation_date       timestamp,
    update_date         timestamp,
    uuid                varchar(255),
    username            varchar(255),
    password            varchar(255),
    email               varchar(255),
    person_id           bigint,
    role                varchar(255),
    excluded            boolean default false,
    primary key (id)
);

create table tb_person
(
    id                  bigint generated by default as identity,
    creation_date       timestamp,
    update_date         timestamp,
    uuid                varchar(255),
    name                varchar(255),
    birthday            varchar(255),
    user_id             bigint,
    primary key (id)
);

alter table tb_person
    add constraint UK_86cal5mg9j0t8j9yn28n9iulc unique (uuid);
alter table tb_user
    add constraint UK_86cal5mg9j0t8j9yn28n9ijjj unique (uuid);
alter table tb_user
    add constraint FK349pmtlc4ukgn1so04k2ls4jj foreign key (person_id) references tb_person;
