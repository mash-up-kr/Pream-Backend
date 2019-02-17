-- 유저 가입
INSERT INTO user (email, password, nickname) VALUES ('admin@naver.com', 1111, '관리자');
INSERT INTO user (email, password, nickname) VALUES ('user2@naver.com', 1111, '유저2');
INSERT INTO user (email, password, nickname) VALUES ('user3@naver.com', 1111, '유저3');

-- 기본 카테고리 제공
INSERT INTO category (id, name, order_sequence, reg_date) VALUES (1, '즐겨찾기', 1, '2018-12-25 10:22:05');
INSERT INTO category (id, name, order_sequence, reg_date) VALUES (2, '모든 사진', 2, '2018-12-25 10:22:05');
INSERT INTO category (id, name, order_sequence, reg_date) VALUES (3, '인물 사진', 3, '2018-12-25 10:22:05');
INSERT INTO category (id, name, order_sequence, reg_date) VALUES (4, '음식 사진', 4, '2018-12-25 10:22:05');
INSERT INTO category (id, name, order_sequence, reg_date) VALUES (5, '사물 사진', 5, '2018-12-25 10:22:05');
INSERT INTO category (id, name, order_sequence, reg_date) VALUES (6, '풍경 사진', 6, '2018-12-25 10:22:05');

-- 유저2의 카테고리 등록
INSERT INTO category (name, reg_date) VALUES ('풍경 사진', '2018-12-25 10:22:05');
INSERT INTO category (name, reg_date) VALUES ('유저 2꺼', '2018-12-25 10:22:05');
INSERT INTO category (name, reg_date) VALUES ('유저 3꺼', '2018-12-25 10:22:05');
INSERT INTO category (name, reg_date) VALUES ('가나다라', '2018-12-25 10:22:05');

INSERT INTO user_category (user_id, category_id) VALUES (2, 8);
INSERT INTO user_category (user_id, category_id) VALUES (2, 7);
INSERT INTO user_category (user_id, category_id) VALUES (2, 10);