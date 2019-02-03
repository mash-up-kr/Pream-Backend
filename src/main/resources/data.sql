-- 관리자 가입
INSERT INTO user (email, password, nickname) VALUES ('admin@naver.com', 1111, '관리자');
-- 기본 카테고리 제공
INSERT INTO category (id, name, order_sequence) VALUES (1, '모든 사진', 1);
INSERT INTO category (id, name, order_sequence) VALUES (2, '인물 사진', 2);
INSERT INTO category (id, name, order_sequence) VALUES (3, '음식 사진', 3);
INSERT INTO category (id, name, order_sequence) VALUES (4, '사물 사진', 4);
INSERT INTO category (id, name, order_sequence) VALUES (5, '풍경 사진', 5);
