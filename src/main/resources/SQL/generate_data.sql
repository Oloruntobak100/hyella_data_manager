SET search_path TO gateway_service;

INSERT INTO gateway_service.currency (code, name, digits_after_decimal) VALUES
('012', 'Algerian Dinar', 2),
('566', 'Nigerian Naira', 2),
('716', 'Zimbabwe Dollar', 2);


INSERT INTO gateway_service.country (code, name) VALUES
('012', 'Algeria'),
('566', 'Nigeria'),
('716', 'Zimbabwe');

INSERT INTO gateway_service.currency_country(country_code, currency_code) VALUES
('012', '012'),
('566', '566'),
('716', '716');



INSERT INTO gateway_service.organization (deleted, id, name, enabled, phone_number, email_address, address, country_code, created_at, updated_at) VALUES
( FALSE, 1071378364, 'Hyella Algeria', TRUE, '+213-20-123123', 'algeria@hyella.com', '103 Main St, Algiers', '012', '1522553600', '1625145600'),
( FALSE, 675316748, 'Algiers Central Hospital', TRUE, '+213-20-123456', 'contact@algierscentral.com', '123 Main St, Algiers', '012', '1622553600', '1625145600'),
( FALSE, 580976964, 'Oran City Hospital', TRUE, '+213-41-654321', 'info@orancityhospital.dz', '47 Secondary Rd, Oran', '012', '1625131200', '1627819200'),
( FALSE, 19136820, 'Tizi Ouzou Clinic', TRUE, '+213-26-987654', 'admin@tiziouzouclinic.dz', '32 Tertiary Ave, Tizi Ouzou', '012', '1627804800', '1630492800'),
( FALSE, 1221017676, 'Setif Medical Center', TRUE, '+213-36-123987', 'setifmed@setifmedcenter.dz', '58 Quaternary Blvd, Setif', '012', '1630480000', '1633072000'),
( FALSE, 302277388, 'Annaba Health Services', TRUE, '+213-38-789123', 'services@annabahealth.dz', '81 Quinary Lane, Annaba', '012', '1633058400', '1635746400'),
( FALSE, 809664556, 'Algiers East Hospital', FALSE, '+213-21-334455', 'shutdown@algierseast.dz', '789 Eastern Blvd, Algiers', '012', '1614556800', '1622553600');


INSERT INTO gateway_service.organization (deleted, id, name, enabled, phone_number, email_address, address, country_code, created_at, updated_at) VALUES
( FALSE, 4229380, 'Hyella Nigeria', TRUE, '+234-1-2635840', 'nigeria@hyella.com', '12 Broad St, Lagos', '566', '1522553600', '1625145600'),
( FALSE, 3170820, 'Lagos General Hospital', TRUE, '+234-1-2633840', 'contact@lagosgeneral.ng', '12 Broad St, Lagos', '566', '1622553600', '1625145600'),
( FALSE, 1785686550, 'Abuja National Hospital', TRUE, '+234-9-2348210', 'info@abujanational.ng', '24 Capitol Ave, Abuja', '566', '1625131200', '1627819200'),
( FALSE, 21741598, 'Kano Specialist Hospital', TRUE, '+234-64-9876543', 'admin@kanospecialist.ng', '56 Hospital Rd, Kano', '566', '1627804800', '1630492800'),
( FALSE, 10248883, 'Ibadan Health Clinic', TRUE, '+234-2-2310942', 'ibadanhealth@clinic.ng', '33 University Way, Ibadan', '566', '1630480000', '1633072000'),
( FALSE, 60342501, 'Port Harcourt Medical Centre', TRUE, '+234-84-237890', 'service@portharcourtmed.ng', '90 River Rd, Port Harcourt', '566', '1633058400', '1635746400'),
( FALSE, 73038703, 'Enugu Health Services', FALSE, '+234-42-556677', 'shutdown@enuguhealth.ng', '789 Unity St, Enugu', '566', '1614556800', '1622553600');


