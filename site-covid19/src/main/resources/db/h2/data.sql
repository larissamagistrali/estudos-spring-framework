INSERT INTO patients VALUES(1, 'Cillian Murphy','1980-09-01',NULL,'25631452198','(51)99925-6899');
INSERT INTO patients VALUES(2, 'Paul Anderson','1970-08-02',NULL,'25896321454','(51)99325-8888');
INSERT INTO patients VALUES(3, 'Sophie Rundle','1975-07-03','2020-07-07','12312541299','(51)99251-2244');
INSERT INTO patients VALUES(4, 'Helen McCrory','1976-06-07','2020-07-07','89896980855','(51)99858-4475');
INSERT INTO patients VALUES(5, 'Finn Cole','1977-08-05',NULL,'96363022236','(51)98252-2222');
INSERT INTO patients VALUES(6, 'Tom Hardy','1978-04-06',NULL,'25230652201','(51)99898-5478');
INSERT INTO patients VALUES(7, 'Louis Thomas','1960-08-10',NULL,'70058745512','(51)99363-6655');
INSERT INTO patients VALUES(8, 'Joe Cole','1962-08-07',NULL,'59809623300','(51)99985-1122');
INSERT INTO patients VALUES(9, 'Natasha OKeeffe','1990-08-11',NULL,'80784548457','(51)99235-6699');
INSERT INTO patients VALUES(10, 'Annabelle Wallis','1991-08-12',NULL,'02102523633','(51)99935-5111');


INSERT INTO functionaries VALUES(1,'Packy Lee',			'1975-09-02','1170190210','(51)99689-9689','Médico');
INSERT INTO functionaries VALUES(2,'Brian Gleeson',		'1965-08-02','8454790936','(51)99829-9829','Médico');
INSERT INTO functionaries VALUES(3,'Charlotte Riley',	'1988-08-02','7474441085','(51)96600-6600','Médica');
INSERT INTO functionaries VALUES(4,'Emmett J. Scanlan',	'1987-07-02','3700795143','(51)99545-9545','Enfermeira');
INSERT INTO functionaries VALUES(5,'Aimee-Ffion Edwards','1986-06-02','1024458116','(51)97516-7516','Enfermeira');
INSERT INTO functionaries VALUES(6,'Adrien Brody',		'1985-05-02','9808073461','(51)93265-3265','Enfermeira');
INSERT INTO functionaries VALUES(7,'Charlie Murphy',	'1984-04-02','4429780141','(51)97806-7806','Recepcionista');
INSERT INTO functionaries VALUES(8,'Jack Rowan',		'1991-03-02','8522542807','(51)91037-1037','Administrador da unidade básica de saúde');
INSERT INTO functionaries VALUES(9,'Daryl McCormack',	'1992-02-02','8896133535','(51)97005-7005','Administrador da unidade básica de saúde');
INSERT INTO functionaries VALUES(10,'Kate Dickie',		'1980-01-02','7730236151','(51)93028-3028','Administrador da unidade básica de saúde');


INSERT INTO basic_units VALUES(1,'Unidade de Saúde Santa Cecília','(51)3359-8685','90620110',' R. São Manoel',543,'Porto Alegre','RS','Brasil','07:00','18:00');
INSERT INTO basic_units VALUES(2,'Unidade de Saúde Vila Cruzeiro','(51) 3266-5065','90810430',' Av. Capivari',2020,'Porto Alegre','RS','Brasil','07:00','18:00');
INSERT INTO basic_units VALUES(3,'Unidade de Saúde Cristal','(51)3266-6132','90840000','R. Cruzeiro do Sul',2702,'Porto Alegre','RS','Brasil','07:00','18:00');
INSERT INTO basic_units VALUES(4,'Unidade de Saúde Divisa','(51)3289-5573','90820140','R. Upamaroti',735,'Porto Alegre','RS','Brasil','07:00','18:00');
INSERT INTO basic_units VALUES(5,'Unidade de Saúde Tristeza','(51)3289-5764','91900000',' R. Av. Wenceslau Escobar',2442,'Porto Alegre','RS','Brasil','08:00','18:00');
INSERT INTO basic_units VALUES(6,'Unidade de Saúde Jardim das Palmeiras','(51)3241-2140','91740310','R. Ângelo Barbosa',38,'Porto Alegre','RS','Brasil','08:00','18:00');
INSERT INTO basic_units VALUES(7,'Unidade de Saúde Modelo','(51)3289-2555','90040340','Av. Jerônimo de Ornelas',55,'Porto Alegre','RS','Brasil','08:00','18:00');
INSERT INTO basic_units VALUES(8,'Unidade de Saúde Moab Caldas','(51)3289-4070','90880310','Av. Moab Caldas',400,'Porto Alegre','RS','Brasil','06:00','18:00');
INSERT INTO basic_units VALUES(9,'Unidade de Saúde Santa Anita','(51)3289-5556','90830260','R. Gregório da Fonseca',91,'Porto Alegre','RS','Brasil','06:00','18:00');
INSERT INTO basic_units VALUES(10,'Unidade de Saúde Santa Tereza','(51)3232-9773','90830580','R. Dona Otília',5,'Porto Alegre','RS','Brasil','06:00','18:00');


