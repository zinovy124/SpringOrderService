INSERT INTO user VALUE (1, "JaeMin");

USE OrderService;

INSERT INTO menu VALUE (1, "미쳐버린맛의치킨", 10000);
INSERT INTO menu VALUE (2, "미쳐버린맛의피자", 20000);

SELECT * FROM orders;
SELECT * FROM menu;
SELECT * FROM user;

DELETE FROM orders WHERE id = 3;

ALTER TABLE user MODIFY id INT AUTO_INCREMENT;
DROP TABLE orders;
DROP TABLE user;

CREATE TABLE user (
    email VARCHAR(100) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(20),
    profile_image_path VARCHAR(255)
);

CREATE TABLE menu (
    id INT PRIMARY KEY,
    name VARCHAR(30),
    price INT
);

CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT, -- 고유 식별자 추가
    user_id VARCHAR(100) REFERENCES user(email) ON DELETE CASCADE,
    menu_id INT REFERENCES menu(id) ON DELETE CASCADE,
    quantity INT,
    price_at_order INT, -- 주문 당시의 가격
    order_date DATETIME, -- 주문 날짜와 시간
    UNIQUE(user_id, menu_id, order_date) -- 사용자-메뉴-주문 시간 복합 키
);

INSERT INTO user VALUE ("example@gamil.com", "1234", "dlwoals", null);
DELETE FROM user
WHERE email = "example@gmail.com";
