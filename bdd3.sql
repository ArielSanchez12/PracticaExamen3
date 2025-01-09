create database ExamenTres;
use ExamenTres;


create table Usuarios(
id int auto_increment primary key,
usuario varchar(30),
contrasenia varchar(30)
);

insert into Usuarios(usuario,contrasenia) 
values ('ariel', 'passw123');

select * from Usuarios;