INSERT INTO gateway_service.organization (deleted, id, name, enabled, phone_number, email_address, address, country_code, created_at, updated_at) VALUES
( FALSE, 270033364, 'Hyella Zimbabwe', TRUE, '+263-4-163456', 'zimbabwe@hyella.com', '10 King St, Harare', '716', '1522553600', '1625145600'),
( FALSE, 930347508, 'Harare Hospital', TRUE, '+263-4-123456', 'contact@hararehospital.zw', '101 King St, Harare', '716', '1622553600', '1625145600'),
( FALSE, 68766452, 'Bulawayo Health Services', TRUE, '+263-9-654321', 'info@bulawayohealth.zw', '202 Queen St, Bulawayo', '716', '1625131200', '1627819200'),
( FALSE, 27575438, 'Chitungwiza Clinic', TRUE, '+263-270-987654', 'admin@chitungwizaclinic.zw', '303 Prince Rd, Chitungwiza', '716', '1627804800', '1630492800'),
( FALSE, 91273733, 'Mutare Medical Centre', TRUE, '+263-20-123987', 'mutaremed@centre.zw', '404 Duke Blvd, Mutare', '716', '1630480000', '1633072000'),
( FALSE, 53618046, 'Gweru General Hospital', TRUE, '+263-54-789123', 'services@gwerugeneral.zw', '505 Earl Ln, Gweru', '716', '1633058400', '1635746400'),
( FALSE, 29067396, 'Gwanda Care Unit', FALSE, '+263-284-667788', 'offline@gwandacare.zw', '505 Gold St, Gwanda', '716', '1625088000', '1633058400');


INSERT INTO gateway_service.strategic_business_unit (organization_id, name, enabled, phone_number, email_address, address, country_code, created_at, updated_at)
VALUES
((SELECT id FROM gateway_service.organization WHERE country_code = '012' AND name = 'Algiers Central Hospital'), 'Cardiology Center draria', TRUE, '+213-20-123456', 'contact_cardiology@algierscentral.com', '123 Main St, Draria, Algiers', '012', 1622553600, 1625145600),
((SELECT id FROM gateway_service.organization WHERE country_code = '012' AND name = 'Oran City Hospital'), 'Pediatrics Facility', TRUE, '+213-41-654321', 'contact_pediatrics@orancityhospital.dz', '47 Secondary Rd, Oran', '012', 1625131200, 1627819200),
((SELECT id FROM gateway_service.organization WHERE country_code = '012' AND name = 'Tizi Ouzou Clinic'), 'Laboratory Services', TRUE, '+213-26-987654', 'contact_lab@tiziouzouclinic.dz', '32 Tertiary Ave, Tizi Ouzou', '012', 1627804800, 1630492800),
((SELECT id FROM gateway_service.organization WHERE country_code = '012' AND name = 'Setif Medical Center'), 'Emergency Department', TRUE, '+213-36-123987', 'contact_emergency@setifmedcenter.dz', '58 Quaternary Blvd, Setif', '012', 1630480000, 1633072000),
((SELECT id FROM gateway_service.organization WHERE country_code = '012' AND name = 'Annaba Health Services'), 'Orthopedics Services', TRUE, '+213-38-789123', 'contact_orthopedics@annabahealth.dz', '81 Quinary Lane, Annaba', '012', 1633058400, 1635746400),
((SELECT id FROM gateway_service.organization WHERE country_code = '012' AND name = 'Algiers East Hospital'), 'Radiology Center', FALSE, '+213-21-334455', 'contact_radiology@algierseast.dz', '789 Eastern Blvd, Algiers', '012', 1614556800, 1622553600),
((SELECT id FROM gateway_service.organization WHERE country_code = '012' AND name = 'Algiers East Hospital'), 'Gynecology Center', TRUE, '+213-21-334455', 'contact_gynecology@algierseast.dz', '789 Eastern Blvd, Algiers', '012', 1614556800, 1622553600),
((SELECT id FROM gateway_service.organization WHERE country_code = '012' AND name = 'Algiers East Hospital'), 'Oncology Center', TRUE, '+213-21-334455', 'contact_oncology@algierseast.dz', '789 Eastern Blvd, Algiers', '012', 1614556800, 1622553600),
((SELECT id FROM gateway_service.organization WHERE country_code = '012' AND name = 'Algiers East Hospital'), 'Neurology Center', FALSE, '+213-21-334455', 'contact_neurology@algierseast.dz', '789 Eastern Blvd, Algiers', '012', 1614556800, 1622553600),
((SELECT id FROM gateway_service.organization WHERE country_code = '012' AND name = 'Algiers East Hospital'), 'Dermatology Clinic', TRUE, '+213-21-334455', 'contact_dermatology@algierseast.dz', '789 Eastern Blvd, Algiers', '012', 1614556800, 1622553600),
((SELECT id FROM gateway_service.organization WHERE country_code = '012' AND name = 'Algiers East Hospital'), 'ENT Clinic', FALSE, '+213-21-334455', 'contact_ent@algierseast.dz', '789 Eastern Blvd, Algiers', '012', 1614556800, 1622553600);

