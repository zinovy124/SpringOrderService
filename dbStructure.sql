create database OrderService;
use OrderService;
drop database OrderService;

CREATE TABLE user (
	id INT PRIMARY KEY,
    name VARCHAR(20)
);

CREATE TABLE menu (
	id INT PRIMARY KEY,
    name VARCHAR(30),
    price INT
);

CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT, -- 고유 식별자 추가
    user_id INT REFERENCES user(id) ON DELETE CASCADE,
    menu_id INT REFERENCES menu(id) ON DELETE CASCADE,
    quantity INT,
    price_at_order INT, -- 주문 당시의 가격
    order_date DATETIME, -- 주문 날짜와 시간
    UNIQUE(user_id, menu_id, order_date) -- 사용자-메뉴-주문 시간 복합 키
);

drop table orders;