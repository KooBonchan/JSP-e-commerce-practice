DROP VIEW IF EXISTS product_view;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS MEMBER;

CREATE TABLE `member` (
  `id` varchar(12) NOT NULL,
  `password` char(64) NOT NULL, -- sha 256 encrypted
  `name` varchar(20) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `join_date` date NOT NULL DEFAULT (curdate()),
  `is_merchant` boolean NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
);

CREATE TABLE product(
  prod_id int PRIMARY KEY AUTO_INCREMENT,
  merchant_id varchar(12) NOT NULL,
  name varchar(30) NOT NULL,
  price int NOT NULL,
  img_path char(40), -- uuid
  description varchar(2000),
  inventory int NOT NULL DEFAULT 0,
  
  INDEX (name),
  INDEX (price)
);

CREATE TABLE review(
  id int PRIMARY KEY AUTO_INCREMENT,
  prod_id int NOT NULL,
  mem_id varchar(12) NOT NULL,
  content varchar(200) NOT NULL,
  write_time timestamp NOT NULL DEFAULT current_timestamp
);

INSERT INTO MEMBER
(id, password, name, email, phone, join_date, is_merchant)
VALUES
('vwooten', '0511c30e1a4601c4b6676608c33ef51b801b661b7af034b2decdddcac1d34c6f', 'Victor Wooten', 'victor@woot.en', '0803350020', str_to_date('20240506','%Y%m%d'), 1),
('synthaxe', '2e2a826cf524f9a6c40534245f3db92933de66d4c22061c1f5c787fd204c97b8', 'Drumitar Inventor', 'futureman@fleck.tones', '01020304050', str_to_date('20230405','%Y%m%d'), 1),
('berthoud', '8ed0751a64e9db53a957c4191ee5a9cd9a0dbc685f63711bf598d766d5e9d814', 'Charles Berthoud', 'basscamp@you.tube', '06212345678', str_to_date('20240506','%Y%m%d'), 1);


INSERT INTO MEMBER
(id, password, name)
VALUES
('dhssut', '1q2w3e4r!Q', 'Dorothee Hatshepsut'),
('tconrad', '1q2w3e4r!Q', 'Tyson Conrad'),
('mmagana', '1qa2ws#ED', 'Mariam Magana'),
('mtavares', '1qa@WS3ed', 'Marion Tavares'),
('lbond', '123QWE!@#qwe', 'Lexie Bond');

INSERT INTO product
(merchant_id, name, price, img_path, description, inventory)
VALUES
('synthaxe', 'Zendrum', 1020304, 'test0.jpg', 'Percussive Synthesizer device with 24 triggers, made to be worn with a strap like a guitar;', 23),
('synthaxe', 'Synth Axe', 2030405, 'test1.jpg', 'Futureman modified it into a Drumitar.', 16),
('vwooten', 'Palmystery', 23232, 'test2.jpg', 'Victor Wooten gives you "The Lesson", you''ll get dominated by the groove', 1719);

INSERT INTO review
(prod_id, mem_id, content)
VALUES
(3, 'mtavares', 'I''ve got the lesson'),
(2, 'dhssut', 'If you ain''t got no pocket');

ALTER TABLE product ADD FOREIGN KEY (merchant_id) REFERENCES member(id);
ALTER TABLE review ADD FOREIGN KEY (prod_id) REFERENCES product(prod_id);
ALTER TABLE review ADD FOREIGN KEY (mem_id) REFERENCES member(id);
ALTER TABLE review ADD INDEX (write_time);

CREATE VIEW product_view AS
SELECT 
  m.name AS provider,
  m.id AS provider_id,
  p.prod_id AS id,
  p.name,
  p.price,
  p.description,
  p.img_path,
  p.inventory
FROM
product p
INNER JOIN MEMBER m
ON m.id = p.merchant_id;

SELECT * FROM MEMBER;
SELECT * FROM product;