INSERT INTO medical_appointments VALUES(1,1,1,1,'2020-07-02','10:00:00','10:20:00','Necessário teste para COVID-19',FALSE);
INSERT INTO medical_appointments VALUES(2,1,1,1,'2020-07-10','10:00:00','10:21:00','Necessário teste para COVID-19',FALSE);
INSERT INTO medical_appointments VALUES(3,1,1,2,'2020-07-20','10:00:00','10:22:00','Necessário teste para COVID-19',TRUE);

INSERT INTO medical_appointments VALUES(4,2,2,3,'2020-05-05','10:00:00','10:26:00','Necessário teste para COVID-19',FALSE);
INSERT INTO medical_appointments VALUES(5,2,2,3,'2020-06-15','10:00:00','10:25:00','Necessário teste para COVID-19',TRUE);
INSERT INTO medical_appointments VALUES(6,2,2,4,'2020-05-18','10:00:00','10:28:00','Necessário teste para COVID-19',TRUE);

INSERT INTO medical_appointments VALUES(7,3,3,5,'2020-05-16','10:00:00','10:27:00','Necessário teste para COVID-19',FALSE);
INSERT INTO medical_appointments VALUES(8,3,3,5,'2020-06-12','10:00:00','10:24:00','Necessário teste para COVID-19',FALSE);
INSERT INTO medical_appointments VALUES(9,3,3,6,'2020-07-01','10:00:00','10:28:00','Necessário teste para COVID-19',TRUE);

INSERT INTO medical_appointments VALUES(10,4,1,7,'2020-05-12','11:00:00','11:10:00','Necessário teste para COVID-19',FALSE);
INSERT INTO medical_appointments VALUES(11,4,1,7,'2020-06-15','11:00:00','11:15:00','Necessário teste para COVID-19',FALSE);
INSERT INTO medical_appointments VALUES(12,4,1,8,'2020-07-02','11:00:00','11:20:00','Necessário teste para COVID-19',TRUE);

INSERT INTO medical_appointments VALUES(13,5,2,9,'2020-05-14','11:00:00','11:16:00','Necessário teste para COVID-19',FALSE);
INSERT INTO medical_appointments VALUES(14,5,2,9,'2020-06-26','11:00:00','11:15:00','Necessário teste para COVID-19',FALSE);
INSERT INTO medical_appointments VALUES(15,5,2,10,'2020-07-25','11:00:00','11:18:00','Necessário teste para COVID-19',TRUE);

INSERT INTO medical_appointments VALUES(16,6,3,1,'2020-05-26','11:00:00','11:26:00','Necessário teste para COVID-19',FALSE);
INSERT INTO medical_appointments VALUES(17,6,3,1,'2020-06-28','11:00:00','11:17:00','Necessário teste para COVID-19',TRUE);
INSERT INTO medical_appointments VALUES(18,6,3,2,'2020-07-14','11:00:00','11:19:00','Necessário teste para COVID-19',TRUE);

INSERT INTO medical_appointments VALUES(19,7,1,3,'2020-05-03','14:00:00','14:25:00','Necessário teste para COVID-19',FALSE);
INSERT INTO medical_appointments VALUES(20,7,1,4,'2020-06-05','14:00:00','14:14:00','Necessário teste para COVID-19',TRUE);
INSERT INTO medical_appointments VALUES(21,7,1,5,'2020-07-17','14:00:00','14:23:00','Necessário teste para COVID-19',TRUE);

INSERT INTO medical_appointments VALUES(22,8,2,6,'2020-05-16','14:00:00','14:22:00','Necessário teste para COVID-19',FALSE);
INSERT INTO medical_appointments VALUES(23,8,2,6,'2020-05-03','14:00:00','14:26:00','Necessário teste para COVID-19',TRUE);
INSERT INTO medical_appointments VALUES(24,8,2,7,'2020-06-19','14:00:00','14:23:00','Necessário teste para COVID-19',TRUE);

INSERT INTO medical_appointments VALUES(25,9,3,8,'2020-05-16','14:00:00','14:12:00','Necessário teste para COVID-19',FALSE);
INSERT INTO medical_appointments VALUES(26,9,3,9,'2020-06-03','14:00:00','14:16:00','Necessário teste para COVID-19',FALSE);

INSERT INTO medical_appointments VALUES(27,10,1,10,'2020-07-03','14:00:00','14:18:00','Necessário teste para COVID-19',TRUE);

--INSERT INTO users VALUES(1,'ADMIN',NULL,'admin','admin');
--INSERT INTO users VALUES(2,'USER',1,'santacecilia','santacecilia');
--INSERT INTO users VALUES(3,'USER',2,'vilacruzeiro','vilacruzeiro');
--INSERT INTO users VALUES(4,'USER',3,'cristal','cristal');
--INSERT INTO users VALUES(5,'USER',4,'divisa','divisa');
--INSERT INTO users VALUES(6,'USER',5,'tristeza','tristeza');
--INSERT INTO users VALUES(7,'USER',6,'jardimdaspalmeiras','jardimdaspalmeiras');
--INSERT INTO users VALUES(8,'USER',7,'modelo','modelo');
--INSERT INTO users VALUES(9,'USER',8,'moabcaldas','moabcaldas');
--INSERT INTO users VALUES(10,'USER',9,'santaanita','santaanita');
--INSERT INTO users VALUES(11,'USER',10,'santatereza','santatereza');

