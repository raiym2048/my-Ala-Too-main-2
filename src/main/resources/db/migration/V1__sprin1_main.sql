
create table if not exists language(
    code char(2) primary key ,-- primary key
    title_kg varchar(50)  not null , -- not unique
    title_ru varchar(50)  not null ,
    title_en varchar(50)  not null
    );

create table if not exists role(
    code varchar(10) primary key ,-- primary key
    title_kg varchar(50)  not null ,
    title_ru varchar(50)  not null ,
    title_en varchar(50)  not null
    );
create table if not exists marital_status( -- marital_status
                                             id serial primary key,
                                             title_kg varchar(50)  not null ,
    title_ru varchar(50)  not null ,
    title_en varchar(50)  not null
    );
create table if not exists military(
                                       id serial primary key,
                                       title_kg varchar(150)  not null , -- 150 chars
    title_ru varchar(150)  not null ,
    title_en varchar(150)  not null
    );
create table if not exists nationality(
                                          id serial primary key,
                                          title_kg varchar(50)  not null ,
    title_ru varchar(50)  not null ,
    title_en varchar(50)  not null
    );
create table if not exists address_type(
                                           id serial primary key,
                                           title_kg varchar(50)  not null ,
    title_ru varchar(50)  not null ,
    title_en varchar(50)  not null
    );
create table if not exists address(
                                      id serial primary key,
                                      p_id int references address(id),
    address_type_id int references address_type(id),
    title_kg varchar(50)  not null ,
    title_ru varchar(50)  not null ,
    title_en varchar(50)  not null
    );
create table if not exists gender(
                                     id serial primary key ,
                                     title_kg varchar(25)  not null , -- size umenwit
    title_ru varchar(25)  not null ,
    title_en varchar(25)  not null
    );
create table if not exists web_log(
                                      id serial primary key,
                                      log_ip varchar(25) not null , -- 25
    log_date_time timestamp not null , -- datetime
    device_info varchar(200)
    );
create table if not exists users(
                                    id serial primary key,
                                    email varchar(50) unique not null ,
    password varchar(200) not null,
    reg_date timestamp not null , -- datetime
    is_active boolean not null , -- is_active
    attempt_count int,
    attempt_date date,
    web_lan_code char(2) references language(code),
    last_login_info_id int references web_log(id)
    );
create table if not exists applicant(
                                        id serial primary key,
                                        user_id int references users(id),
    name varchar(50) not null,
    surname varchar(50) not null,
    patronymic varchar(50) not null,
    name_native varchar(50) not null,
    surname_native varchar(50) not null
    );
create table if not exists stud_info(
                                        id serial primary key,
                                        applicant_id int references applicant(id),
    birth_date date not null ,
    citizenship_id int references address(id) not null , -- citizenship_id
    nationality_id int references nationality(id) not null ,
    gender_id int references gender(id),
    marital_status_id int references marital_status(id), -- marital_status_id
    iin varchar(25) unique not null , -- 25
    military_id int references military(id)
    );

create table if not exists user_roles (user_id int references users(id),
    role_code varchar(10) references role(code)
    );


