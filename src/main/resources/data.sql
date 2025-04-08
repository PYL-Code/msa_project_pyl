INSERT INTO users (user_id, password, name, email, phone, address, postcode)
VALUES
    ('user001', 'pass1234', '홍길동', 'hong@example.com', '010-1234-5678', '서울시 강남구 테헤란로 1길', '06100'),
    ('user002', 'securePass', '김철수', 'kim@example.com', '010-2345-6789', '부산시 해운대구 센텀로 2길', '48058'),
    ('user003', 'mySecret1', '이영희', 'lee@example.com', '010-3456-7890', '대구시 수성구 동대구로 3길', '42110'),
    ('user004', 'strongPass!', '박지민', 'park@example.com', '010-4567-8901', '인천시 남동구 예술로 4길', '21550'),
    ('user005', 'passWord567', '최수진', 'choi@example.com', '010-5678-9012', '광주시 서구 상무대로 5길', '61947');

INSERT INTO reservation (title, description, price, duration, img_url, user_no) VALUES
-- user_no = 1
('제주도 효도 여행 패키지', '풀빌라 숙박 포함', 359000, '2박 3일', 'https://www.agoda.com/wp-content/uploads/2024/07/Jeju-Island-1244x700.jpg', 1),
('일본 오사카 2박 3일 자유여행 패키지', '자유 일정 포함', 499000, '2박 3일', 'https://d28dpoj42hxr8c.cloudfront.net/files/topics/9586_ext_14_ko_0.jpg?v=1449751963', 1),
('이탈리아 로마 패키지', '로마 벤츠 투어', 7990000, '8박 10일', 'https://d3b39vpyptsv01.cloudfront.net/photo/1/2/17f552dbb8d76670480cd3ec5e9ac0c2.jpg', 1),

-- user_no = 2
('중국 상해/청도 일주 패키지', '중국 역사 일주', 4999000, '11박 12일', 'https://img.modetour.com/eagle/photoimg/10718/bfile/636074613108352429.png?resize=y&resize_w=603&resize_h=360&w_h_fill=y', 2),
('아프리카 5개국 패키지', '빅토리아폭포, 세렝게티', 9990000, '8박 10일', 'https://img.modetour.com/eagle/photoimg/74692/bfile/638573534372668089.jpg?resize=y&resize_w=603&resize_h=360&w_h_fill=y', 2),
('스페인 포르투갈 시그니처 패키지', '아그바타워/플라멩코쇼/몬세랏케이블카', 4590000, '7박 9일', 'https://img.modetour.com/eagle/photoimg/48419/bfile/636184546086922674.png?resize=y&resize_w=603&resize_h=360&w_h_fill=y', 2),

-- user_no = 3
('일본 삿포로 온천 패키지', '고품격 온천', 749000, '3박 4일', 'https://www.visit-hokkaido.jp/lsc/upfile/articleDetail/0000/0212/212_2_m.jpg', 3),
('스페인 일주 9일 패키지', '스페인 10대 도시 완전일주', 3790000, '7박 9일', 'https://img.modetour.com/eagle/photoimg/52616/bfile/636506698368246400.jpg?resize=y&resize_w=603&resize_h=360&w_h_fill=y', 3),
('중국 북경/만리장성 패키지', '북경 베이징 만리장성', 999000, '3박 4일', 'https://img.modetour.com/eagle/photoimg/1348/bfile/638648600476966328.jpg?resize=y&resize_w=603&resize_h=360&w_h_fill=y', 3);


