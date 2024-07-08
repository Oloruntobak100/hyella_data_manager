CREATE DATABASE hyella_payment_service;
CREATE USER hps_admin WITH PASSWORD 'randomPassword123!';
GRANT ALL PRIVILEGES ON DATABASE hyella_payment_service TO hps_admin;
CREATE DATABASE hyella_payment_service;

CREATE SCHEMA gateway_service;
GRANT ALL PRIVILEGES ON SCHEMA templateService TO hps_admin;