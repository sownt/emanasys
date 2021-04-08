CREATE TABLE employee (
    id nvarchar(255),
    first_name nvarchar(255),
    last_name nvarchar(255),
    birthday date,
    gender varchar(5),
    add constraint pk_employee primary key (id)
)