-- 5B.  Insert SBUs for Nigerian hospitals
INSERT INTO gateway_service.strategic_business_unit (organization_id, name, enabled, phone_number, email_address, address, country_code, created_at, updated_at)
VALUES
((SELECT id FROM gateway_service.organization WHERE country_code = '566' AND name = 'Lagos General Hospital'), 'Pharmacy', TRUE, '+234-1-2633840', 'contact_pharmacy@lagosgeneral.ng', '12 Broad St, Lagos', '566', 1622553600, 1625145600),
((SELECT id FROM gateway_service.organization WHERE country_code = '566' AND name = 'Abuja National Hospital'), 'Radiology Services', TRUE, '+234-9-2348210', 'contact_radiology@abujanational.ng', '24 Capitol Ave, Abuja', '566', 1625131200, 1627819200),
((SELECT id FROM gateway_service.organization WHERE country_code = '566' AND name = 'Kano Specialist Hospital'), 'Cardiovascular Department', TRUE, '+234-64-9876543', 'contact_cardiovascular@kanospecialist.ng', '56 Hospital Rd, Kano', '566', 1627804800, 1630492800),
((SELECT id FROM gateway_service.organization WHERE country_code = '566' AND name = 'Ibadan Health Clinic'), 'Dental Care Center', TRUE, '+234-2-2310942', 'contact_dental@ibadanhealth.clinic', '33 University Way, Ibadan', '566', 1630480000, 1633072000),
((SELECT id FROM gateway_service.organization WHERE country_code = '566' AND name = 'Port Harcourt Medical Centre'), 'Pediatrics Unit', TRUE, '+234-84-237890', 'contact_pediatrics@portharcourtmed.ng', '90 River Rd, Port Harcourt', '566', 1633058400, 1635746400),
((SELECT id FROM gateway_service.organization WHERE country_code = '566' AND name = 'Enugu Health Services'), 'Orthopedics Ward', FALSE, '+234-42-556677', 'contact_orthopedics@enuguhealth.ng', '789 Unity St, Enugu', '566', 1614556800, 1622553600),
((SELECT id FROM gateway_service.organization WHERE country_code = '566' AND name = 'Enugu Health Services'), 'Neurology Department', TRUE, '+234-42-556677', 'contact_neurology@enuguhealth.ng', '789 Unity St, Enugu', '566', 1614556800, 1622553600),
((SELECT id FROM gateway_service.organization WHERE country_code = '566' AND name = 'Enugu Health Services'), 'Ophthalmology Clinic', TRUE, '+234-42-556677', 'contact_ophthalmology@enuguhealth.ng', '789 Unity St, Enugu', '566', 1614556800, 1622553600),
((SELECT id FROM gateway_service.organization WHERE country_code = '566' AND name = 'Enugu Health Services'), 'Psychiatry Unit', FALSE, '+234-42-556677', 'contact_psychiatry@enuguhealth.ng', '789 Unity St, Enugu', '566', 1614556800, 1622553600),
((SELECT id FROM gateway_service.organization WHERE country_code = '566' AND name = 'Enugu Health Services'), 'Urology Department', FALSE, '+234-42-556677', 'contact_urology@enuguhealth.ng', '789 Unity St, Enugu', '566', 1614556800, 1622553600),
((SELECT id FROM gateway_service.organization WHERE country_code = '566' AND name = 'Enugu Health Services'), 'Gastroenterology Clinic', TRUE, '+234-42-556677', 'contact_gastroenterology@enuguhealth.ng', '789 Unity St, Enugu', '566', 1614556800, 1622553600);

