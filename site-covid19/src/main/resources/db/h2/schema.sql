DROP TABLE patients IF EXISTS;
DROP TABLE basic_units IF EXISTS;
DROP TABLE functionaries IF EXISTS;
DROP TABLE medical_appointments IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE roles IF EXISTS;

CREATE TABLE patients(
	id INTEGER IDENTITY PRIMARY KEY,
	name VARCHAR(30) NOT NULL,
	date_of_birth DATE NOT NULL,
	date_of_death DATE,
	cpf VARCHAR(20) not null,
	telephone VARCHAR(20) not null
);

CREATE TABLE basic_units(
	id INTEGER IDENTITY PRIMARY KEY,
	name VARCHAR(60) NOT NULL,
	telephone  VARCHAR(20) not null,
	zip_code INTEGER not null,
	street VARCHAR(30) not null,
	number INTEGER not null,
	city VARCHAR(30) not null,
	state VARCHAR(30) not null,
	country VARCHAR(30) not null,
	opening_time TIME ,
	closing_time TIME
);

CREATE TABLE functionaries(
	id INTEGER IDENTITY PRIMARY KEY,
	name VARCHAR(30) NOT NULL,
	date_of_birth DATE NOT NULL,
	cpf VARCHAR(20) not null,
	telephone VARCHAR(20) not null,	
	function VARCHAR(60) NOT NULL
);

CREATE TABLE medical_appointments(
	id INTEGER IDENTITY PRIMARY KEY,
	id_patient INTEGER NOT NULL,
	id_functionary INTEGER NOT NULL,
	id_basic_unit INTEGER NOT NULL,
	date_medical_appointment DATE not null,
	start_time TIME not null,
	end_time TIME not null,
	description VARCHAR(200),
	test_result BOOLEAN not null
);
ALTER TABLE medical_appointments ADD CONSTRAINT fk_asic_unit FOREIGN KEY (id_basic_unit) REFERENCES basic_units (id);
ALTER TABLE medical_appointments ADD CONSTRAINT fk_patient FOREIGN KEY (id_patient) REFERENCES patients (id);
ALTER TABLE medical_appointments ADD CONSTRAINT fk_functionary FOREIGN KEY (id_functionary) REFERENCES functionaries (id);

--CREATE TABLE users(
	--id INTEGER IDENTITY PRIMARY KEY,
	--role VARCHAR(30),
	--id_basic_unit INTEGER,
	--user_name VARCHAR(30),
	--password VARCHAR(30)	
--);
--ALTER TABLE users ADD CONSTRAINT fk_basic_unit FOREIGN KEY (id_basic_unit) REFERENCES basic_units (id);

