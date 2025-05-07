DROP DATABASE IF EXISTS YelmoCines;
CREATE DATABASE YelmoCines;
USE YelmoCines;

CREATE TABLE pelicula(
id INT PRIMARY KEY,
nombre_pelicula VARCHAR(50) NOT NULL,
descripcion TEXT,
particularidad ENUM('Castellano', 'Ingles')
);

CREATE TABLE cine(
CIF VARCHAR(9) PRIMARY KEY ,
nombre VARCHAR(50) NOT NULL,
direccion TEXT
);

CREATE TABLE sesion(
id_sesion INT PRIMARY KEY AUTO_INCREMENT,
hora TIME,
dia DATE,
aforo INT UNSIGNED,
CIF VARCHAR(9),
id_pelicula INT,
FOREIGN KEY (CIF) REFERENCES Cine(CIF),
FOREIGN KEY (id_pelicula) REFERENCES pelicula(id)
);

CREATE TABLE persona(
DNI VARCHAR(9) PRIMARY KEY,
nombre VARCHAR(50) NOT NULL,
apellido VARCHAR(50),
rol VARCHAR(50),
email VARCHAR(50) UNIQUE,
telefono VARCHAR(10),
contrasena VARCHAR (10)
);

CREATE TABLE sesion_reservada(
id_reserva INT AUTO_INCREMENT PRIMARY KEY,
id_sesion INT,
DNI VARCHAR(9),
FOREIGN KEY (DNI) REFERENCES persona(DNI),
FOREIGN KEY (id_sesion) REFERENCES sesion(id_sesion)
);

CREATE TABLE sugerencia(
id INT PRIMARY KEY AUTO_INCREMENT,
fecha DATE,
descripcion TEXT,
DNI VARCHAR(9),
FOREIGN KEY (DNI) REFERENCES Persona(DNI)
);

---Tablas no conectadas
CREATE TABLE datos_borrados (
id INT PRIMARY KEY AUTO_INCREMENT,
fecha DATETIME,
tabla_afectada VARCHAR(100),
datos_anteriores TEXT
);

CREATE TABLE datos_actualizados (
id INT PRIMARY KEY AUTO_INCREMENT,
fecha DATETIME,
tabla_afectada VARCHAR(100),
datos_anteriores TEXT
);

--Insertar datos a película
INSERT INTO pelicula (id,nombre_pelicula,descripcion, particularidad) VALUES 
(1, 'La lista de Schindler','Historia de un empresario que salvó a judíos durante la Segunda Guerra Mundial', 'Castellano'),
(2, 'Ciudad de Dios','Relato de la vida en las favelas de Brasil', 'Castellano'),
(3, 'One of our own','The story of a group of mobsters in New York', 'Ingles'),
(4, 'Dersu Uzala','La vida de un explorador ruso en el siglo XX', 'Castellano'),
(5, 'Catch me if you can','The story of a young man who becomes an identity thief', 'Inglés'),
(6, 'Dangerous Minds','The story of a group of students fighting for their rights at a school', 'Ingles'),
(7, 'El ladrón de bancos','Relato de la vida de un hombre que se convierte en un famoso ladrón de bancos', 'Castellano'),
(8, 'El rescatador','La vida de un hombre que se convierte en un héroe tras salvar a su familia de un ataque', 'Castellano'),
(9, 'Born to kill','The story of a group of soldiers in the Vietnam War', 'Ingles'),
(10, 'El artista del desastre','La vida de un hombre que se convierte en un famoso artista a pesar de sus dificultades', 'Castellano');

--Insertar datos a cine
INSERT INTO cine (CIF, nombre, direccion) VALUES 
('A12345678', 'Cine Callao', 'Calle Gran Vía, 32, Madrid'),
('B98765432', 'Cinesa La Vaguada', 'Avenida de la Ciudad de Barcelona, 1, Madrid'),
('C11223344', 'Cine de la Filmoteca Española', 'Calle de Vallehermoso, 79, Madrid'),
('D55667788', 'Cine Yelmo - Plaza de España', 'Calle de José Abascal, 56, Madrid'),
('E99001122', 'Cine Capitol', 'Calle de la Princesa, 3, Madrid'),
('F33445566', 'Cine Doré', 'Calle de Vallehermoso, 79, Madrid'),
('G77889900', 'Cine Ideal', 'Calle de la Oca, 1, Madrid'),
('H22110033', 'Cine Aribau', 'Calle de la Estación, 5, Barcelona'),
('I44556677', 'Cinesa Diagonal', 'Calle de la Marina, 19, Barcelona'),
('J88990011', 'Cine Coliseum','Calle de la Rambla, 34, Barcelona');