-- 5C.  Insert SBUs for Zimbabwean hospitals
INSERT INTO gateway_service.strategic_business_unit (organization_id, name, enabled, phone_number, email_address, address, country_code, created_at, updated_at)
VALUES
((SELECT id FROM gateway_service.organization WHERE country_code = '716' AND name = 'Harare Hospital'), 'Harare Hospital - Lab 1', TRUE, '+263-4-123456', 'contact1@hararehospital.zw', '101 King St, Harare', '716', 1622553600, 1625145600),
((SELECT id FROM gateway_service.organization WHERE country_code = '716' AND name = 'Bulawayo Health Services'), 'Bulawayo Health Services - Branch 2', FALSE, '+263-9-654321', 'contact2@bulawayohealth.zw', '202 Queen St, Bulawayo', '716', 1625131200, 1627819200),
((SELECT id FROM gateway_service.organization WHERE country_code = '716' AND name = 'Chitungwiza Clinic'), 'Chitungwiza Clinic - Branch 3', FALSE, '+263-270-987654', 'contact3@chitungwizaclinic.zw', '303 Prince Rd, Chitungwiza', '716', 1627804800, 1630492800);


INSERT INTO gateway_service.role (name, created_at, updated_at)
VALUES
('Hyella Reconciliation Analyst', 1622553600, 1625145600),
('Reconciliation Analyst', 1622553600, 1625145600),
('Settlement Analyst', 1625131200, 1627819200),
('Payments Support Analyst', 1630480000, 1633072000),
('Payment Operations Manager', 1633058400, 1635746400);


INSERT INTO gateway_service.user (first_name, last_name, organization_id, source_system_user_id, created_at, updated_at, created_by_user_id) VALUES
('ALG - Kenneth', 'Uvah', (SELECT id FROM gateway_service.organization WHERE name='Hyella Algeria'), null, 1622553601, 1627819202, null ),
('ALG - Karim', 'Ouazene', (SELECT id FROM gateway_service.organization WHERE name='Hyella Algeria'), null, 1625131202, 1627819202, 1),
('ALG - Amir', 'Boualem', (SELECT id FROM gateway_service.organization WHERE name='Algiers Central Hospital'), 'AB123', 1622553601, 1625145601, 1),
('ALG - Lamia', 'Saadi', (SELECT id FROM gateway_service.organization WHERE name='Algiers Central Hospital'), 'LS456', 1622553602, 1625145602, 1),
('ALG - Fares', 'Kerroum', (SELECT id FROM gateway_service.organization WHERE name='Oran City Hospital'), 'FK789', 1625131201, 1627819201, 1),
('ALG - Soraya', 'Bensalem', (SELECT id FROM gateway_service.organization WHERE name='Tizi Ouzou Clinic'), 'SB345', 1627804801, 1630492801, 1),
('ALG - Tahar', 'Moussi', (SELECT id FROM gateway_service.organization WHERE name='Tizi Ouzou Clinic'), 'TM678', 1627804802, 1630492802, 1),
('ALG - Nadia', 'Belhadj', (SELECT id FROM gateway_service.organization WHERE name='Setif Medical Center'), 'NB901', 1630480001, 1633072001, 1),
('ALG - Samir', 'Djennadi', (SELECT id FROM gateway_service.organization WHERE name='Setif Medical Center'), 'SD234', 1630480002, 1633072002, 1),
('ALG - Lina', 'Hamroune', (SELECT id FROM gateway_service.organization WHERE name='Annaba Health Services'), 'LH567', 1633058401, 1635746401, 1),
('ALG - Rachid', 'Zerrouki', (SELECT id FROM gateway_service.organization WHERE name='Annaba Health Services'), 'RZ890', 1633058402, 1635746402, 1),
('ALG - Yasmine', 'Touati', (SELECT id FROM gateway_service.organization WHERE name='Algiers East Hospital'), 'YT123', 1614556801, 1622553601, 1),
('ALG - Hakim', 'Serir', (SELECT id FROM gateway_service.organization WHERE name='Algiers East Hospital'), 'HS456', 1614556802, 1622553602, 1),
('ALG - Nour', 'Ould Ali', (SELECT id FROM gateway_service.organization WHERE name='Algiers Central Hospital'), 'NO789', 1622553610, 1625145610, 1),
('ALG - Safia', 'Benchikh', (SELECT id FROM gateway_service.organization WHERE name='Oran City Hospital'), 'SB012', 1625131210, 1627819210, 1),
('ALG - Amine', 'Mehenni', (SELECT id FROM gateway_service.organization WHERE name='Tizi Ouzou Clinic'), 'AM345', 1627804810, 1630492810, 1),
('ALG - Samia', 'Larbi', (SELECT id FROM gateway_service.organization WHERE name='Setif Medical Center'), 'SL678', 1630480010, 1633072010, 1),
('ALG - Omar', 'Fekhar', (SELECT id FROM gateway_service.organization WHERE name='Annaba Health Services'), 'OF901', 1633058410, 1635746410, 1),
('ALG - Khalil', 'Dahmani', (SELECT id FROM gateway_service.organization WHERE name='Algiers East Hospital'), 'KD234', 1614556810, 1622553610, 1),
('ALG - Leila', 'Zeroual', (SELECT id FROM gateway_service.organization WHERE name='Algiers Central Hospital'), 'LZ567', 1622553620, 1625145620, 1),
('ALG - Sofiane', 'Mokhtar', (SELECT id FROM gateway_service.organization WHERE name='Oran City Hospital'), 'SM890', 1625131220, 1627819220, 1);


