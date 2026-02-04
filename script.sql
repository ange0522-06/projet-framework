create database travel_agency;
\c travel_agency;


CREATE TABLE client (
    client_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15)
);

create TABLE hotel (
    hotel_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    addresse TEXT NOT NULL,
    phone VARCHAR(15)
);

CREATE TABLE reservation (
    reservation_id SERIAL PRIMARY KEY,
    client_id INT REFERENCES client(client_id),
    nb_people INT NOT NULL,
    dateheure TIMESTAMP NOT NULL,
    hotel_id INT REFERENCES hotel(hotel_id)
);

create TABLE vehicule (
    vehicule_id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    license_plate VARCHAR(20) UNIQUE NOT NULL,
    capacity INT NOT NULL
);

CREATE TABLE regroupement (
    regroupement_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE assignation (
    assignation_id SERIAL PRIMARY KEY,
    client_id INT REFERENCES client(client_id),
    regroupement_id INT REFERENCES regroupement(regroupement_id),
    vehicule_id INT REFERENCES vehicule(vehicule_id),
    assigned_date TIMESTAMP NOT NULL
);