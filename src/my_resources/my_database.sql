create table if not exists role
(
    role_id bigint not null
        constraint user_roles_pk
            primary key,
    role_name varchar(50) not null
);

alter table role owner to postgres;

create unique index if not exists user_roles_role_uindex
    on role (role_name);

create unique index if not exists user_roles_role_id_uindex
    on role (role_id);

create table if not exists department
(
    id_depart serial not null
        constraint department_pk
            primary key,
    department_name varchar(50) not null
);

alter table department owner to postgres;

create table if not exists users
(
    id bigserial not null
        constraint users_pk
            primary key,
    name varchar(50) not null,
    surname varchar(70) not null,
    email varchar(100) not null,
    birth_day date,
    department_id integer not null
        constraint users_department_id_depart_fk
            references department,
    created timestamp not null,
    changed timestamp not null,
    is_deleted boolean default false not null
);

alter table users owner to postgres;

create unique index if not exists users_id_uindex
    on users (id);

create unique index if not exists users_email_uindex
    on users (email);

create index if not exists users_name_index
    on users (name);

create index if not exists users_name_surname_index
    on users (name, surname);

create index if not exists users_surname_index
    on users (surname);

create unique index if not exists department_id_depart_uindex
    on department (id_depart);

create table if not exists type_of_user_days
(
    id_type_of_day serial not null
        constraint type_of_user_days_pk
            primary key,
    name varchar(50) not null
);

alter table type_of_user_days owner to postgres;

create table if not exists user_worked_time
(
    id_time bigserial not null
        constraint user_worked_time_pk
            primary key
        constraint user_worked_time_users_id_fk
            references users,
    id_users bigint not null,
    start_time time,
    end_time time,
    day date not null,
    type_of_day integer
        constraint user_worked_time_type_of_user_days_id_type_of_day_fk
            references type_of_user_days
);

alter table user_worked_time owner to postgres;

create unique index if not exists user_worked_time_id_time_uindex
    on user_worked_time (id_time);

create unique index if not exists type_of_user_days_id_type_of_day_uindex
    on type_of_user_days (id_type_of_day);

create unique index if not exists type_of_user_days_name_uindex
    on type_of_user_days (name);

create table if not exists rate
(
    id_rate serial not null
        constraint rate_pk
            primary key,
    salary_rate numeric(3,2) not null,
    work_hour integer not null,
    work_hour_short_day integer
);

alter table rate owner to postgres;

create table if not exists user_work_schedule
(
    id bigserial not null
        constraint user_work_schedule_pk
            primary key,
    id_users bigint not null
        constraint user_work_schedule_users_id_fk
            references users,
    id_rate integer not null
        constraint user_work_schedule_rate_id_rate_fk
            references rate,
    work_day json not null
);

alter table user_work_schedule owner to postgres;

create unique index if not exists user_work_schedule_id_uindex
    on user_work_schedule (id);

create unique index if not exists rate_id_rate_uindex
    on rate (id_rate);

create unique index if not exists rate_salary_rate_uindex
    on rate (salary_rate);

create unique index if not exists rate_work_hour_uindex
    on rate (work_hour);

create table if not exists type_of_calendar_days
(
    id serial not null
        constraint type_of_calendar_days_pk
            primary key,
    type varchar(50)
);

alter table type_of_calendar_days owner to postgres;

create table if not exists "production calendar"
(
    id bigserial not null
        constraint "production calendar_pk"
            primary key,
    date date not null,
    id_type_of_prod_calendar_day integer not null
        constraint "production calendar_type_of_calendar_days_id_fk"
            references type_of_calendar_days,
    description varchar(50)
);

alter table "production calendar" owner to postgres;

create unique index if not exists "production calendar_id_uindex"
    on "production calendar" (id);

create unique index if not exists type_of_calendar_days_id_uindex
    on type_of_calendar_days (id);

create table if not exists users_role
(
    id bigserial not null
        constraint users_role_pk
            primary key,
    id_user bigint not null
        constraint users_role_users_id_fk
            references users,
    id_role integer not null
        constraint users_role_role_role_id_fk
            references role
);

alter table users_role owner to postgres;

create unique index if not exists users_role_id_uindex
    on users_role (id);

create table if not exists credential
(
    id bigserial not null
        constraint credential_pk
            primary key,
    id_users bigint not null
        constraint credential_users_id_fk
            references users,
    login varchar(50) not null,
    password text not null
);

alter table credential owner to postgres;

create unique index if not exists credential_id_uindex
    on credential (id);

create unique index if not exists credential_id_users_uindex
    on credential (id_users);

create unique index if not exists credential_login_uindex
    on credential (login);