--Insertar datos a sesion
INSERT INTO sesion (hora, dia, aforo, CIF, id_pelicula) VALUES
('19:00', '2025-01-08', 100, 'A12345678', 1),
('21:30', '2025-03-08', 150, 'B98765432', 2),
('17:00', '2025-01-09', 80, 'A12345678', 3),
('20:00', '2025-03-09', 120, 'C11223344', 1),
('19:30', '2025-03-10', 75, 'B98765432', 4),
('22:00', '2024-03-10', 100, 'A12345678', 2),
('18:00', '2025-03-13', 90, 'C11223344', 5),
('21:00', '2025-02-11', 130, 'B98765432', 3),
('18:00', '2025-02-11', 90, 'C11223344', 5),
('21:00', '2025-01-12', 130, 'B98765432', 3);

--Insertar datos a persona
INSERT INTO persona (DNI, nombre, apellido, rol, email, telefono, contrasena) VALUES 
('12345678A', 'Javier', 'Milei', 'Administrador', 'javiermilei@gmail.com', '999999999', '1JavierMilei'),
('87654321B', 'Albert', 'Einstein', 'Empleado', 'alberteinstein@gmail.com', '888888888', '2AlbertEinstein'),
('98765432C', 'Malala', 'Yousafzai', 'Empleado', 'malalayousafzai@gmail.com', '777777777', '3MalalaYousafzai'),
('45678901D', 'Bill', 'Gates', 'Empleado', 'billgates@gmail.com', '666666666', '4BillGates'),
('34567890E', 'Michael', 'Jackson', 'Empleado', 'michaeljackson@gmail.com', '555555555', '5MichaelJackson'),
('23456789F', 'Steven', 'Spielberg', 'Empleado', 'stevenspielberg@gmail.com', '444444444', '6StevenSpielberg'),
('78901234G', 'Emma', 'Watson', 'Empleado', 'emmawatson@gmail.com', '333333333', '7EmmaWatson'),
('10111213H', 'Tom', 'Hanks', 'Empleado', 'tomhanks@gmail.com', '222222222', '8TomHanks'),
('11121314I', 'Lady', 'Gaga', 'Empleado', 'ladygaga@gmail.com', '111111111', '9LadyGaga'),
('12131415J', 'Stephen', 'King','Empleado', 'stephenking@gmail.com', '000000000', '10StephenKing');

--Insertar datos a sesion_reservada
INSERT INTO sesion_reservada (id_sesion, DNI) VALUES 
(1, '12345678A'),
(2, '87654321B'),
(3, '98765432C'),
(4, '45678901D'),
(5, '34567890E'),
(6, '23456789F'),
(7, '78901234G'),
(8, '10111213H'),
(9, '11121314I'),
(10, '12131415J');

--Insertar datos a sugerencia
INSERT INTO sugerencia (id, fecha, descripcion, DNI) VALUES 
(1, '2024-01-01', 'Mejorar la calidad del sonido', '12345678A'),
(2, '2024-02-05', 'Más variedad de películas', '87654321B'),
(3, '2024-03-10', 'Aumentar el aforo', '98765432C'),
(4, '2024-04-15', 'Sistema de reservas online', '78901234G'),
(5, '2024-04-20', 'Más opciones de comida', '23456789F'),
(6, '2024-05-25', 'Butacas más cómodas','45678901D'),
(7, '2024-06-30', 'Mejorar la iluminación', '10111213H'),
(8, '2024-07-01', 'Precios más accesibles', '11121314I'),
(9, '2024-08-05', 'Más cartelera infantil', '34567890E'),
(10,'2024-09-10', 'Mas cartelera infantil', '98765432C');


--hola--
--ADIOS--