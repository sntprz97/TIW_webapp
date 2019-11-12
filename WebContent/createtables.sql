CREATE DATABASE IF NOT EXISTS `usersdb`;
USE `usersdb`;

DROP TABLE IF EXISTS `VENDEDORES`;
DROP TABLE IF EXISTS `PEDIDOS`;
DROP TABLE IF EXISTS `ID_STOCKPRODUCTOS`;
DROP TABLE IF EXISTS `PRODUCTOS`;
DROP TABLE IF EXISTS `NOMBRE_MARCA_TALLAPRODUCTOS`;
DROP TABLE IF EXISTS `USUARIO`;

CREATE TABLE USUARIO(
idUsuario int (10),
nombre VARCHAR (255) NOT NULL,
apellido1 VARCHAR (255) NOT NULL,
username VARCHAR (255) NOT NULL,
email VARCHAR (255) NOT NULL UNIQUE,
contrasena VARCHAR (255) NOT NULL,
direccion VARCHAR (255) NOT NULL,

CONSTRAINT PK_USUARIO PRIMARY KEY (username)
);

CREATE TABLE NOMBRE_MARCA_TALLAPRODUCTOS(
nombre VARCHAR (255) NOT NULL,
marca VARCHAR (255) NOT NULL,
talla VARCHAR (255) NOT NULL,

CONSTRAINT PK_NMT PRIMARY KEY (nombre, marca, talla)
);

CREATE TABLE PRODUCTOS(
idProducto int (10) auto_increment,
nombreProducto VARCHAR (255) NOT NULL,
marca VARCHAR (255) NOT NULL,
talla VARCHAR (255) NOT NULL,
descripcionbreve VARCHAR (255) NOT NULL,
precio VARCHAR (255) NOT NULL,
cantidad int (10) NOT NULL,
idUsuario VARCHAR (255) NOT NULL,
imagen BLOB(1000000000) NOT NULL,

CONSTRAINT PK_PRODUCTOS PRIMARY KEY (idProducto),
CONSTRAINT FK_PRODUCTOS FOREIGN KEY (idUsuario) REFERENCES USUARIO (username) ON DELETE CASCADE,
CONSTRAINT FK_NMT FOREIGN KEY (nombreProducto, marca, talla) REFERENCES NOMBRE_MARCA_TALLAPRODUCTOS (nombre, marca, talla) ON UPDATE NO ACTION
);

CREATE TABLE PEDIDOS(
idPedido int (10) auto_increment,
idUsuario VARCHAR (255) NOT NULL,
idProducto int (10) NOT NULL,
estado enum ('Realizado', 'En proceso') NOT NULL,

CONSTRAINT PK_PEDIDOS PRIMARY KEY (idPedido),
CONSTRAINT FK_PEDIDOS FOREIGN KEY (idUsuario) REFERENCES USUARIO (username) ON DELETE CASCADE,
CONSTRAINT FK_PEDIDOS2 FOREIGN KEY (idProducto) REFERENCES PRODUCTOS (idProducto) ON DELETE CASCADE
);

CREATE TABLE ID_STOCKPRODUCTOS(
idProducto int (10) auto_increment,
stock int (50),

CONSTRAINT PK_IDSTOCK PRIMARY KEY (idProducto),
CONSTRAINT FK_IDSTOCK FOREIGN KEY (idProducto) REFERENCES PRODUCTOS (idProducto)
);

CREATE TABLE VENDEDORES(
idUsuario VARCHAR (255) NOT NULL,
username VARCHAR (255) NOT NULL,
nombreProducto VARCHAR (255) NOT NULL,
marca VARCHAR (255) NOT NULL,
talla VARCHAR (255) NOT NULL,

CONSTRAINT PK_VENDEDORES PRIMARY KEY (nombreProducto, marca, talla),
CONSTRAINT FK_VENDEDORES FOREIGN KEY (nombreProducto, marca, talla) REFERENCES nombre_marca_tallaproductos (nombre, marca, talla) ON DELETE CASCADE,
CONSTRAINT FK_VENDEDORES2 FOREIGN KEY (username) REFERENCES USUARIO (username) ON DELETE CASCADE
);