INSERT INTO gateway_service.user (first_name, last_name, organization_id, source_system_user_id, created_at, updated_at, created_by_user_id) VALUES
('NG - Kenneth', 'Uvah', (SELECT id FROM gateway_service.organization WHERE name='Hyella Nigeria'), null, 1622553601, 1627819202, null),
('NG - Adeola', 'Okeke', (SELECT id FROM gateway_service.organization WHERE name='Hyella Nigeria'), 'AO123', 1522553601, 1625145601,1),
('NG - Chinedu', 'Balogun', (SELECT id FROM gateway_service.organization WHERE name='Hyella Nigeria'), 'CB456', 1522553602, 1625145602,1),
('NG - Ifeoma', 'Eze', (SELECT id FROM gateway_service.organization WHERE name='Lagos General Hospital'), 'IE789', 1622553601, 1625145601,1),
('NG - Obinna', 'Okonkwo', (SELECT id FROM gateway_service.organization WHERE name='Lagos General Hospital'), 'OO012', 1622553602, 1625145602,1),
('NG - Amaka', 'Ndubuisi', (SELECT id FROM gateway_service.organization WHERE name='Abuja National Hospital'), 'AN345', 1625131201, 1627819201,1),
('NG - Yemi', 'Abubakar', (SELECT id FROM gateway_service.organization WHERE name='Abuja National Hospital'), 'YA678', 1625131202, 1627819202,1),
('NG - Hassan', 'Ibrahim', (SELECT id FROM gateway_service.organization WHERE name='Kano Specialist Hospital'), 'HI901', 1627804801, 1630492801,1),
('NG - Folake', 'Adebayo', (SELECT id FROM gateway_service.organization WHERE name='Kano Specialist Hospital'), 'FA234', 1627804802, 1630492802,1),
('NG - Ngozi', 'Okereke', (SELECT id FROM gateway_service.organization WHERE name='Ibadan Health Clinic'), 'NO567', 1630480001, 1633072001,1),
('NG - Uche', 'Chukwu', (SELECT id FROM gateway_service.organization WHERE name='Ibadan Health Clinic'), 'UC890', 1630480002, 1633072002,1),
('NG - Chibuzo', 'Ahmed', (SELECT id FROM gateway_service.organization WHERE name='Port Harcourt Medical Centre'), 'CA123', 1633058401, 1635746401,1),
('NG - Fatima', 'Aliyu', (SELECT id FROM gateway_service.organization WHERE name='Port Harcourt Medical Centre'), 'FA456', 1633058402, 1635746402,1),
('NG - Kemi', 'Ojo', (SELECT id FROM gateway_service.organization WHERE name='Enugu Health Services'), 'KO789', 1614556801, 1622553601,1),
('NG - Emeka', 'Nwosu', (SELECT id FROM gateway_service.organization WHERE name='Enugu Health Services'), 'EN012', 1614556802, 1622553602,1),
('NG - Tolu', 'Lawal', (SELECT id FROM gateway_service.organization WHERE name='Hyella Nigeria'), 'TL345', 1522553610, 1625145610,1),
('NG - Bisi', 'Adewale', (SELECT id FROM gateway_service.organization WHERE name='Lagos General Hospital'), 'BA678', 1622553610, 1625145610,1),
('NG - Sade', 'Akintola', (SELECT id FROM gateway_service.organization WHERE name='Abuja National Hospital'), 'SA901', 1625131210, 1627819210,1),
('NG - Chike', 'Okafor', (SELECT id FROM gateway_service.organization WHERE name='Kano Specialist Hospital'), 'CO234', 1627804810, 1630492810,1),
('NG - Ada', 'Eze', (SELECT id FROM gateway_service.organization WHERE name='Ibadan Health Clinic'), 'AE567', 1630480010, 1633072010,1),
('NG - Tayo', 'Oladipo', (SELECT id FROM gateway_service.organization WHERE name='Port Harcourt Medical Centre'), 'TO890', 1633058410, 1635746410,1);



