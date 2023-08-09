CREATE SEQUENCE public.hibernate_sequence;
create table revinfo (rev int4 not null, revtstmp int8, primary key (rev));
create table users_aud (id uuid not null, rev int4 not null, revtype int2, email varchar(255), name varchar(255), phone varchar(255), primary key (id, rev));
alter table users_aud add constraint FKc4vk4tui2la36415jpgm9leoq foreign key (rev) references revinfo;
