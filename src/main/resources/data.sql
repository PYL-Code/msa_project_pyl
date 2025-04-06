INSERT INTO users (user_id, password, name, email, phone, address, postcode)
VALUES
    ('user001', 'pass1234', '홍길동', 'hong@example.com', '010-1234-5678', '서울시 강남구 테헤란로 1길', '06100'),
    ('user002', 'securePass', '김철수', 'kim@example.com', '010-2345-6789', '부산시 해운대구 센텀로 2길', '48058'),
    ('user003', 'mySecret1', '이영희', 'lee@example.com', '010-3456-7890', '대구시 수성구 동대구로 3길', '42110'),
    ('user004', 'strongPass!', '박지민', 'park@example.com', '010-4567-8901', '인천시 남동구 예술로 4길', '21550'),
    ('user005', 'passWord567', '최수진', 'choi@example.com', '010-5678-9012', '광주시 서구 상무대로 5길', '61947');

INSERT INTO reservation (title, destination, price, duration)
VALUES
    ('하와이 휴양 패키지', '하와이', 1500000, '7일'),
    ('파리 예술 탐방', '파리', 2200000, '10일'),
    ('오사카 미식 여행', '오사카', 850000, '5일'),
    ('뉴욕 시티 투어', '뉴욕', 3000000, '12일'),
    ('발리 럭셔리 리조트', '발리', 1200000, '8일');

