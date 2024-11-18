DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS MEMBER;

CREATE TABLE `member` (
  `id` varchar(12) NOT NULL,
  `password` varchar(20) NOT NULL,
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
  img_dir varchar(20),
  description varchar(2000),
  remainings SMALLINT NOT NULL DEFAULT 0,
  
  INDEX (name),
  INDEX (price)
);

CREATE TABLE review(
  prod_id int NOT null,
  mem_id varchar(12) NOT NULL,
  content varchar(200) NOT NULL,
  write_time timestamp NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (prod_id, mem_id)
);

INSERT INTO MEMBER
(id, password, name, email, phone, join_date, is_merchant)
VALUES
('synthaxe', 'futureman1q!Q', 'Drumitar Inventor', 'futureman@fleck.tones', '01020304050', str_to_date('20230405','%Y%m%d'), 1),
('vwooten', 'uchng!09OP', 'Victor Wooten', 'victor@woot.en', '0803350020', str_to_date('20240506','%Y%m%d'), 1);

INSERT INTO MEMBER
(id, password, name)
VALUES
('dhssut', '1q2w3e4r!Q', 'Dorothee Hatshepsut'),
('tconrad', '1q2w3e4r!Q', 'Tyson Conrad'),
('mmagana', '1qa2ws#ED', 'Mariam Magana'),
('mtavares', '1qa@WS3ed', 'Marion Tavares'),
('lbond', '123QWE!@#qwe', 'Lexie Bond');

INSERT INTO product
(merchant_id, name, price, img_dir, description, remainings)
VALUES
('synthaxe', 'Zendrum', 1020304, '/test/test0.jpg', 'Synth Drum', 23),
('synthaxe', 'Synth Axe', 2030405, '/test/test1.jpg', 'Synth Axe', 16),
('vwooten', 'Palmystery', 23232, '/test/test2.jpg', 'Album', 1719);

INSERT INTO review
(prod_id, mem_id, content)
VALUES
(3, 'mtavares', 'I''ve got the lesson'),
(2, 'dhssut', 'If you ain''t got no pocket');

ALTER TABLE product ADD FOREIGN KEY (merchant_id) REFERENCES member(id);
ALTER TABLE review ADD FOREIGN KEY (prod_id) REFERENCES product(prod_id);
ALTER TABLE review ADD FOREIGN KEY (mem_id) REFERENCES member(id);
ALTER TABLE review ADD INDEX (write_time);