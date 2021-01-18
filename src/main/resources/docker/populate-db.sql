

DELETE FROM ads_ages ;
DELETE FROM ads_countrys ;
DELETE FROM ads_genders ;
DELETE FROM ads ;
DELETE FROM users;
DELETE FROM ages;
DELETE FROM countrys;
DELETE FROM genders;



INSERT INTO ages (id_age, active, age) VALUES(1, 1, 1);
INSERT INTO ages (id_age, active, age) VALUES(2, 1, 2);
INSERT INTO ages (id_age, active, age) VALUES(3, 1, 3);
INSERT INTO ages (id_age, active, age) VALUES(4, 1, 4);
INSERT INTO ages (id_age, active, age) VALUES(5, 1, 5);
INSERT INTO ages (id_age, active, age) VALUES(6, 1, 6);
INSERT INTO ages (id_age, active, age) VALUES(7, 1, 7);
INSERT INTO ages (id_age, active, age) VALUES(8, 1, 8);
INSERT INTO ages (id_age, active, age) VALUES(9, 1, 9);
INSERT INTO countrys (id_country, active, country)VALUES(1, 1, 'Argentina');
INSERT INTO countrys (id_country, active, country)VALUES(2, 1, 'EE.UU.');
INSERT INTO countrys (id_country, active, country)VALUES(3, 1, 'España');
INSERT INTO genders (id_gender, active, gender)VALUES(1, 1, 'Femenino');
INSERT INTO genders (id_gender, active, gender)VALUES(2, 1, 'Masculino');


INSERT INTO simpleDB.ads (id_ad, active,description,end_date,price,start_date,title,total_amount, consumed_amount) VALUES
	 (1, 1,'Some description 001','2021-01-29 00:00:00',10.50,'2021-01-01 00:00:00','Ad001',1000.00, 0),
	 (2, 1,'Some description 002','2021-01-29 00:00:00',12.50,'2021-01-01 00:00:00','Ad002',10000.00, 0),	 
	 (3, 1,'Some description 003','2021-01-29 00:00:00',13.00,'2021-01-01 00:00:00','Ad003',2000.00, 0),
	 (4, 1,'Some description 004','2021-01-29 00:00:00',14.50,'2021-01-01 00:00:00','Ad004',4000.00, 0),
	 (5, 1,'Some description 005','2021-01-29 00:00:00',15.50,'2021-01-01 00:00:00','Ad005',5000.00, 0),
	 (6, 1,'Some description 006','2021-01-29 00:00:00',5.50,'2021-01-01 00:00:00','Ad006',5000.00, 0),
	 (7, 1,'Some description 007','2021-01-29 00:00:00',12.50,'2021-01-01 00:00:00','Ad007',5000.00, 0),
	 (8, 1,'Some description 008','2021-01-29 00:00:00',14.00,'2021-01-01 00:00:00','Ad008',5000.00, 0),
	 (9, 1,'Some description 009','2021-01-29 00:00:00',15.50,'2021-01-01 00:00:00','Ad009',5000.00, 0),
	 (10, 1,'Some description 010','2021-01-29 00:00:00',15.50,'2021-01-01 00:00:00','Ad010',5000.00, 0);
	
INSERT INTO simpleDB.ads_ages (id_ad,id_ages) VALUES
	 (1,2),
	 (1,3);
INSERT INTO simpleDB.ads_ages (id_ad,id_ages) VALUES
	 (2,4);
INSERT INTO simpleDB.ads_ages (id_ad,id_ages) VALUES
	 (3,2),
	 (3,3),
	 (3,7);
INSERT INTO simpleDB.ads_ages (id_ad,id_ages) VALUES
	 (4,5),
	 (4,6),
	 (4,7);
INSERT INTO simpleDB.ads_ages (id_ad,id_ages) VALUES
	 (4,5),
	 (4,6),
	 (4,7);
INSERT INTO simpleDB.ads_ages (id_ad,id_ages) VALUES
	 (6,1),
	 (6,2),
	 (6,3),
	 (6,3),
	 (6,4),
	 (6,5),
	 (6,6),
	 (6,7),
	 (6,8),
	 (6,9);
INSERT INTO simpleDB.ads_ages (id_ad,id_ages) VALUES
	 (7,5),
	 (7,8),
	 (7,7);
INSERT INTO simpleDB.ads_ages (id_ad,id_ages) VALUES
	 (9,5),
	 (9,6),
	 (9,9);
	
INSERT INTO simpleDB.ads_countrys (id_ad,id_country) VALUES
	 (1,1);
INSERT INTO simpleDB.ads_countrys (id_ad,id_country) VALUES
	 (2,2);
INSERT INTO simpleDB.ads_countrys (id_ad,id_country) VALUES
	 (3,3);
INSERT INTO simpleDB.ads_countrys (id_ad,id_country) VALUES
	 (6,1),
	 (6,2),
	 (6,3);
	
INSERT INTO simpleDB.ads_genders (id_ad,id_gender) VALUES
	 (1,1);
INSERT INTO simpleDB.ads_genders (id_ad,id_gender) VALUES
	 (2,1),
	 (2,2);
INSERT INTO simpleDB.ads_genders (id_ad,id_gender) VALUES
	 (3,2);
INSERT INTO simpleDB.ads_genders (id_ad,id_gender) VALUES
	 (4,2);
INSERT INTO simpleDB.ads_genders (id_ad,id_gender) VALUES
	 (6,1),
	 (6,2);

INSERT INTO simpleDB.users (id_user,active,user_name,user_pwd,id_age,id_country,id_gender) VALUES
	 (1,1,'User1','Pwd!',1,1,1),
	 (2,1,'User2','Pwd!',4,2,2),
	 (3,1,'User3','Pwd!',5,3,1);

	

