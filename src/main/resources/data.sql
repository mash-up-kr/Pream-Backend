-- 유저 가입
INSERT INTO user (email, password, nickname) VALUES ('admin@naver.com', 1111, '관리자');
INSERT INTO user (email, password, nickname) VALUES ('user2@naver.com', 1111, '유저2');
INSERT INTO user (email, password, nickname) VALUES ('user3@naver.com', 1111, '유저3');

-- 필터 추가
INSERT INTO filter (name, img_url, reg_date,  share_date, shared_count, use_count, admin_yn, user_id) VALUES ('관리자 치킨',
'https://s3.namuwikiusercontent.com/s/e8a91b34f71348b3367f2bfaaf8d59410439a47a11539a5fb0391478252d27a8a22b79030e57692d487023a454cc322c9624ca962096a9ac7c1d8e5caef90183425fe4eb27373f20318be55357e87bc605b810b79a82de9a8c245e6850a6de44',
'2019-01-01 00:00:01', '2019-01-01 00:00:01', 1, 0, true, 1);