INSERT INTO gateway_service.user (first_name, last_name, organization_id, source_system_user_id, created_at, updated_at, created_by_user_id) VALUES
('ZIB - Tendai', 'Moyo', (SELECT id FROM gateway_service.organization WHERE name='Hyella Zimbabwe'), 'TM123', 1522553601, 1625145601, 1),
('ZIB - Farai', 'Ncube', (SELECT id FROM gateway_service.organization WHERE name='Hyella Zimbabwe'), 'FN456', 1522553602, 1625145602, 1),
('ZIB - Rumbidzai', 'Makoni', (SELECT id FROM gateway_service.organization WHERE name='Harare Hospital'), 'RM789', 1622553601, 1625145601, 1),
('ZIB - Tafadzwa', 'Chinamasa', (SELECT id FROM gateway_service.organization WHERE name='Harare Hospital'), 'TC012', 1622553602, 1625145602, 1),
('ZIB - Tanaka', 'Mushonga', (SELECT id FROM gateway_service.organization WHERE name='Bulawayo Health Services'), 'TM345', 1625131201, 1627819201, 1),
('ZIB - Chipo', 'Mare', (SELECT id FROM gateway_service.organization WHERE name='Bulawayo Health Services'), 'CM678', 1625131202, 1627819202, 1),
('ZIB - Kudzai', 'Bere', (SELECT id FROM gateway_service.organization WHERE name='Chitungwiza Clinic'), 'KB901', 1627804801, 1630492801, 1),
('ZIB - Tatenda', 'Chirara', (SELECT id FROM gateway_service.organization WHERE name='Chitungwiza Clinic'), 'TC234', 1627804802, 1630492802, 1),
('ZIB - Sibongile', 'Mazibuko', (SELECT id FROM gateway_service.organization WHERE name='Mutare Medical Centre'), 'SM567', 1630480001, 1633072001, 1),
('ZIB - Tinashe', 'Mutizwa', (SELECT id FROM gateway_service.organization WHERE name='Mutare Medical Centre'), 'TM890', 1630480002, 1633072002, 1),
('ZIB - Nyasha', 'Madziva', (SELECT id FROM gateway_service.organization WHERE name='Gweru General Hospital'), 'NM123', 1633058401, 1635746401, 1),
('ZIB - Anesu', 'Kumalo', (SELECT id FROM gateway_service.organization WHERE name='Gweru General Hospital'), 'AK456', 1633058402, 1635746402, 1),
('ZIB - Panashe', 'Gumbo', (SELECT id FROM gateway_service.organization WHERE name='Gwanda Care Unit'), 'PG789', 1625088001, 1633058401, 1),
('ZIB - Shingai', 'Rusike', (SELECT id FROM gateway_service.organization WHERE name='Gwanda Care Unit'), 'SR012', 1625088002, 1633058402, 1),
('ZIB - Ropafadzo', 'Mapimhidze', (SELECT id FROM gateway_service.organization WHERE name='Hyella Zimbabwe'), 'RM345', 1522553610, 1625145610, 1),
('ZIB - Munyaradzi', 'Gwatidzo', (SELECT id FROM gateway_service.organization WHERE name='Harare Hospital'), 'MG678', 1622553610, 1625145610, 1),
('ZIB - Takunda', 'Mashava', (SELECT id FROM gateway_service.organization WHERE name='Bulawayo Health Services'), 'TM901', 1625131210, 1627819210, 1),
('ZIB - Batsirai', 'Mutasa', (SELECT id FROM gateway_service.organization WHERE name='Chitungwiza Clinic'), 'BM234', 1627804810, 1630492810, 1),
('ZIB - Fadzai', 'Dziva', (SELECT id FROM gateway_service.organization WHERE name='Mutare Medical Centre'), 'FD567', 1630480010, 1633072010, 1),
('ZIB - Tawanda', 'Muchenje', (SELECT id FROM gateway_service.organization WHERE name='Gweru General Hospital'), 'TM890', 1633058410, 1635746410, 1);
