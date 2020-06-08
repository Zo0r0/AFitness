create schema afitness_db;
CREATE TABLE afitness_db.memberships (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    membership_period VARCHAR(20) NOT NULL,
    membership_price VARCHAR(20) NOT NULL
);

CREATE TABLE afitness_db.clients (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    client_name CHAR(250) NOT NULL,
    client_surname CHAR(250) NOT NULL,
    client_phonenumber CHAR(20) NOT NULL,
    client_address CHAR(250),
    client_date_of_birth DATE NOT NULL,
    client_is_active CHAR(1) NOT NULL,
    client_membership_id INT NOT NULL,
    client_membership_expiration DATE,
    client_user_id INT
);

CREATE TABLE afitness_db.roles (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(250) NOT NULL,
    access_level CHAR(2)
);

CREATE TABLE afitness_db.users (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(25) NOT NULL,
    password VARCHAR(25) NOT NULL,
    role_id INT NOT NULL
);

alter table afitness_db.clients
add foreign key fk_clients(client_membership_id) references memberships(id);

alter table afitness_db.clients
add foreign key fk_users(client_user_id) references users(id);

alter table afitness_db.users
add foreign key fk_users(role_id) references roles(id);


insert into afitness_db.memberships (
membership_period, 
membership_price) values
('3 maanden', 'SRD 300'),
('6 maanden', 'SRD 600'),
('12 maanden', 'SRD 850');


insert into afitness_db.roles (
role_name,
access_level) values
("super", "W"),
("admin", "W"),
("system manager", "W"),
("receptionist", "R"),
("client", "R");

insert into afitness_db.users (
username,
password,
role_id) values
('administratie1', '@fitn3$$1', 3),
('super', '$0$3cur3', 1),
('zjadi', 'LeM0nHe@d', 5),
('tsultan', 'LuIgieIsDead', 5),
('msahit', 'superSonic', 5),
('ssewdien', 'GoosBumps', 5);

insert into afitness_db.clients (
client_name,
client_surname,
client_phonenumber,
client_address,
client_date_of_birth ,
client_is_active,
client_membership_id,
client_membership_expiration,
client_user_id) values
('Zoë','Jadi', '8684623', 'Granmankondrestraat 9', '1998-01-25', 'T', 3, '2020-12-01', 3),
('Taufiq','Sultan', '8755964', 'Keizerstraat 10', '1998-02-14', 'T', 2, '2020-08-07', 4),
('Matthew','Sahit', '845632', 'Leiding 9a 13', '2000-06-21', 'T', 1, '2020-07-03', 5),
('Shivan','Sewdien', '784266', 'Leiding 10 25', '1998-11-11', 'T', 1, '2020-03-03', 6);




